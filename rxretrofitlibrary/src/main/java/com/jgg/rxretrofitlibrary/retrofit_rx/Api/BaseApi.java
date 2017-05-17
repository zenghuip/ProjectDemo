package com.jgg.rxretrofitlibrary.retrofit_rx.Api;

import com.jgg.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import java.lang.ref.SoftReference;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * 请求数据统一封装类
 */
public abstract class BaseApi {
    /*是否能取消加载框*/
    private boolean cancel = false;
    /*是否显示加载框*/
    private boolean showProgress = true;
    private String message = "请稍后...";
    /*是否需要缓存处理*/
    private boolean cache = false;
    /*方法-如果需要缓存必须设置这个参数；不需要不用設置*/
    private String method="";
    /*有网情况下的本地缓存时间默认60秒*/
    private int cookieNetWorkTime = 60;
    /*无网络的情况下本地缓存时间默认30天*/
    private int cookieNoNetWorkTime = 24 * 60 * 60 * 30;

    private SoftReference<HttpOnNextListener> onNextListener;

    /**
     * 设置参数
     *
     * @param retrofit
     * @return
     */
    public abstract Observable getObservable(Retrofit retrofit);


    public int getCookieNoNetWorkTime() {
        return cookieNoNetWorkTime;
    }

    public void setCookieNoNetWorkTime(int cookieNoNetWorkTime) {
        this.cookieNoNetWorkTime = cookieNoNetWorkTime;
    }

    public int getCookieNetWorkTime() {
        return cookieNetWorkTime;
    }

    public void setCookieNetWorkTime(int cookieNetWorkTime) {
        this.cookieNetWorkTime = cookieNetWorkTime;
    }

    public String getUrl() {
        return getMethod();
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public SoftReference<HttpOnNextListener> getOnNextListener() {
        return onNextListener;
    }

    public void setOnNextListener(HttpOnNextListener onNextListener) {
        this.onNextListener = new SoftReference(onNextListener);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
