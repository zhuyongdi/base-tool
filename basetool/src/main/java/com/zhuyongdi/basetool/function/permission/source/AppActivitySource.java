package com.zhuyongdi.basetool.function.permission.source;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by ZhuYongdi on 2019/5/10.
 */
public class AppActivitySource extends Source {

    private Activity mActivity;

    public AppActivitySource(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public Context getContext() {
        return mActivity;
    }

    @Override
    public void startActivity(Intent intent) {
        mActivity.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        mActivity.startActivityForResult(intent, requestCode);
    }

}
