package com.zhuyongdi.basetool.function.image_selector.bean;

/**
 * Created by ZhuYongdi on 2019/3/27.
 */
public class MediaBean {

    private MediaType type;

    //通用
    private String name;
    private long size;
    private long lastModifyTime;
    private String logo;
    private String path;
    private boolean isSelect;

    //视频
    private long videoTimeLength;

    public MediaBean(MediaType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVideoTimeLength() {
        return videoTimeLength;
    }

    public void setVideoTimeLength(long videoTimeLength) {
        this.videoTimeLength = videoTimeLength;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getPath() {
        return path;
    }

    public MediaType getType() {
        return type;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
