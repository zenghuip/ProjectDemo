package com.jgg.rxretrofitlibrary.retrofit_rx.http.cookie;


/**
 * post請求緩存数据
 */

public class CookieResulte {

    private long id;
    /*url*/
    private String url;
    /*返回结果*/
    private String resulte;
    /*时间*/
    private long time;

    public CookieResulte(String url, String resulte, long time) {
        this.url = url;
        this.resulte = resulte;
        this.time = time;
    }


    public CookieResulte(long id, String url, String resulte, long time) {
        this.id = id;
        this.url = url;
        this.resulte = resulte;
        this.time = time;
    }

    public CookieResulte() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getResulte() {
        return this.resulte;
    }
    public void setResulte(String resulte) {
        this.resulte = resulte;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
}
