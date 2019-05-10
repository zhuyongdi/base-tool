package com.zhuyongdi.basetool.tool;

import android.os.Environment;

/**
 * Created by ZhuYongdi on 2019/3/26.
 */
public class ApplicationUtil {

    //检查手机是否有外部存储
    public static boolean hasExternalStorage() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
