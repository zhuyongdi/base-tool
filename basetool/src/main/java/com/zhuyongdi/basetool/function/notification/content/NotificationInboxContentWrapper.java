package com.zhuyongdi.basetool.function.notification.content;

import java.util.List;

/**
 * Created by ZhuYongdi on 2019/4/16.
 */
public class NotificationInboxContentWrapper {

    private String title;
    private String titleExpand;
    private String content;
    private String contentExpand;
    private List<String> inboxStringList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleExpand() {
        return titleExpand;
    }

    public void setTitleExpand(String titleExpand) {
        this.titleExpand = titleExpand;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentExpand() {
        return contentExpand;
    }

    public void setContentExpand(String contentExpand) {
        this.contentExpand = contentExpand;
    }

    public List<String> getInboxStringList() {
        return inboxStringList;
    }

    public void setInboxStringList(List<String> inboxStringList) {
        this.inboxStringList = inboxStringList;
    }
}
