package com.zhuyongdi.basetool.function.image_selector.source;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;

/**
 * Created by ZhuYongdi on 2019/3/26.
 */
public class FragmentSource extends Source {

    private Fragment fragment;

    public FragmentSource(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public Context getContext() {
        return fragment.getActivity();
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
