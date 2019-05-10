package com.zhuyongdi.basetool.function.image_selector.handler;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

/**
 * Created by ZhuYongdi on 2019/3/27.
 */
public class CursorHandler {

    /*---------------------------------------------IMAGE---------------------------------------------*/

    //查询图片信息
    private static final String[] PROJECTION_IMAGE = {MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE};
    //查询全部图片
    private static final String QUERY_ARGS_IMAGE = MediaStore.Images.Media.MIME_TYPE + "=? or "
            + MediaStore.Images.Media.MIME_TYPE + "=? or "
            + MediaStore.Images.Media.MIME_TYPE + "=?";
    //指定格式查询
    private static final String[] SELECTION_ARGS_IMAGE = {"image/jpeg", "image/png", "image/jpg"};
    //查询排序规则
    private static final String SORT_ORDER_IMAGE = MediaStore.Images.Media.DATE_MODIFIED + " DESC ";

    /*---------------------------------------------VIDEO---------------------------------------------*/

    //查询视频信息
    private static final String[] PROJECTION_VIDEO = {MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE};
    //查询全部视频
    private static final String QUERY_ARGS_VIDEO = MediaStore.Images.Media.MIME_TYPE + "=? or "
            + MediaStore.Video.Media.MIME_TYPE + "=? or "
            + MediaStore.Video.Media.MIME_TYPE + "=? or "
            + MediaStore.Video.Media.MIME_TYPE + "=? or "
            + MediaStore.Video.Media.MIME_TYPE + "=? or "
            + MediaStore.Video.Media.MIME_TYPE + "=? or "
            + MediaStore.Video.Media.MIME_TYPE + "=? or "
            + MediaStore.Video.Media.MIME_TYPE + "=? or "
            + MediaStore.Video.Media.MIME_TYPE + "=?";
    //查询指定格式
    private static final String[] SELECTION_ARGS_VIDEO = {"video/mp4", "video/3gp", "video/aiv", "video/rmvb", "video/vob", "video/flv",
            "video/mkv", "video/mov", "video/mpg"};
    //查询排序规则
    private static final String SORT_ORDER_VIDEO = MediaStore.Video.Media.DATE_MODIFIED + " DESC ";

    //查询图片和视频信息
    private static final String[] PROJECTION_ALL = {MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE};

    /**
     * 获取图片查询Cursor
     */
    Cursor getImageCursor(Context context) {
        return context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                PROJECTION_IMAGE,
                QUERY_ARGS_IMAGE,
                SELECTION_ARGS_IMAGE,
                SORT_ORDER_IMAGE);
    }

    /**
     * 获取视频查询Cursor
     */
    Cursor getVideoCursor(Context context) {
        return context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                PROJECTION_VIDEO, QUERY_ARGS_VIDEO, SELECTION_ARGS_VIDEO, SORT_ORDER_VIDEO);
    }


}
