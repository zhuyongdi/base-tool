package com.zhuyongdi.basetool.function.notification.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by ZhuYongdi on 2019/4/16.
 */
public class NotificationService extends Service {

    public static final String NOTIFICATION_DOWNLOAD = "notification_download";
    public static final String NOTIFICATION_MEDIA = "notification_media";
    public static final String NOTIFICATION_MEDIA_STATE = "notification_media_state";
    public static final String NOTIFICATION_MEDIA_PAUSE = "notification_media_pause";
    public static final String NOTIFICATION_MEDIA_PLAY = "notification_media_play";
    private Context context;
    private NotificationManager manager;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null) {
            switch (intent.getAction()) {
                case NOTIFICATION_DOWNLOAD:
                    handleNotificationDownload();
                    break;
                case NOTIFICATION_MEDIA:
                    handleNotificationMedia(intent);
                    break;
            }
        }
        return START_STICKY;
    }

    private void handleNotificationMedia(Intent intent) {
        boolean isPlaying = intent.getBooleanExtra(NOTIFICATION_MEDIA_STATE, false);
//        XNotificationManager.getInstance(context).showNotification(NotificationActivity.NOTIFICATION_ID_MEDIA, NotificationFactory.createMedia(this, !isPlaying));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void handleNotificationDownload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
//                    XNotificationManager.getInstance(context).showNotification(NotificationActivity.NOTIFICATION_ID_DOWNLOAD, NotificationFactory.createDownloader(getApplicationContext(), i));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
