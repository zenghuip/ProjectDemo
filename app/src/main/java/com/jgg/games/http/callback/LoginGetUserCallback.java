package com.jgg.games.http.callback;

import com.jgg.games.callback.OnLoginSucCallBack;
import com.jgg.games.http.base.CommonCallback;
import com.jgg.games.model.entity.BaseCodeEntity;
import com.jgg.games.model.entity.CommonEntity;
import com.jgg.games.model.entity.UserEntity;
import com.jgg.games.utils.ToastUtil;

/**
 * Created by Administrator on 2017/3/27 0027.
 * 登录回调
 */

public class LoginGetUserCallback implements CommonCallback<CommonEntity<BaseCodeEntity>> {
    private OnLoginSucCallBack back;

    public LoginGetUserCallback(OnLoginSucCallBack back){
        this.back = back;
    }

    @Override
    public void onSuccess(CommonEntity<BaseCodeEntity> response) {

        BaseCodeEntity login = response.getData().getLogin();
        if (login != null){
            if (login.getStatus() == 0){
                UserEntity user = login.getUser();
                if (back != null){
                    back.onSuccess(user);
                }
            }else {
                if (back != null){
                    back.onFail(login.getMsg());
                }
            }
        }
    }

    @Override
    public void onError(String error) {
        if (back != null){
            back.onFail(error);
        }
    }

}
