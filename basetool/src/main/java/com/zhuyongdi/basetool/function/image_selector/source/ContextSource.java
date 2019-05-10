package com.zhuyongdi.basetool.function.image_selector.source;

import android.content.Context;
import android.content.Intent;

/**
 * Created by ZhuYongdi on 2019/3/26.
 */
public class ContextSource extends Source {

    private Context context;

    public ContextSource(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void startActivity(Intent intent) {
        context.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        context.startActivity(intent);
    }
}
