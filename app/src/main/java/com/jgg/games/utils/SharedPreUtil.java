package com.jgg.games.utils;

import com.jgg.games.base.CosApp;
import com.jgg.games.model.entity.UserEntity;
import com.jgg.rxretrofitlibrary.retrofit_rx.database.DatabaseManager;
import com.umeng.socialize.UMShareAPI;

import java.util.List;


/**
 * Created by Administrator on 2017/4/12 0012.
 * 本地缓存数据
 */

public class SharedPreUtil {

    private static final String USER = "user"; // 当前用户
    private static final String ISLOGIN = "islogin"; // 当前用户是否登录
    private static final String TOKEN = "token"; //
    private static final String OPEN_ID = "openid"; //
    private static final String UID = "uid"; //
    private static final String SID = "sid"; //
    private static final String PHONE = "phone"; // 手机号
    public static final String WEIXIN_OR_QQ_NAME = "weixin_or_qq_name"; // 微信或者qq昵称
    public static final String WEIXIN_OR_QQ_HEAD = "weixin_or_qq_head"; // 微信或者qq头像


    public static void clearParams() {
        setToken("");
        setIslogin(false);
        setWeixinOrQqName("");
        setWeixinOrQqHead("");
    }

    public static boolean getIslogin() {
        return Utils.getSpUtils().getBoolean(ISLOGIN, false);
    }

    public static void setIslogin(boolean islogin) {
        Utils.getSpUtils().put(ISLOGIN, islogin);
    }

    public static String getToken() {
        return Utils.getSpUtils().getString(TOKEN);
    }

    public static void setToken(String token) {
        Utils.getSpUtils().put(TOKEN, token);
    }

    public static long getUid() {
        return Utils.getSpUtils().getLong(UID);
    }

    public static void setUid(long uid) {
        Utils.getSpUtils().put(UID, uid);
    }

    public static String getSid() {
        return Utils.getSpUtils().getString(SID);
    }

    public static void setSid(String sid) {
        Utils.getSpUtils().put(SID, sid);
    }

    public static String getPhone() {
        return Utils.getSpUtils().getString(PHONE);
    }

    public static void setPhone(String phone) {
        Utils.getSpUtils().put(PHONE, phone);
    }

    public static String getOpenId() {
        return Utils.getSpUtils().getString(OPEN_ID);
    }

    public static void setOpenId(String openId) {
        Utils.getSpUtils().put(OPEN_ID, openId);
    }

    public static String getWeixinOrQqName() {
        return Utils.getSpUtils().getString(WEIXIN_OR_QQ_NAME);
    }

    public static void setWeixinOrQqName(String name) {
        Utils.getSpUtils().put(WEIXIN_OR_QQ_NAME, name);
    }

    public static String getWeixinOrQqHead() {
        return Utils.getSpUtils().getString(WEIXIN_OR_QQ_HEAD);
    }

    public static void setWeixinOrQqHead(String url) {
        Utils.getSpUtils().put(WEIXIN_OR_QQ_HEAD, url);
    }


    // 获取用户
    public static UserEntity getUser() {
        List<UserEntity> list = DatabaseManager.getInstance().getQueryByWhere(UserEntity.class, UserEntity.UID, String.valueOf(SharedPreUtil.getUid()));
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


}
