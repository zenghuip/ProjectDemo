package com.jgg.games.http.base;

import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 获取基础列表
 *
 */

public interface BaseGraphqlService {

    @POST()
    Observable<String> getResult(@Url String url);
}
