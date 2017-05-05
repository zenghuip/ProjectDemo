package com.jgg.games.model.entity;

/**
 */
public class QnMsgEntity extends GraphQlModel {
    private QnMsgEntity qiniuToken;
    private String token;
    private String prefix;
    private String key;
    private QnMsgEntity data;

    public QnMsgEntity getData() {
        return data;
    }

    public void setData(QnMsgEntity data) {
        this.data = data;
    }

    public QnMsgEntity getQiniuToken() {
        return qiniuToken;
    }

    public void setQiniuToken(QnMsgEntity qiniuToken) {
        this.qiniuToken = qiniuToken;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
