package com.jgg.games.model.manager;

import android.app.Activity;
import android.content.Context;

import com.jgg.games.http.HttpRequest;
import com.jgg.games.http.base.BaseGraphqlRequest;
import com.jgg.games.http.base.CommonCallback;
import com.jgg.games.http.callback.GetUserInfoCallback;
import com.jgg.games.http.callback.LoginGetUserCallback;
import com.jgg.games.model.entity.BaseCodeEntity;
import com.jgg.games.model.entity.QnMsgEntity;
import com.jgg.games.utils.SharedPreUtil;

/**
 * Created by dingdongdong on 2017/3/28.
 * 用户管理类
 */
public class UserManager{

    private static UserManager instance = null;
    private Context appCompatActivity;

    private UserManager(Context appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public synchronized static UserManager getInstance(Context appCompatActivity) {
        if (instance == null) {
            instance = new UserManager(appCompatActivity);
        }
        return instance;
    }

    /**
     * 验证token
     * @param oldToken
     * @param callback
     */
    public void checkToken(String oldToken,CommonCallback callback) {
        new HttpRequest(appCompatActivity).postUrl(false,checkToken(oldToken),callback,BaseCodeEntity.class);
    }

    /*
    * 获取验证码
    */
    public void getCode(String phone, CommonCallback callback) {
        new HttpRequest(appCompatActivity).postUrl(true,getCode(phone),callback, BaseCodeEntity.class);
    }

    /**
     *  手机验证码登录
     * @param phone
     * @param code
     */
    public void loginByPhone(String phone, String code) {
        new HttpRequest(appCompatActivity).postUrl(true,loginByPhoneParam(phone,code),new LoginGetUserCallback((Activity) appCompatActivity), BaseCodeEntity.class);
    }

    public void loginByOpenId(String openId) {
        new HttpRequest(appCompatActivity).postUrl(true, loginByOpenid(openId), new LoginGetUserCallback((Activity) appCompatActivity), BaseCodeEntity.class);
    }

    /**
     * 获取用户信息
     */
    public void getUserInfo() {
        new HttpRequest(appCompatActivity).postUrl(false,getUserInfo(SharedPreUtil.getToken()),new GetUserInfoCallback(), BaseCodeEntity.class);
    }

    /**
     * 更新用户资料
     * @param name 用户名
     * @param headUrl 头像
     * @param sex 性别
     * @param birth 生日
     * @return
     */
    public void updateUserInfo(String name,String headUrl,int sex,long birth,CommonCallback callback) {
        new HttpRequest(appCompatActivity).postUrl(true,updateUserInfo(name,headUrl,sex,birth),callback, BaseCodeEntity.class);
    }

    private String updateUserInfo(String name,String headUrl,int sex,long birth) {
        return "mutation {\n" +
                "  updateUserInformation:updateUserInformation(token: \""+SharedPreUtil.getToken()+"\", avatar: \""+headUrl+"\", name: \""+name+"\", sex: "+sex+", birthday: "+birth+") {\n" +
                "    status\n" +
                "    msg\n" +
                "  }\n" +
                "}";
    }

    /**
     * 获取验证码
     * @param phone
     * @return
     */
    private String getCode(String phone){
        return "mutation {\n" +
                "  checkCode: checkCode(phone: \""+phone+"\") {\n" +
                "    status\n" +
                "    msg\n" +
                "  }\n" +
                "}\n";

    }

    /**
     * 手机验证码登录
     * @param phone
     * @param code
     * @return
     */
    private String loginByPhoneParam(String phone, String code){

        return "mutation {\n" +
                "  login: login(phone: \""+phone+"\", code: \""+code+"\") {\n" +
                "    status\n" +
                "    msg\n" +
                "    user {\n" + userInfo()+"}\n" +
                "  }\n" +
                "  }\n" +
                "}";
    }



    private String loginByOpenid(String openid){

        return "mutation {\n" +
                "  login: login(openId: \""+openid+"\") {\n" +
                "    status\n" +
                "    msg\n" +
                "    user {\n" + userInfo()+"}\n" +
                "  }\n" +
                "  }\n" +
                "}";
    }

    private String userInfo(){
        return " id,uid,name,avatar,openid,token,sid,betTimes,bingoTimes,rateOfBingo,earningOfGold,earningOfCredit,IDNumber,gold,credit\n" +
                "    sex,isAdmin,email,homePage,signature,telephone\n" +
                "    latestLoginTime,realName,birthday\n" +
                "    \n" +getGuildData()+"\n";
    }

    private String getGuildData(){

        return "consortia {\n" +
                " id,name,logo,description,members {id,betTimes,rateOfBingo,earningOfGold,earningOfCredit,uid,avatar,name},size,leader {id,betTimes,rateOfBingo,earningOfGold,earningOfCredit,uid,avatar,name}\n" +
                "  viceLeader {id,betTimes,rateOfBingo,earningOfGold,earningOfCredit,uid,avatar,name},createTime,announcement {title,content,time}\n";
    }

    /**
     * 验证token是否失效
     * @param oldToken
     * @return # 0 成功 非0 失败   #成功是返回新token
     */
    private String checkToken(String oldToken){
        return "mutation {\n" +
                "  validateToken: validateToken(token: \""+oldToken+"\") {\n" +
                "    status\n" +
                "    msg\n" +
                "    token\n" +
                "  }\n" +
                "}";
    }

    /**
     * 绑定手机号
     * @param token
     * @param phone
     * @param code
     * @return
     */
    private String bindPhone(String token, String phone, String code){
        return "mutation {\n" +
                "  bindPhone: bindPhone(token: \""+token+"\", phone: \""+phone+"\", code: \""+code+"\") {\n" +
                "    status\n" +
                "    msg\n" +
                "  }\n" +
                "}\n";
    }

    private String getUserInfo(String token) {
        return "{\n" +
                "  user(token: \"" + token + "\") {\n" +userInfo()+" }" +
                "  }\n" +
                "}\n";
    }


    private String getUserInfoByUid(long uid) {
        return "{\n" +
                "  user(uid: " + uid + ") {\n" +userInfo()+" }" +
                "  }\n" +
                "}\n";
    }


    private String updateUserInfo(String token, String name, String headUrl, String phone, int sex, long birth) {
        return "mutation {\n" +
                "  updateUserInformation: updateUserInformation(\n" +
                "    token: \""+token+"\",\n" +
                "    avatar: \""+headUrl+"\",\n" +
                "    name: \""+name+"\",\n" +
                "    telephone: \""+phone+"\", \n" +
                "    sex: "+sex+", birthday: "+birth+", \n" +
                "    ) {\n" +
                "    status\n" +
                "    msg\n" +
                "  }\n" +
                "}";
    }
}
