package com.jgg.games.http.base;

import com.google.gson.Gson;
import com.jgg.rxretrofitlibrary.retrofit_rx.Api.BaseApi;
import com.jgg.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.jgg.rxretrofitlibrary.retrofit_rx.http.HttpManager;
import com.jgg.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import com.jgg.games.base.AppConfig;
import com.jgg.games.model.entity.CommonEntity;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public abstract class BaseOtherRequest<T> extends BaseApi implements HttpOnNextListener{

    private String jsonStr = "";
    private Gson gson;

    private String baseUrl = AppConfig.TIEBA_URL;
    private CommonCallback commonCallback;
    private Class<T> tClass;
    public HttpManager httpManager;

    public BaseOtherRequest(RxAppCompatActivity appCompatActivity){
        setBaseUrl(baseUrl);
        setOnNextListener(this);
        gson = new Gson();
        httpManager = new HttpManager(appCompatActivity);
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        BaseOtherService httpService = retrofit.create(BaseOtherService.class);
        return httpService.postJson(jsonStr);
    }


    @Override
    public void onNext(String resulte, String mothead) {
        T bean = gson.fromJson(resulte, tClass);
        if (commonCallback != null){
            if (bean instanceof CommonEntity){
                CommonEntity entry = (CommonEntity)bean;
                if (entry.getCode() != 0){
                    commonCallback.onError(entry.getMsg());
                    return;
                }
            }
            commonCallback.onSuccess(bean);
        }


    }

    @Override
    public void onError(ApiException e) {

        if (commonCallback != null){
            commonCallback.onError(e.getDisplayMessage());
        }
    }

    public CommonCallback getCommonCallback() {
        return commonCallback;
    }


    public void setCommonCallback(String urlParam,CommonCallback commonCallback,Class<T> tClass) {
        this.jsonStr = urlParam;
        this.commonCallback = commonCallback;
        this.tClass = tClass;
        httpManager.doHttpDeal(this);
    }
}
