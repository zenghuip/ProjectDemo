package com.jgg.games.http.callback;

import com.jgg.games.callback.OnLoginCallBack;
import com.jgg.games.http.base.CommonCallback;
import com.jgg.games.model.entity.BaseCodeEntity;
import com.jgg.games.model.entity.CommonEntity;
import com.jgg.games.model.entity.UserEntity;

/**
 * Created by Administrator on 2017/3/27 0027.
 * 登录回调
 */

public class LoginGetUserCallback implements CommonCallback<CommonEntity<BaseCodeEntity>> {
    private OnLoginCallBack callBack;

    public LoginGetUserCallback(OnLoginCallBack callBack){
        this.callBack = callBack;
    }

    @Override
    public void onSuccess(CommonEntity<BaseCodeEntity> response) {

        BaseCodeEntity login = response.getData().getLogin();
        if (login != null){
            if (login.getStatus() == 0){
                UserEntity user = login.getUser();
                if (callBack != null){
                    callBack.onSuccess(user);
                }
            }else {
                if (callBack != null){
                    callBack.onFail(login.getMsg());
                }
            }
        }
    }

    @Override
    public void onError(String error) {
        if (callBack != null){
            callBack.onFail(error);
        }
    }

}
