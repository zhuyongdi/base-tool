package com.zhuyongdi.basetool.function.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

/**
 * Created by ZhuYongdi on 2019/4/16.
 */
public class XNotificationManager {

    private NotificationManager manager;

    public static XNotificationManager getInstance(Context context) {
        return ClassHolder.createInstance(context);
    }

    private XNotificationManager(Context applicationContext) {
        manager = (NotificationManager) applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    private static final class ClassHolder {
        private static XNotificationManager createInstance(Context context) {
            return new XNotificationManager(context);
        }
    }

    public void showNotification(int notificationId, Notification notification) {
        manager.notify(notificationId, notification);
    }

    public void clearNotification(int notificationId) {
        manager.cancel(notificationId);
    }

    public void clearAllNotification() {
        manager.cancelAll();
    }

}
