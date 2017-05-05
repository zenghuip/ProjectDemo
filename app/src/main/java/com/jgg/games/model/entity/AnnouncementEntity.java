package com.jgg.games.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31 0031.
 * 公会公告
 */

public class AnnouncementEntity extends GraphQlModel {
    private List<AnnouncementEntity> announcements;
    private String title;
    private long time;
    private String content;
    private int order;

    public List<AnnouncementEntity> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<AnnouncementEntity> announcements) {
        this.announcements = announcements;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
