package com.zhuyongdi.basetool.function.image_selector.handler;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.zhuyongdi.basetool.function.image_selector.bean.MediaBean;
import com.zhuyongdi.basetool.function.image_selector.bean.MediaType;
import com.zhuyongdi.basetool.function.image_selector.comparator.FileModifyTimeComparator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ZhuYongdi on 2019/3/27.
 */
public class LocalDataHandler {

    private Context context;
    private static LocalDataHandler instance;

    private LocalDataHandler(Context context) {
        this.context = context;
    }

    public static LocalDataHandler getInstance(Context context) {
        if (instance == null) {
            synchronized (LocalDataTool.class) {
                if (instance == null) {
                    instance = new LocalDataHandler(context);
                }
            }
        }
        return instance;
    }

    private CursorHandler cursorHandler = new CursorHandler();

    /**
     * 获取所有图片,按修改时间排序
     */
    public ArrayList<MediaBean> getImages() {
        ArrayList<MediaBean> result = new ArrayList<>();
        Cursor cursor = cursorHandler.getImageCursor(context);
        if (cursor == null) {
            return result;
        }
        while (cursor.moveToNext()) {
            MediaBean bean = new MediaBean(MediaType.IMAGE);
            //图片名称
            String imageName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
            //图片大小
            long imageSize = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE));
            //图片数据
            byte[] imageData = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            String path = new String(imageData, 0, imageData.length - 1);
            bean.setName(imageName);
            bean.setSize(imageSize);
            bean.setPath(path);
            bean.setLogo(path);
            bean.setLastModifyTime(new File(path).lastModified());
            result.add(bean);
        }
        cursor.close();
        Collections.sort(result, new FileModifyTimeComparator());
        return result;
    }

    /**
     * 获取所有视频,按修改时间排序
     */
    public ArrayList<MediaBean> getVideos() {
        ArrayList<MediaBean> result = new ArrayList<>();
        Cursor cursor = cursorHandler.getVideoCursor(context);
        if (cursor == null) {
            return result;
        }
        while (cursor.moveToNext()) {
            MediaBean bean = new MediaBean(MediaType.VIDEO);
            //视频名称
            String videoName = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
            //视频大小
            long videoSize = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
            //视频时长
            long videoTimeLength = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
            //视频路径
            String videoPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
            bean.setName(videoName);
            bean.setSize(videoSize);
            bean.setVideoTimeLength(videoTimeLength);
            bean.setPath(videoPath);
            bean.setLastModifyTime(new File(videoPath).lastModified());
            bean.setLogo(videoPath);
            result.add(bean);
        }
        cursor.close();
        Collections.sort(result, new FileModifyTimeComparator());
        return result;
    }

    /**
     * 获取图片和视频,按修改时间排序
     */
    public ArrayList<MediaBean> getImagesAndVideos() {
        ArrayList<MediaBean> result = new ArrayList<>();
        result.addAll(getImages());
        result.addAll(getVideos());
        Collections.sort(result, new FileModifyTimeComparator());
        return result;
    }

    //检查当前选择是否与已选择的不同
    public boolean checkIsNotSameSelect(ArrayList<MediaBean> list, int position) {
        for (MediaBean bean : list) {
            if (bean.isSelect() && list.get(position).getType() != bean.getType()) {
                return true;
            }
        }
        return false;
    }

}
