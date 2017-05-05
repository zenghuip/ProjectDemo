package com.jgg.games.http;

import android.content.Context;

import com.jgg.games.http.base.BaseGraphqlRequest;
import com.jgg.games.http.base.CommonCallback;

/**
 * Created by Administrator on 2017/5/4 0004.
 */

public class HttpRequest extends BaseGraphqlRequest {

    public HttpRequest() {
        super();
    }


    public void postUrl(boolean showProgress, String url, CommonCallback callback,Class cls){
        setShowProgress(showProgress);
        setCommonCallback(url,callback, cls);
    }


}
