package com.zhuyongdi.basetool.widget.x_gridview;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by ZhuYongdi on 2019/4/3.
 */
public abstract class XNineGridViewAdapter {

    public abstract void onImageItemClick(Context context, XNineGridView gridView, int index, ArrayList<ImageInfo> imageInfo);

    protected Context mContext;
    private ArrayList<ImageInfo> mImageInfoList;

    public XNineGridViewAdapter(Context context) {
        this.mContext = context;
    }

    public XNineGridViewAdapter(Context context, ArrayList<ImageInfo> imageInfoList) {
        this.mContext = context;
        this.mImageInfoList = imageInfoList;
    }

    public ArrayList<ImageInfo> getImageInfo() {
        return this.mImageInfoList;
    }

    public void setImageInfoList(ArrayList<ImageInfo> imageInfoList) {
        this.mImageInfoList = imageInfoList;
    }

}
