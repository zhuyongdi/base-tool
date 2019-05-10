package com.zhuyongdi.basetool.function.notification.builder;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.media.AudioAttributes;
import android.net.Uri;

import com.zhuyongdi.basetool.function.notification.enums.ChannelImportance;
import com.zhuyongdi.basetool.function.notification.enums.LockScreenVisibility;

/**
 * Created by ZhuYongdi on 2019/4/16.
 */
public class NotificationChannelBuilder {

    private String channelId;
    private String channelName;
    private String channelDesc;
    private String channelGroupId;
    private ChannelImportance channelImportance;
    private boolean isShowAppIconNotificationBadge; //是否在app右上角显示通知圆点
    //        private long appIconNotificationBadgeTimeout; //app右上角通知圆点消失时间
    private boolean isBypassDnd; //是否覆盖勿扰模式,即开启勿扰模式时允许继续接收通知(无法生效,只能由用户自己在系统设置中设置)
    private LockScreenVisibility lockScreenVisibility; //是否在锁定屏幕上显示通知(无法生效,只能由用户自己在系统设置中设置)
    private boolean enableSound; //是否发出通知提示音,channelImportance在DEFAULT及以上有效
    private Uri soundUri; //自定义通知提示音,null为默认提示音
    private boolean enableLight; //是否显示指示灯
    private boolean enableVibrate; //是否震动
    private long[] vibratePattern = {100, 200, 300, 400, 500, 400, 300, 200, 400}; //震动频率

    public NotificationChannelBuilder setChannelId(String channelId) {
        this.channelId = channelId;
        return this;
    }

    public NotificationChannelBuilder setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }

    public NotificationChannelBuilder setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc;
        return this;
    }

    public NotificationChannelBuilder setChannelGroupId(String channelGroupId) {
        this.channelGroupId = channelGroupId;
        return this;
    }

    public NotificationChannelBuilder setChannelImportance(ChannelImportance channelImportance) {
        this.channelImportance = channelImportance;
        return this;
    }

    public NotificationChannelBuilder setShowAppIconNotificationBadge(boolean showAppIconNotificationBadge) {
        isShowAppIconNotificationBadge = showAppIconNotificationBadge;
        return this;
    }

    public NotificationChannelBuilder setBypassDnd(boolean bypassDnd) {
        isBypassDnd = bypassDnd;
        return this;
    }

    public NotificationChannelBuilder setLockScreenVisibility(LockScreenVisibility lockScreenVisibility) {
        this.lockScreenVisibility = lockScreenVisibility;
        return this;
    }

    public NotificationChannelBuilder setEnableSound(boolean enableSound) {
        this.enableSound = enableSound;
        return this;
    }

    public NotificationChannelBuilder setSoundUri(Uri soundUri) {
        this.soundUri = soundUri;
        return this;
    }

    public NotificationChannelBuilder setEnableLight(boolean enableLight) {
        this.enableLight = enableLight;
        return this;
    }

    public NotificationChannelBuilder setEnableVibrate(boolean enableVibrate) {
        this.enableVibrate = enableVibrate;
        return this;
    }

    public NotificationChannelBuilder setVibratePattern(long[] vibratePattern) {
        this.vibratePattern = vibratePattern;
        return this;
    }

    @SuppressLint("NewApi")
    public NotificationChannel build() {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, channelImportance.getValue());
        //设置渠道描述
        channel.setDescription(channelDesc);
        //设置app通知圆点
        channel.setShowBadge(isShowAppIconNotificationBadge);
        //设置是否在开启了勿扰模式后继续接收通知
        channel.setBypassDnd(isBypassDnd);
        //设置是否在锁屏模式中显示
        channel.setLockscreenVisibility(lockScreenVisibility.getValue());
        //设置渠道组
        channel.setGroup(channelGroupId);
        //设置通知提示音
        if (enableSound) {
            channel.setSound(soundUri, new AudioAttributes.Builder().build());
        } else {
            channel.setSound(Uri.EMPTY, null);
        }
        //设置通知灯光
        channel.enableLights(enableLight);
        //设置通知震动
        if (enableVibrate) {
            channel.enableVibration(true);
            channel.setVibrationPattern(vibratePattern);
        } else {
            channel.enableVibration(false);
        }
        return channel;
    }

}
