package com.zhuyongdi.basetool.function.notification.content;

import android.graphics.Bitmap;

/**
 * Created by ZhuYongdi on 2019/4/16.
 */
public class NotificationBigImageContentWrapper {

    private String title;
    private String titleExpand;
    private String content;
    private String contentExpand;
    private Bitmap bigBitmap;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleExpand() {
        return titleExpand;
    }

    public void setTitleExpand(String titleExpand) {
        this.titleExpand = titleExpand;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentExpand() {
        return contentExpand;
    }

    public void setContentExpand(String contentExpand) {
        this.contentExpand = contentExpand;
    }

    public Bitmap getBigBitmap() {
        return bigBitmap;
    }

    public void setBigBitmap(Bitmap bigBitmap) {
        this.bigBitmap = bigBitmap;
    }
}
