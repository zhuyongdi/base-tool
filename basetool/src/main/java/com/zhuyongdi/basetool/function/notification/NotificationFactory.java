package com.zhuyongdi.basetool.function.notification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.widget.RemoteViews;

import com.zhuyongdi.basetool.R;
import com.zhuyongdi.basetool.function.notification.content.NotificationBigImageContentWrapper;
import com.zhuyongdi.basetool.function.notification.content.NotificationBigTextContentWrapper;
import com.zhuyongdi.basetool.function.notification.content.NotificationConversationContentWrapper;
import com.zhuyongdi.basetool.function.notification.content.NotificationInboxContentWrapper;
import com.zhuyongdi.basetool.function.notification.content.NotificationMessageContentWrapper;
import com.zhuyongdi.basetool.function.notification.content.NotificationMusicContentWrapper;
import com.zhuyongdi.basetool.function.notification.service.NotificationService;

/**
 * Created by ZhuYongdi on 2019/4/16.
 */
@SuppressLint("NewApi")
public class NotificationFactory {

    /**
     * 创建Message通知
     */
    public static Notification createMessage(Context context, NotificationMessageContentWrapper wrapper) {
        Notification.MessagingStyle messagingStyle = new Notification.MessagingStyle(wrapper.getDisplayName())
                .setConversationTitle(wrapper.getConversationTitle());
        for (NotificationConversationContentWrapper conversation : wrapper.getConversationList()) {
            messagingStyle.addMessage(conversation.getMessage(), conversation.getTimestamp(), conversation.getSender());
        }
        return new Notification.Builder(context, NotificationChannelTools.CHANNEL_ID_MESSAGE)
                //设置优先级
                .setPriority(Notification.PRIORITY_MAX)
                //设置通知左侧的小图标
                .setSmallIcon(R.mipmap.ic_launcher)
                //设置通知标题
                .setContentTitle("Messaging style notification")
                //设置通知内容
                .setContentText("Demo for messaging style notification !")
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                //设置显示通知时间
                .setShowWhen(true)
                //设置信箱样式通知
                .setStyle(messagingStyle)
                //创建通知
                .build();
    }

