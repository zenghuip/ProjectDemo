package com.jgg.games.model.entity;

/**
 * Created by Administrator on 2017/3/28 0028.
 * 只返回code msg的各种实体类
 */

public class BaseCodeEntity extends ServerStatus {
    private String token;

    private BaseCodeEntity checkCode;
    private BaseCodeEntity bindPhone;
    private BaseCodeEntity login;
    private BaseCodeEntity validateToken;
    private BaseCodeEntity updateUserInformation;
    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    //    private User user;

    public BaseCodeEntity getBindPhone() {
        return bindPhone;
    }

    public void setBindPhone(BaseCodeEntity bindPhone) {
        this.bindPhone = bindPhone;
    }

    public BaseCodeEntity getUpdateUserInformation() {
        return updateUserInformation;
    }

    public void setUpdateUserInformation(BaseCodeEntity updateUserInformation) {
        this.updateUserInformation = updateUserInformation;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public BaseCodeEntity getLogin() {
        return login;
    }

    public void setLogin(BaseCodeEntity login) {
        this.login = login;
    }

    public BaseCodeEntity getValidateToken() {
        return validateToken;
    }

    public void setValidateToken(BaseCodeEntity validateToken) {
        this.validateToken = validateToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public BaseCodeEntity getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(BaseCodeEntity checkCode) {
        this.checkCode = checkCode;
    }
}
