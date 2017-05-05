package com.jgg.games.model.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31 0031.
 * 首页轮播
 */

public class IndexBannerEntity extends GraphQlModel {
    private List<IndexBannerEntity> appMenuItems;

    private String name;
    /**
     * url : http://o99jmtwm2.bkt.clouddn.com/5ca33f44744b7de4b4cfd54cf2327a2f.jpg
     */

    private ImageEntry image;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageEntry getImage() {
        return image;
    }

    public void setImage(ImageEntry image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<IndexBannerEntity> getAppMenuItems() {
        return appMenuItems;
    }

    public void setAppMenuItems(List<IndexBannerEntity> appMenuItems) {
        this.appMenuItems = appMenuItems;
    }

    public class ImageEntry {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
