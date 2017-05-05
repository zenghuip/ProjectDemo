package com.jgg.games.model.entity;

/**
 * Created by Administrator on 2017/4/7 0007.
 */

public class IconEntity extends GraphQlModel {
    private String url;
    private String thumb_url;

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
