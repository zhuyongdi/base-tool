package com.zhuyongdi.basetool.function.image_selector.source;

import android.content.Context;
import android.content.Intent;

/**
 * Created by ZhuYongdi on 2019/3/26.
 */
public abstract class Source {

    public abstract Context getContext();

    public abstract void startActivity(Intent intent);

    public abstract void startActivityForResult(Intent intent, int requestCode);

}
