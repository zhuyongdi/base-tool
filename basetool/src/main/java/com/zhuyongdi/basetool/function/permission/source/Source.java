package com.zhuyongdi.basetool.function.permission.source;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import java.lang.reflect.Method;

/**
 * Created by ZhuYongdi on 2019/5/10.
 */
public abstract class Source {

    public abstract Context getContext();

    public abstract void startActivity(Intent intent);

    public abstract void startActivityForResult(Intent intent, int requestCode);

    /**
     * Show permissions rationale?
     */
    public final boolean isShowRationalePermission(String permission) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return false;

        PackageManager packageManager = getContext().getPackageManager();
        Class<?> pkManagerClass = packageManager.getClass();
        try {
            Method method = pkManagerClass.getMethod("shouldShowRequestPermissionRationale", String.class);
            if (!method.isAccessible()) method.setAccessible(true);
            return (boolean) method.invoke(packageManager, permission);
        } catch (Exception ignored) {
            return false;
        }
    }

}
