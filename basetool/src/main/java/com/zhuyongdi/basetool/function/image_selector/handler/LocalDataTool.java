package com.zhuyongdi.basetool.function.image_selector.handler;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.zhuyongdi.basetool.function.image_selector.bean.Folder;
import com.zhuyongdi.basetool.function.image_selector.bean.Item;
import com.zhuyongdi.basetool.function.image_selector.comparator.ImageFolderComparator;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Created by ZhuYongdi on 2019/3/26.
 */
public class LocalDataTool {

    private ArrayList<Folder> folderList = new ArrayList<>();
    private HashSet<String> fileCacheSet = new HashSet<>();
    private Context context;
    private static LocalDataTool instance;

    private LocalDataTool(Context context) {
        this.context = context;
    }

    public static LocalDataTool getInstance(Context context) {
        if (instance == null) {
            synchronized (LocalDataTool.class) {
                if (instance == null) {
                    instance = new LocalDataTool(context);
                }
            }
        }
        return instance;
    }

    public ArrayList<Folder> getFolderList() {
        return folderList;
    }

    // 获取本地所有图片链接 并且会获取图片存储的文件夹列表
    public ArrayList<Item> getImageList() {
        ArrayList<Item> data = new ArrayList<>();
        Cursor cursor = getImageCursor(context);
        folderList.clear();
        while (cursor.moveToNext()) {
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            Item item = new Item();
            item.setPath(path);
            data.add(item);
            File parentFile = new File(path).getParentFile();
            if (parentFile == null) {
                continue;
            }
            String parentPath = parentFile.getAbsolutePath();
            if (fileCacheSet.contains(parentPath)) {
                continue;
            } else {
                fileCacheSet.add(parentPath);
                addImageFolder(path, parentFile);
            }
        }

        fileCacheSet.clear();
        Collections.sort(folderList, new ImageFolderComparator());
        addAllImageFolder("所有图片");
        return data;
    }

    // 获取本地的视频列表
    public ArrayList<Item> getVideoList() {
        folderList.clear();
        ArrayList<Item> sysVideoList = new ArrayList<>();
        Cursor cursor = getVideoCursor(context);
        if (cursor == null) {
            return sysVideoList;
        }
        if (cursor.moveToFirst()) {
            do {
                Item info = new Item();
                int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Video.Media._ID));
                Cursor thumbCursor = getVideoCursorById(context, id);
                if (thumbCursor.moveToFirst()) {
                    info.setThumb(thumbCursor.getString(thumbCursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA)));
                }
                info.setPath(cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA)));
                info.setDuration(cursor.getInt(cursor.getColumnIndex(MediaStore.Video.Media.DURATION)));
                sysVideoList.add(info);
                File parentFile = new File(info.getPath()).getParentFile();
                if (parentFile == null) {
                    continue;
                }
                String parentPath = parentFile.getAbsolutePath();
                if (fileCacheSet.contains(parentPath)) {
                    for (int i = 0; i < folderList.size(); i++) {
                        Folder folder = folderList.get(i);
                        if (folder.getDir().equals(parentPath)) {
                            folder.setCount(folder.getCount() + 1);
                        }
                    }
                    continue;
                } else {
                    fileCacheSet.add(parentPath);
                    addVideoFolder(info.getThumb(), parentFile);
                }
            } while (cursor.moveToNext());
        }
        fileCacheSet.clear();
        Collections.sort(folderList, new ImageFolderComparator());
        addAllImageFolder("所有视频");
        folderList.get(0).setCount(sysVideoList.size());
        return sysVideoList;
    }

    /**
     * 获取本地图片的Cursor
     */
    private Cursor getImageCursor(Context context) {
        //查找手机中的jpeg png 格式的图片
        Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        return context.getContentResolver().query(imageUri, null, MediaStore.Images.Media.MIME_TYPE + "=? or " +
                MediaStore.Images.Media.MIME_TYPE + "=?", new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_MODIFIED);
    }

    //获取本地视频的Cursor
    private Cursor getVideoCursor(Context context) {
        String[] mediaColumns = {MediaStore.Video.Media._ID, MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.DURATION};
        return context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                mediaColumns, null, null, null);
    }

    //根据videoId获取cursor
    private Cursor getVideoCursorById(Context context, int videoId) {
        String[] thumbColumns = {MediaStore.Video.Thumbnails.DATA, MediaStore.Video.Thumbnails.VIDEO_ID};
        return context.getContentResolver().query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI,
                thumbColumns, MediaStore.Video.Thumbnails.VIDEO_ID + "=" + videoId, null, null);
    }

    /**
     * 添加图片文件夹
     */
    private void addImageFolder(String path, File parentFile) {
        Folder folder = new Folder();
        folder.setFirstImagePath(path);
        folder.setDir(parentFile.getAbsolutePath());
        folder.setName(parentFile.getName());
        String[] children = getImagesArray(parentFile.getAbsolutePath());
        if (children != null && children.length > 0) {
            folder.setCount(children.length);
        }
        folderList.add(folder);
    }

    //添加视频文件夹
    private void addVideoFolder(String path, File parentFile) {
        Folder folder = new Folder();
        folder.setFirstImagePath(path);
        folder.setDir(parentFile.getAbsolutePath());
        folder.setName(parentFile.getName());
        folder.setCount(1);
        folderList.add(folder);
    }


    /**
     * 获得指定文件夹下的所有图片
     */
    private String[] getImagesArray(String parentPath) {
        File parentFile = new File(parentPath);
        String[] childs = parentFile.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg");
            }
        });
        if (childs == null) {
            childs = new String[]{};
        }
        return childs;
    }

    /**
     * 添加所有图片或者视频文件夹
     */
    private void addAllImageFolder(String name) {
        Folder folder = new Folder();
        folder.setAll(true);
        folder.setName(name);
        if (folderList.size() > 0) {
            folder.setFirstImagePath(folderList.get(0).getFirstImagePath());
        }
        folderList.add(0, folder);
    }

    //获取指定文件夹下的图片列表
    public List<Item> getFolderImages(Folder folder) {
        List<Item> list = new ArrayList<>();
        File parentFile = new File(folder.getDir());
        String[] images = getImagesArray(parentFile.getAbsolutePath());
        for (String image : images) {
            String thumb = parentFile.getAbsolutePath() + "/" + image;
            Item itemEntity = new Item();
            itemEntity.setPath(thumb);
            list.add(itemEntity);
        }
        return list;
    }

    //获得指定文件夹下的视频列表
    public ArrayList<Item> getFolderVideo(ArrayList<Item> list, Folder folder) {
        ArrayList<Item> l = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Item itemEntity = list.get(i);
            if (folder.getDir().equals(new File(itemEntity.getPath()).getParent())) {
                l.add(itemEntity);
            }
        }
        return l;
    }
}
