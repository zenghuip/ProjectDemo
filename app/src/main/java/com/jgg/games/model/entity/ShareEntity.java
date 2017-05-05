package com.jgg.games.model.entity;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class ShareEntity {

    public String mKeyword;
    public int mShowWord;
    public int mIcon;
    public int mIndex;
    public SHARE_TYPE mPlatform;

    public ShareEntity(String var1) {
        this.mKeyword = var1;
        this.mPlatform = SHARE_TYPE.convertToEmun(var1);
    }

    public ShareEntity() {
    }
}
