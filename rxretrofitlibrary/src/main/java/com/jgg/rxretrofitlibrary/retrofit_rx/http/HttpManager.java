package com.jgg.rxretrofitlibrary.retrofit_rx.http;

import com.jgg.rxretrofitlibrary.retrofit_rx.Api.BaseApi;
import com.jgg.rxretrofitlibrary.retrofit_rx.exception.FactoryException;
import com.jgg.rxretrofitlibrary.retrofit_rx.exception.RetryWhenNetworkException;
import com.jgg.rxretrofitlibrary.retrofit_rx.subscribers.ProgressSubscriber;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * http交互处理类
 */
public class HttpManager {
    private String baseUrl;
    /*软引用對象*/
    private OkHttpClient.Builder builder;
    private Retrofit retrofit;
    /*超时时间-默认6秒*/
    private int connectionTime = 6;

    private static HttpManager instance = null;
    private HttpManager(String baseUrl) {
        this.baseUrl = baseUrl;

        //手动创建一个OkHttpClient并设置超时时间缓存等设置
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(connectionTime, TimeUnit.SECONDS);
        /*创建retrofit对象*/
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    public synchronized static HttpManager getInstance(String baseUrl) {
        if (instance == null) {
            instance = new HttpManager(baseUrl);
        }
        return instance;
    }
    /**
     * 处理http请求
     *
     * @param basePar 封装的请求数据
     */
    public void doHttpDeal(BaseApi basePar) {
        /*rx处理*/
        ProgressSubscriber subscriber = new ProgressSubscriber(basePar);
        Observable observable = basePar.getObservable(retrofit)
                /*失败后的retry配置*/
                .retryWhen(new RetryWhenNetworkException())
                /*异常处理*/
                .onErrorResumeNext(funcException)
                /*生命周期管理*/
//                .compose(((RxAppCompatActivity)appCompatActivity).bindToLifecycle())
                //Note:手动设置在activity onDestroy的时候取消订阅
//                .compose(((RxAppCompatActivity)appCompatActivity).bindUntilEvent(ActivityEvent.DESTROY))
                /*http请求线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread());

        /*数据回调*/
        observable.subscribe(subscriber);
    }


    /**
     * 异常处理
     */
    Func1 funcException = new Func1<Throwable, Observable>() {
        @Override
        public Observable call(Throwable throwable) {
            return Observable.error(FactoryException.analysisExcetpion(throwable));
        }
    };

}
