package com.jgg.games.callback;

import com.jgg.games.model.entity.UserEntity;

/**
 * Created by Administrator on 2017/5/19 0019.
 * 登录成功回调
 */

public interface OnLoginCallBack {
    void onSuccess(UserEntity userEntity);
    void onFail(String error);
}
