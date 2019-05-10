package com.zhuyongdi.basetool.function.notification.content;

import java.util.List;

/**
 * Created by ZhuYongdi on 2019/4/16.
 */
public class NotificationMessageContentWrapper {

    private String displayName;
    private String conversationTitle;
    private List<NotificationConversationContentWrapper> conversationList;
    private String title;
    private String content;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getConversationTitle() {
        return conversationTitle;
    }

    public void setConversationTitle(String conversationTitle) {
        this.conversationTitle = conversationTitle;
    }

    public List<NotificationConversationContentWrapper> getConversationList() {
        return conversationList;
    }

    public void setConversationList(List<NotificationConversationContentWrapper> conversationList) {
        this.conversationList = conversationList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
