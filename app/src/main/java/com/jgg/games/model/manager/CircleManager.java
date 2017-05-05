package com.jgg.games.model.manager;

import android.content.Context;

import com.jgg.games.http.HttpRequest;
import com.jgg.games.http.base.BaseGraphqlRequest;
import com.jgg.games.http.base.CommonCallback;
import com.jgg.games.model.entity.MatchListEntity;
import com.jgg.games.model.entity.TiebaTypeEntity;

/**
 * Created by dingdongdong on 2017/3/28.
 *  圈子
 */
public class CircleManager {

    private static CircleManager instance = null;
    private Context appCompatActivity;

    private CircleManager(Context appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public synchronized static CircleManager getInstance(Context appCompatActivity) {
        if (instance == null) {
            instance = new CircleManager(appCompatActivity);
        }
        return instance;
    }


    public void getCircleType(CommonCallback callback) {
        new HttpRequest(appCompatActivity).postUrl(false,getCircleType(),callback,TiebaTypeEntity.class);
    }

    private String getCircleType(){
        return "{postCategories {id,name,image}\n" +
                "}";
    }

}
