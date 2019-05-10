package com.zhuyongdi.basetool.function.notification;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;

import com.zhuyongdi.basetool.function.notification.builder.NotificationChannelBuilder;
import com.zhuyongdi.basetool.function.notification.enums.ChannelImportance;
import com.zhuyongdi.basetool.function.notification.enums.LockScreenVisibility;

import java.util.Arrays;

/**
 * Created by ZhuYongdi on 2019/4/16.
 */
@SuppressLint("NewApi")
public class NotificationChannelTools {

    public static final String CHANNEL_ID_MUSIC = "music_id";
    private static final String CHANNEL_NAME_MUSIC = "music_name";
    private static final String CHANNEL_DESC_MUSIC = "music_desc";

    public static final String CHANNEL_ID_DOWNLOAD = "download_id";
    private static final String CHANNEL_NAME_DOWNLOAD = "download_name";
    private static final String CHANNEL_DESC_DOWNLOAD = "download_desc";

    public static final String CHANNEL_ID_BIG_IMAGE = "big_image_id";
    private static final String CHANNEL_NAME_BIG_IMAGE = "big_image_name";
    private static final String CHANNEL_DESC_BIG_IMAGE = "big_image_desc";

    public static final String CHANNEL_ID_BIG_TEXT = "big_text_id";
    private static final String CHANNEL_NAME_BIG_TEXT = "big_text_name";
    private static final String CHANNEL_DESC_BIG_TEXT = "big_text_desc";

    public static final String CHANNEL_ID_INBOX = "inbox_id";
    private static final String CHANNEL_NAME_INBOX = "inbox_name";
    private static final String CHANNEL_DESC_INBOX = "inbox_desc";

    public static final String CHANNEL_ID_MEDIA = "media_id";
    private static final String CHANNEL_NAME_MEDIA = "media_name";
    private static final String CHANNEL_DESC_MEDIA = "media_desc";

    public static final String CHANNEL_ID_MESSAGE = "message_id";
    private static final String CHANNEL_NAME_MESSAGE = "message_name";
    private static final String CHANNEL_DESC_MESSAGE = "message_desc";

