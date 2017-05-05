package com.jgg.games.model.entity;

/**
 * Created by zhp on 2015/8/10.
 * 最外层数据
 */
public class CommonEntity<T> extends ServerStatus{

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
