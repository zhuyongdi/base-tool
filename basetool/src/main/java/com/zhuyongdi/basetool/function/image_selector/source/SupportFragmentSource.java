package com.zhuyongdi.basetool.function.image_selector.source;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by ZhuYongdi on 2019/3/26.
 */
public class SupportFragmentSource extends Source {

    private Fragment fragment;

    public SupportFragmentSource(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public Context getContext() {
        return fragment.getContext();
    }

    @Override
    public void startActivity(Intent intent) {
        fragment.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        fragment.startActivityForResult(intent, requestCode);
    }
}
