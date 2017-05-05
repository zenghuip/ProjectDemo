package com.jgg.games.http.base;

import android.content.Context;

import com.google.gson.Gson;
import com.jgg.rxretrofitlibrary.retrofit_rx.Api.BaseApi;
import com.jgg.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.jgg.rxretrofitlibrary.retrofit_rx.http.HttpManager;
import com.jgg.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.jgg.games.R;
import com.jgg.games.base.AppConfig;
import com.jgg.games.model.entity.CommonEntity;
import com.jgg.games.utils.ToastUtil;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public abstract class BaseGraphqlRequest<T> extends BaseApi implements HttpOnNextListener{

    private String baseUrl = AppConfig.NET_ADDRESS;
    private String urlParam = "";
    private CommonCallback commonCallback;
    private Class<T> tClass;
    private Context appCompatActivity;

    public BaseGraphqlRequest(Context appCompatActivity){
        this.appCompatActivity = appCompatActivity;
        setBaseUrl(baseUrl);
        setOnNextListener(this);
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        BaseGraphqlService httpService = retrofit.create(BaseGraphqlService.class);
        return httpService.getResult(GenURL(urlParam));
    }

    protected  String GenURL(String param) {
        try {
            String paramEncode = URLEncoder.encode(param, "UTF-8");
            return ("graphql?query=" + paramEncode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ("graphql?query=" + param);
        }
    }


    @Override
    public void onNext(String resulte, String mothead) {
        CommonEntity commonEntity = new CommonEntity();

        try {
            JSONObject jsonObject = new JSONObject(resulte);
            String jsonStr  = jsonObject.getJSONObject("data").toString();
            T data = new Gson().fromJson(jsonStr,tClass);
            commonEntity.setData(data);
        } catch (JSONException e) {
            ToastUtil.showToast(R.string.json_error);
            e.printStackTrace();
        }
        if (commonCallback != null){
           commonCallback.onSuccess(commonEntity);
        }
    }

    @Override
    public void onError(ApiException e) {

        if (commonCallback != null){
            commonCallback.onError(e.getDisplayMessage());
        }
    }

    public void setCommonCallback(String urlParam,CommonCallback commonCallback,Class<T> tClass) {
        this.urlParam = urlParam;
        this.commonCallback = commonCallback;
        this.tClass = tClass;
        HttpManager.getInstance(appCompatActivity).doHttpDeal(this);
    }

}
