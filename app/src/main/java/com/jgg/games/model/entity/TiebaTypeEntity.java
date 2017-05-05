package com.jgg.games.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class TiebaTypeEntity extends GraphQlModel implements Serializable {

    private List<TiebaTypeEntity> postCategories;

    private String name;
    private String image;
    private int fid;

    public List<TiebaTypeEntity> getPostCategories() {
        return postCategories;
    }

    public void setPostCategories(List<TiebaTypeEntity> postCategories) {
        this.postCategories = postCategories;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}