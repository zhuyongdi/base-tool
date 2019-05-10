package com.zhuyongdi.basetool.function.notification.enums;

import android.app.Notification;

/**
 * Created by ZhuYongdi on 2019/4/16.
 */
public enum LockScreenVisibility {

    PUBLIC(Notification.VISIBILITY_PUBLIC),
    PRIVATE(Notification.VISIBILITY_PRIVATE),
    SECRET(Notification.VISIBILITY_SECRET);

    final int value;

    LockScreenVisibility(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