    /**
     * 创建Media通知
     */
    public static Notification createMedia(Context context, boolean isPlaying) {
        Icon iconPlay = Icon.createWithResource(context, R.mipmap.icon_notification_music_play);
        Icon iconPause = Icon.createWithResource(context, R.mipmap.icon_notification_music_pause);
        Icon iconNext = Icon.createWithResource(context, R.mipmap.icon_notification_music_next);
        Icon iconDelete = Icon.createWithResource(context, R.mipmap.icon_notification_music_delete);
        Icon iconPlayOrPause = isPlaying ? iconPause : iconPlay;
        String textPlayOrPause = isPlaying ? "PAUSE" : "PLAY";
        String textDelete = "DELETE";
        String textNext = "NEXT";
        Intent playOrPauseIntent = new Intent(context, NotificationService.class);
        playOrPauseIntent.setAction(NotificationService.NOTIFICATION_MEDIA);
        playOrPauseIntent.putExtra(NotificationService.NOTIFICATION_MEDIA_STATE, isPlaying);
        PendingIntent playOrPausePendingIntent = PendingIntent.getService(context, 0, playOrPauseIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Action actionPlay = new Notification.Action.Builder(iconPlayOrPause, textPlayOrPause, playOrPausePendingIntent).build();

        //创建下一首按钮
        Notification.Action actionNext = new Notification.Action.Builder(iconNext, textNext, null).build();
        //创建删除按钮
        Notification.Action actionDelete = new Notification.Action.Builder(iconDelete, textDelete, null).build();

        Notification.MediaStyle mediaStyle = new Notification.MediaStyle()
                //最多三个Action
                .setShowActionsInCompactView(0, 1, 2);

        return new Notification.Builder(context, NotificationChannelTools.CHANNEL_ID_MEDIA)
                //设置优先级
                .setPriority(Notification.PRIORITY_MAX)
                //设置通知左侧的小图标
                .setSmallIcon(R.mipmap.ic_launcher)
                //设置通知标题
                .setContentTitle("Media style notification")
                //设置通知内容
                .setContentText("Demo for media style notification !")
                //设置通知不可删除
                .setOngoing(true)
                //设置显示通知时间
                .setShowWhen(true)
                //设置Action按钮
                .setActions(actionPlay, actionNext, actionDelete)
                //设置信箱样式通知
                .setStyle(mediaStyle)
                //创建通知
                .build();
    }

    /**
     * 创建inbox通知
     */
    public static Notification createInbox(Context context, NotificationInboxContentWrapper wrapper) {
        Notification.InboxStyle inboxStyle = new Notification.InboxStyle()
                .setBigContentTitle(wrapper == null ? "" : wrapper.getTitleExpand())
                .setSummaryText(wrapper == null ? "" : wrapper.getContentExpand());
        //最多可添加6行
        if (wrapper != null && wrapper.getInboxStringList() != null && !wrapper.getInboxStringList().isEmpty()) {
            for (String line : wrapper.getInboxStringList()) {
                inboxStyle.addLine(line);
            }
        }
        return new Notification.Builder(context, NotificationChannelTools.CHANNEL_ID_INBOX)
                //设置优先级
                .setPriority(Notification.PRIORITY_MAX)
                //设置通知左侧的小图标
                .setSmallIcon(R.mipmap.ic_launcher)
                //设置通知标题
                .setContentTitle(wrapper == null ? "" : wrapper.getTitle())
                //设置通知内容
                .setContentText(wrapper == null ? "" : wrapper.getContent())
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                //设置显示通知时间
                .setShowWhen(true)
                //设置信箱样式通知
                .setStyle(inboxStyle)
                //创建通知
                .build();
    }

    /**
     * 创建大文本通知
     */
    public static Notification createBigText(Context context, NotificationBigTextContentWrapper wrapper) {
        Notification.BigTextStyle bigTextStyle = new Notification.BigTextStyle()
                .setBigContentTitle(wrapper.getTitleExpand())
//                .setSummaryText(wrapper.getContentExpand()) //右边的文字
                .bigText(wrapper.getBigText());
        return new Notification.Builder(context, NotificationChannelTools.CHANNEL_ID_BIG_TEXT)
                //设置优先级
                .setPriority(Notification.PRIORITY_MAX)
                //设置通知左侧的小图标
                .setSmallIcon(R.mipmap.ic_launcher)
                //设置通知标题
                .setContentTitle(wrapper.getTitle())
                //设置通知内容
                .setContentText(wrapper.getContent())
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                //设置显示通知时间
                .setShowWhen(true)
                //设置大文字样式通知
                .setStyle(bigTextStyle)
                //创建通知
                .build();
    }

    /**
     * 创建大图通知
     */
    public static Notification createBigImage(Context context, NotificationBigImageContentWrapper wrapper) {
        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle()
                .setBigContentTitle(wrapper.getTitleExpand())
                .setSummaryText(wrapper.getContentExpand())
                .bigPicture(wrapper.getBigBitmap());
//                .bigLargeIcon(wrapper.getBigBitmap()); //右边的小图
        return new Notification.Builder(context, NotificationChannelTools.CHANNEL_ID_BIG_IMAGE)
                //设置优先级
                .setPriority(Notification.PRIORITY_MAX)
                //设置通知左侧的小图标
                .setSmallIcon(R.mipmap.ic_launcher)
                //设置通知标题
                .setContentTitle(wrapper.getTitle())
                //设置通知内容
                .setContentText(wrapper.getContent())
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                //设置显示通知时间
                .setShowWhen(true)
                //设置大视图样式通知
                .setStyle(bigPictureStyle)
                //创建通知
                .build();
    }

    /**
     * 创建下载进度条通知
     */
    public static Notification createDownloader(Context context, int progress) {
        return new Notification.Builder(context, NotificationChannelTools.CHANNEL_ID_DOWNLOAD)
                //设置优先级
                .setPriority(Notification.PRIORITY_MAX)
                //设置通知左侧的小图标
                .setSmallIcon(R.mipmap.ic_launcher)
                //设置通知标题
                .setContentTitle(progress == 100 ? "下载完成" : "下载中...")
                //设置通知内容
                .setContentText(String.valueOf(progress) + "%")
                //设置通知不可删除
                .setOngoing(true)
                //设置显示通知时间
                .setShowWhen(true)
                //设置下载进度
                .setProgress(100, progress, false)
                //创建通知
                .build();
    }

    /**
     * 创建音乐播放器通知
     */
    public static Notification createMusicPlayer(Context context, NotificationMusicContentWrapper wrapper, boolean isLoved, boolean isPlaying) {

        RemoteViews smallView = new RemoteViews(context.getPackageName(), R.layout.layout_notification_music_player_small);
        smallView.setImageViewBitmap(R.id.iv_content, wrapper.getMusicBitmap());
        smallView.setTextViewText(R.id.tv_title, wrapper.getMusicTitle());
        smallView.setTextViewText(R.id.tv_summery, wrapper.getMusicAuthor() + " - " + wrapper.getMusicAlbum());
        smallView.setImageViewBitmap(R.id.iv_play_or_pause, BitmapFactory.decodeResource(context.getResources(),
                isPlaying ? R.mipmap.icon_notification_music_pause : R.mipmap.icon_notification_music_play));

        RemoteViews bigView = new RemoteViews(context.getPackageName(), R.layout.layout_notification_music_player_big);
        bigView.setImageViewBitmap(R.id.iv_content_big, wrapper.getMusicBitmap());
        bigView.setTextViewText(R.id.tv_title_big, wrapper.getMusicTitle());
        bigView.setTextViewText(R.id.tv_summery_big, wrapper.getMusicAuthor() + " - " + wrapper.getMusicAlbum());
        bigView.setImageViewBitmap(R.id.iv_love_big, BitmapFactory.decodeResource(context.getResources(),
                isLoved ? R.mipmap.icon_notification_music_loved : R.mipmap.icon_notification_music_love));
        bigView.setImageViewBitmap(R.id.iv_play_or_pause_big, BitmapFactory.decodeResource(context.getResources(),
                isPlaying ? R.mipmap.icon_notification_music_pause : R.mipmap.icon_notification_music_play));
        bigView.setTextViewText(R.id.tv_divider, "");
        return new Notification.Builder(context, NotificationChannelTools.CHANNEL_ID_MUSIC)
                //设置优先级,设置了此优先级可以出现在QQ音乐播放器通知的上方
                .setPriority(Notification.PRIORITY_MAX)
                //设置状态栏小图标
                .setSmallIcon(R.mipmap.ic_launcher)
                //设置通知标题
                .setContentTitle("Custom notification")
                //设置通知内容
                .setContentText("Demo for custom notification !")
                //设置不可删除
                .setOngoing(true)
                //设置自定义小视图
                .setCustomContentView(smallView)
                //设置自定义大视图
                .setCustomBigContentView(bigView)
                //创建通知
                .build();
    }

    /**
     * 创建网易云音乐通知
     */
    public static Notification createNeteaseCloudMusic(Context context, NotificationMusicContentWrapper wrapper, boolean isLoved, boolean isPlaying) {

        RemoteViews smallView = new RemoteViews(context.getPackageName(), R.layout.layout_notification_music_player_small);
        smallView.setImageViewBitmap(R.id.iv_content, wrapper.getMusicBitmap());
        smallView.setTextViewText(R.id.tv_title, wrapper.getMusicTitle());
        smallView.setTextViewText(R.id.tv_summery, wrapper.getMusicAuthor() + " - " + wrapper.getMusicAlbum());
        smallView.setImageViewBitmap(R.id.iv_play_or_pause, BitmapFactory.decodeResource(context.getResources(),
                isPlaying ? R.mipmap.icon_notification_music_pause : R.mipmap.icon_notification_music_play));

        RemoteViews bigView = new RemoteViews(context.getPackageName(), R.layout.layout_notification_music_player_big);
        bigView.setImageViewBitmap(R.id.iv_content_big, wrapper.getMusicBitmap());
        bigView.setTextViewText(R.id.tv_title_big, wrapper.getMusicTitle());
        bigView.setTextViewText(R.id.tv_summery_big, wrapper.getMusicAuthor() + " - " + wrapper.getMusicAlbum());
        bigView.setImageViewBitmap(R.id.iv_love_big, BitmapFactory.decodeResource(context.getResources(),
                isLoved ? R.mipmap.icon_notification_music_loved : R.mipmap.icon_notification_music_love));
        bigView.setImageViewBitmap(R.id.iv_play_or_pause_big, BitmapFactory.decodeResource(context.getResources(),
                isPlaying ? R.mipmap.icon_notification_music_pause : R.mipmap.icon_notification_music_play));
        bigView.setTextViewText(R.id.tv_divider, "");
        return new Notification.Builder(context, NotificationChannelTools.CHANNEL_ID_MUSIC)
                //设置优先级,设置了此优先级可以出现在QQ音乐播放器通知的上方
                .setPriority(Notification.PRIORITY_MAX)
                //设置状态栏小图标
                .setSmallIcon(R.mipmap.ic_launcher)
                //设置通知标题
                .setContentTitle("Custom notification")
                //设置通知内容
                .setContentText("Demo for custom notification !")
                //设置不可删除
                .setOngoing(true)
                //设置自定义小视图
                .setCustomContentView(smallView)
                //设置自定义大视图
                .setCustomBigContentView(bigView)
                //创建通知
                .build();
    }
}
