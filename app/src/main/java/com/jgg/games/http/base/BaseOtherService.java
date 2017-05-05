package com.jgg.games.http.base;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 获取基础列表
 *
 */

public interface BaseOtherService {

    @POST("api")
    Observable<String> postJson(@Body String json);


}
