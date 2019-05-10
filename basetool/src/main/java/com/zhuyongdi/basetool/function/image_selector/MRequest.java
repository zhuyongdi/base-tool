package com.zhuyongdi.basetool.function.image_selector;

import android.content.Intent;

import com.zhuyongdi.basetool.function.image_selector.activity.ImageSelectActivity;
import com.zhuyongdi.basetool.function.image_selector.bean.MediaType;
import com.zhuyongdi.basetool.function.image_selector.source.Source;

/**
 * Created by ZhuYongdi on 2019/3/26.
 */
public class MRequest implements Request {

    private Source source;
    private int columnNum;
    private int maxNum;
    private MediaType selectType;
    private boolean both;
    private int requestCode;
    private boolean isSingle;
    private boolean bottomPreviewEnable;

    MRequest(Source source) {
        this.source = source;
    }

    @Override
    public Request gridColumnNum(int num) {
        this.columnNum = num;
        return this;
    }

    @Override
    public Request selectMaxNum(int max) {
        this.maxNum = max;
        return this;
    }

    @Override
    public Request selectType(MediaType type) {
        this.selectType = type;
        return this;
    }

    @Override
    public Request requestCode(int code) {
        this.requestCode = code;
        return this;
    }

    @Override
    public Request singleSelect(boolean isSingle) {
        this.isSingle = isSingle;
        return this;
    }

    @Override
    public Request selectBoth(boolean both) {
        this.both = both;
        return this;
    }

    @Override
    public Request bottomPreviewEnable(boolean enable) {
        this.bottomPreviewEnable = enable;
        return this;
    }

    @Override
    public void start() {
        Intent intent = new Intent(source.getContext(), ImageSelectActivity.class);
        intent.putExtra(ImageSelectActivity.COLUMN_NUM, columnNum);
        intent.putExtra(ImageSelectActivity.MAX_NUM, maxNum);
        intent.putExtra(ImageSelectActivity.SELECT_TYPE, selectType);
        intent.putExtra(ImageSelectActivity.BOTH, both);
        intent.putExtra(ImageSelectActivity.IS_SINGLE, isSingle);
        intent.putExtra(ImageSelectActivity.BOTTOM_PREVIEW_ENABLE, bottomPreviewEnable);
        source.startActivityForResult(intent, requestCode);
    }
}
