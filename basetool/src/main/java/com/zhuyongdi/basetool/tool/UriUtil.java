package com.zhuyongdi.basetool.tool;

import android.content.Context;
import android.net.Uri;

/**
 * Created by ZhuYongdi on 2019/5/6.
 */
public class UriUtil {

    public static Uri readRaw(Context context, String fileName) {
        return Uri.parse("android.resource://" + context.getPackageName() + "/" + fileName);
    }

}
