package com.jgg.games.model.entity;

import android.text.TextUtils;

import com.jgg.games.R;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public enum SHARE_TYPE {
    GOOGLEPLUS,
    GENERIC,
    SINA,
    QZONE,
    QQ,
    WEIXIN,
    WEIXIN_CIRCLE,
    GUILD,
    CIRCLE,
    FRIENDS;

    public static SHARE_TYPE convertToEmun(String var0) {
        if(TextUtils.isEmpty(var0)) {
            return null;
        } else if(var0.equals("wxtimeline")) {
            return WEIXIN_CIRCLE;
        } else if(var0.equals("wxsession")) {
            return WEIXIN;
        } else {
            SHARE_TYPE[] var1 = values();
            SHARE_TYPE[] var2 = var1;
            int var3 = var1.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                SHARE_TYPE var5 = var2[var4];
                if(var5.toString().trim().equals(var0)) {
                    return var5;
                }
            }

            return null;
        }
    }

    public ShareEntity toSnsPlatform() {
        ShareEntity var1 = new ShareEntity();
        if(this.toString().equals("QQ")) {
            var1.mShowWord = R.string.share_qq;
            var1.mIcon = R.drawable.icon_qq;
            var1.mIndex = 0;
            var1.mKeyword = "qq";
        } else if(!this.toString().equals("GENERIC")) {
            if(this.toString().equals("SINA")) {
                var1.mShowWord = R.string.share_sina;
                var1.mIcon = R.drawable.icon_weibo;
                var1.mIndex = 0;
                var1.mKeyword = "sina";
            } else if(this.toString().equals("QZONE")) {
                var1.mShowWord = R.string.share_qzone;
                var1.mIcon = R.drawable.icon_qzone;
                var1.mIndex = 0;
                var1.mKeyword = "qzone";
            } else if(this.toString().equals("WEIXIN")) {
                var1.mShowWord = R.string.share_weixin;
                var1.mIcon = R.drawable.icon_weixin;
                var1.mIndex = 0;
                var1.mKeyword = "wechat";
            } else if(this.toString().equals("WEIXIN_CIRCLE")) {
                var1.mShowWord = R.string.share_weixin_circle;
                var1.mIcon = R.drawable.icon_circl;
                var1.mIndex = 0;
                var1.mKeyword = "wxcircle";
            }else if(this.toString().equals("GUILD")) {
                var1.mShowWord = R.string.share_guild;
                var1.mIcon = R.drawable.icon_guild;
                var1.mIndex = 0;
                var1.mKeyword = "guild";
            }else if(this.toString().equals("CIRCLE")) {
                var1.mShowWord = R.string.share_circle;
                var1.mIcon = R.drawable.icon_circle;
                var1.mIndex = 0;
                var1.mKeyword = "circle";
            }else if(this.toString().equals("FRIENDS")) {
                var1.mShowWord = R.string.share_friends;
                var1.mIcon = R.drawable.icon_friends;
                var1.mIndex = 0;
                var1.mKeyword = "friends";
            }
        }

        var1.mPlatform = this;
        return var1;
    }


    public String toString() {
        return super.toString();
    }
}
