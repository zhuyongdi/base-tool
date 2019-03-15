package com.zhuyongdi.basetool.tool;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * 屏幕工具类
 * Created by ZhuYongdi on 2019/3/14.
 */
public class ScreenUtil {

    //dp转px
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //px转dp
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    //sp转px
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    //px转sp
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    // 获取屏幕宽度
    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.
                WINDOW_SERVICE);
        Display display;
        if (windowManager != null) {
            display = windowManager.getDefaultDisplay();
            Point outPoint = new Point();
            if (Build.VERSION.SDK_INT >= 19) {
                // 可能有虚拟按键的情况
                display.getRealSize(outPoint);
            } else {
                // 不可能有虚拟按键
                display.getSize(outPoint);
            }
            return outPoint.x;
        }
        return 0;
    }

    // 得到屏幕高度
    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.
                WINDOW_SERVICE);
        Display display;
        if (windowManager != null) {
            display = windowManager.getDefaultDisplay();
            Point outPoint = new Point();
            if (Build.VERSION.SDK_INT >= 19) {
                // 可能有虚拟按键的情况
                display.getRealSize(outPoint);
            } else {
                // 不可能有虚拟按键
                display.getSize(outPoint);
            }
            return outPoint.y;
        }
        return 0;
    }

    //获取状态栏高度
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = -1;
        try {
            @SuppressLint("PrivateApi")
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    //设置状态栏字体变黑
    @TargetApi(Build.VERSION_CODES.M)
    public static void setStatusBarLightMode(Activity activity) {
        if (activity.getWindow().getDecorView().getSystemUiVisibility() != (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    //设置状态栏字体颜色变白
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setStatusBarDarkMode(Activity activity) {
        if (activity.getWindow().getDecorView().getSystemUiVisibility() != (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE)) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }

    }

    /**
     * 获取沉浸式状态栏高度
     * SDK<21时不可设置沉浸式,返回0
     * SDK>21时可以设置沉浸式,返回状态栏高度
     */
    public static int getImmersiveBarHeight(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getStatusBarHeight(context);
        }
        return 0;
    }

}