    /**
     * 创建所有的通知channel
     */
    public static void createAllNotificationChannels(Context context) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (nm == null) {
            return;
        }
        nm.createNotificationChannels(Arrays.asList(createMusicChannel(),
                createDownloadChannel(), createBigImageChannel(),
                createBigTextChannel(), createInbox(), createMedia()));
    }

    /**
     * 获取音乐播放器通知channel
     * 音乐播放器通知特性:
     * 1.不可删除
     * 2.不震动、不闪光、无声音、没有app圆点
     */
    private static NotificationChannel createMusicChannel() {
        return new NotificationChannelBuilder()
                //设置channel编号
                .setChannelId(CHANNEL_ID_MUSIC)
                //设置channel名称
                .setChannelName(CHANNEL_NAME_MUSIC)
                //设置channel描述
                .setChannelDesc(CHANNEL_DESC_MUSIC)
                //设置通知级别
                .setChannelImportance(ChannelImportance.LOW)
                //设置勿扰模式继续接收通知
                .setBypassDnd(true)
                //关闭屏幕显示
                .setLockScreenVisibility(LockScreenVisibility.SECRET)
                //关闭震动
                .setEnableVibrate(false)
                //关闭灯光
                .setEnableLight(false)
                //关闭声音
                .setEnableSound(false)
                //关闭app通知圆点
                .setShowAppIconNotificationBadge(false)
                //创建
                .build();
    }

    /**
     * 获取下载进度条通知channel
     * 下载进度条通知特性:
     * 1.不可删除
     * 2.不震动、不闪光、无声音、没有app圆点
     */
    private static NotificationChannel createDownloadChannel() {
        return new NotificationChannelBuilder()
                //设置channel编号
                .setChannelId(CHANNEL_ID_DOWNLOAD)
                //设置channel名称
                .setChannelName(CHANNEL_NAME_DOWNLOAD)
                //设置channel描述
                .setChannelDesc(CHANNEL_DESC_DOWNLOAD)
                //设置通知级别
                .setChannelImportance(ChannelImportance.LOW)
                //设置勿扰模式继续接收通知
                .setBypassDnd(true)
                //关闭屏幕显示
                .setLockScreenVisibility(LockScreenVisibility.SECRET)
                //关闭震动
                .setEnableVibrate(false)
                //关闭灯光
                .setEnableLight(false)
                //关闭声音
                .setEnableSound(false)
                //关闭app通知圆点
                .setShowAppIconNotificationBadge(false)
                //创建
                .build();
    }

    /**
     * 获取大图通知channel
     * 大图通知特性:
     * 1.不可删除
     * 2.不震动、不闪光、无声音、没有app圆点
     */
    private static NotificationChannel createBigImageChannel() {
        return new NotificationChannelBuilder()
                //设置channel编号
                .setChannelId(CHANNEL_ID_BIG_IMAGE)
                //设置channel名称
                .setChannelName(CHANNEL_NAME_BIG_IMAGE)
                //设置channel描述
                .setChannelDesc(CHANNEL_DESC_BIG_IMAGE)
                //设置通知级别
                .setChannelImportance(ChannelImportance.LOW)
                //设置勿扰模式继续接收通知
                .setBypassDnd(true)
                //关闭屏幕显示
                .setLockScreenVisibility(LockScreenVisibility.SECRET)
                //关闭震动
                .setEnableVibrate(false)
                //关闭灯光
                .setEnableLight(false)
                //关闭声音
                .setEnableSound(false)
                //关闭app通知圆点
                .setShowAppIconNotificationBadge(false)
                //创建
                .build();
    }

    /**
     * 获取大文本通知channel
     * 大文本通知特性:
     * 1.不可删除
     * 2.不震动、不闪光、无声音、没有app圆点
     */
    private static NotificationChannel createBigTextChannel() {
        return new NotificationChannelBuilder()
                //设置channel编号
                .setChannelId(CHANNEL_ID_BIG_TEXT)
                //设置channel名称
                .setChannelName(CHANNEL_NAME_BIG_TEXT)
                //设置channel描述
                .setChannelDesc(CHANNEL_DESC_BIG_TEXT)
                //设置通知级别
                .setChannelImportance(ChannelImportance.LOW)
                //设置勿扰模式继续接收通知
                .setBypassDnd(true)
                //关闭屏幕显示
                .setLockScreenVisibility(LockScreenVisibility.SECRET)
                //关闭震动
                .setEnableVibrate(false)
                //关闭灯光
                .setEnableLight(false)
                //关闭声音
                .setEnableSound(false)
                //关闭app通知圆点
                .setShowAppIconNotificationBadge(false)
                //创建
                .build();
    }

    /**
     * 获取Inbox通知channel
     * Inbox通知特性:
     * 1.不可删除
     * 2.不震动、不闪光、无声音、没有app圆点
     */
    private static NotificationChannel createInbox() {
        return new NotificationChannelBuilder()
                //设置channel编号
                .setChannelId(CHANNEL_ID_INBOX)
                //设置channel名称
                .setChannelName(CHANNEL_NAME_INBOX)
                //设置channel描述
                .setChannelDesc(CHANNEL_DESC_INBOX)
                //设置通知级别
                .setChannelImportance(ChannelImportance.LOW)
                //设置勿扰模式继续接收通知
                .setBypassDnd(true)
                //关闭屏幕显示
                .setLockScreenVisibility(LockScreenVisibility.SECRET)
                //关闭震动
                .setEnableVibrate(false)
                //关闭灯光
                .setEnableLight(false)
                //关闭声音
                .setEnableSound(false)
                //关闭app通知圆点
                .setShowAppIconNotificationBadge(false)
                //创建
                .build();
    }

    /**
     * 获取媒体通知channel
     * 媒体通知特性:
     * 1.不可删除
     * 2.不震动、不闪光、无声音、没有app圆点
     */
    private static NotificationChannel createMedia() {
        return new NotificationChannelBuilder()
                //设置channel编号
                .setChannelId(CHANNEL_ID_MEDIA)
                //设置channel名称
                .setChannelName(CHANNEL_NAME_MEDIA)
                //设置channel描述
                .setChannelDesc(CHANNEL_DESC_MEDIA)
                //设置通知级别
                .setChannelImportance(ChannelImportance.LOW)
                //设置勿扰模式继续接收通知
                .setBypassDnd(true)
                //关闭屏幕显示
                .setLockScreenVisibility(LockScreenVisibility.SECRET)
                //关闭震动
                .setEnableVibrate(false)
                //关闭灯光
                .setEnableLight(false)
                //关闭声音
                .setEnableSound(false)
                //关闭app通知圆点
                .setShowAppIconNotificationBadge(false)
                //创建
                .build();
    }

    /**
     * 获取Message通知channel
     * Message通知特性:
     * 1.不可删除
     * 2.震动、闪光、提示音，有app圆点
     */
    private static NotificationChannel createMessage() {
        return new NotificationChannelBuilder()
                //设置channel编号
                .setChannelId(CHANNEL_ID_MEDIA)
                //设置channel名称
                .setChannelName(CHANNEL_NAME_MEDIA)
                //设置channel描述
                .setChannelDesc(CHANNEL_DESC_MEDIA)
                //设置通知级别
                .setChannelImportance(ChannelImportance.LOW)
                //设置勿扰模式继续接收通知
                .setBypassDnd(true)
                //关闭屏幕显示
                .setLockScreenVisibility(LockScreenVisibility.SECRET)
                //打开震动
                .setEnableVibrate(true)
                //打开灯光
                .setEnableLight(true)
                //打开声音
                .setEnableSound(true)
                .setSoundUri(Uri.parse("..//aa.mp3"))
                //打开app通知圆点
                .setShowAppIconNotificationBadge(true)
                //创建
                .build();
    }

}
