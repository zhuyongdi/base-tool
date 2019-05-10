package com.zhuyongdi.basetool.function.permission.source;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;

/**
 * Created by ZhuYongdi on 2019/5/10.
 */
public class FragmentSource extends Source {

    private Fragment mFragment;

    public FragmentSource(Fragment fragment) {
        this.mFragment = fragment;
    }

    @Override
    public Context getContext() {
        return mFragment.getActivity();
    }

    @Override
    public void startActivity(Intent intent) {
        mFragment.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        mFragment.startActivityForResult(intent, requestCode);
    }

}
