package com.zhuyongdi.basetool.function.permission.source;

import android.content.Context;
import android.content.Intent;

/**
 * Created by ZhuYongdi on 2019/5/10.
 */
public class ContextSource extends Source {

    private Context mContext;

    public ContextSource(Context context) {
        this.mContext = context;
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public void startActivity(Intent intent) {
        mContext.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        mContext.startActivity(intent);
    }

}
