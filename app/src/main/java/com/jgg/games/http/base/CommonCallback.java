package com.jgg.games.http.base;

/**
 * Created by zhy on 15/12/14.
 * 通用回调
 * 解析数据，去掉{"data":{}}这层数据
 */
public interface CommonCallback<T> {
     void onSuccess(T result);
     void onError(String error);


}
