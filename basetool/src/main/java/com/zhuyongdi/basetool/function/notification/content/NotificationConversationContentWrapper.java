package com.zhuyongdi.basetool.function.notification.content;

/**
 * Created by ZhuYongdi on 2019/4/16.
 */
public class NotificationConversationContentWrapper {

    private String message;
    private long timestamp;
    private String sender;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
