package com.zhuyongdi.basetool.function.notification.enums;

import android.app.NotificationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by ZhuYongdi on 2019/4/16.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public enum ChannelImportance {

    //关闭通知
    NONE(NotificationManager.IMPORTANCE_NONE),
    //开启通知，不会弹出，不发出提示音，状态栏中无显示
    MIN(NotificationManager.IMPORTANCE_MIN),
    //开启通知，不会弹出，不发出提示音，状态栏中显示
    LOW(NotificationManager.IMPORTANCE_LOW),
    //开启通知，不会弹出，发出提示音，状态栏中显示
    DEFAULT(NotificationManager.IMPORTANCE_DEFAULT),
    //开启通知，会弹出，发出提示音，状态栏中显示
    HIGH(NotificationManager.IMPORTANCE_HIGH),
    //开启通知，会弹出，发出提示音，状态栏中显示
    MAX(NotificationManager.IMPORTANCE_MAX);

    final int value;

    ChannelImportance(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
