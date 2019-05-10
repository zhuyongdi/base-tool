package com.zhuyongdi.basetool.function.notification.content;

import android.graphics.Bitmap;

/**
 * Created by ZhuYongdi on 2019/4/16.
 */
public class NotificationMusicContentWrapper {

    //音乐背景图
    private Bitmap musicBitmap;
    //音乐标题
    private String musicTitle;
    //音乐作者
    private String musicAuthor;
    //音乐专辑
    private String musicAlbum;

    public Bitmap getMusicBitmap() {
        return musicBitmap;
    }

    public void setMusicBitmap(Bitmap musicBitmap) {
        this.musicBitmap = musicBitmap;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public String getMusicAuthor() {
        return musicAuthor;
    }

    public void setMusicAuthor(String musicAuthor) {
        this.musicAuthor = musicAuthor;
    }

    public String getMusicAlbum() {
        return musicAlbum;
    }

    public void setMusicAlbum(String musicAlbum) {
        this.musicAlbum = musicAlbum;
    }
}
