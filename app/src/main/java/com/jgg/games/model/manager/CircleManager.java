package com.jgg.games.model.manager;

import com.jgg.games.http.HttpRequest;
import com.jgg.games.http.base.CommonCallback;
import com.jgg.games.model.entity.TiebaTypeEntity;

/**
 * Created by dingdongdong on 2017/3/28.
 *  圈子
 */
public class CircleManager {

    private static final CircleManager instance = new CircleManager();


    public static CircleManager getInstance() {
        return instance;
    }


    public void getCircleType(CommonCallback callback) {
        new HttpRequest().postUrl(getCircleType(),callback,TiebaTypeEntity.class);
    }

    private String getCircleType(){
        return "{postCategories {id,name,image}\n" +
                "}";
    }

}
