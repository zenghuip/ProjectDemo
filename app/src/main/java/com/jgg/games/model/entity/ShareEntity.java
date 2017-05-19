package com.jgg.games.model.entity;

import android.support.annotation.IntDef;

import com.jgg.games.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class ShareEntity {
    //先定义 常量
    public static final int GOOGLEPLUS = 0;
    public static final int GENERIC = 1;
    public static final int SINA = 2;
    public static final int QZONE = 3;
    public static final int QQ = 4;
    public static final int WEIXIN = 5;
    public static final int WEIXIN_CIRCLE = 6;
    public static final int GUILD = 7;
    public static final int CIRCLE = 8;
    public static final int FRIENDS = 9;

    @IntDef({GOOGLEPLUS, GENERIC, SINA, QZONE, QQ, WEIXIN, WEIXIN_CIRCLE,GUILD,CIRCLE,FRIENDS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ShareType{};


    public String mKeyword;
    public int mShowWord;
    public int mIcon;
    public int mIndex;
    public int mPlatform;


    public static ShareEntity toSnsPlatform(int platform) {
        ShareEntity var1 = new ShareEntity();
        if(platform == QQ) {
            var1.mShowWord = R.string.share_qq;
            var1.mIcon = R.drawable.icon_qq;
            var1.mIndex = 0;
            var1.mKeyword = "qq";
        } else if(platform != GENERIC) {
            if(platform == SINA) {
                var1.mShowWord = R.string.share_sina;
                var1.mIcon = R.drawable.icon_weibo;
                var1.mIndex = 0;
                var1.mKeyword = "sina";
            } else if(platform == QZONE) {
                var1.mShowWord = R.string.share_qzone;
                var1.mIcon = R.drawable.icon_qzone;
                var1.mIndex = 0;
                var1.mKeyword = "qzone";
            } else if(platform == WEIXIN) {
                var1.mShowWord = R.string.share_weixin;
                var1.mIcon = R.drawable.icon_weixin;
                var1.mIndex = 0;
                var1.mKeyword = "wechat";
            } else if(platform == WEIXIN_CIRCLE) {
                var1.mShowWord = R.string.share_weixin_circle;
                var1.mIcon = R.drawable.icon_circl;
                var1.mIndex = 0;
                var1.mKeyword = "wxcircle";
            }else if(platform == GUILD) {
                var1.mShowWord = R.string.share_guild;
                var1.mIcon = R.drawable.icon_guild;
                var1.mIndex = 0;
                var1.mKeyword = "guild";
            }else if(platform == CIRCLE) {
                var1.mShowWord = R.string.share_circle;
                var1.mIcon = R.drawable.icon_circle;
                var1.mIndex = 0;
                var1.mKeyword = "circle";
            }else if(platform == FRIENDS) {
                var1.mShowWord = R.string.share_friends;
                var1.mIcon = R.drawable.icon_friends;
                var1.mIndex = 0;
                var1.mKeyword = "friends";
            }
        }

        var1.mPlatform = platform;
        return var1;
    }

}
