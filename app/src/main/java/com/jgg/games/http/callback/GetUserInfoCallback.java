package com.jgg.games.http.callback;


import com.jgg.games.event.EventBusManager;
import com.jgg.games.http.base.CommonCallback;
import com.jgg.games.model.entity.BaseCodeEntity;
import com.jgg.games.model.entity.CommonEntity;
import com.jgg.games.model.entity.UserEntity;
import com.jgg.games.utils.ToastUtil;
import com.jgg.rxretrofitlibrary.retrofit_rx.database.DatabaseManager;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/3/27 0027.
 * 获取用户信息
 */

public class GetUserInfoCallback implements CommonCallback<CommonEntity<BaseCodeEntity>> {

    @Override
    public void onSuccess(CommonEntity<BaseCodeEntity> response) {
        UserEntity user = response.getData().getUser();
        if (user != null){
            if (user.getStatus() == 0){
                DatabaseManager.getInstance().update(user);
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 更新用户信息
                        EventBusManager.NotifyUser notifyUser = new EventBusManager.NotifyUser();
                        EventBus.getDefault().post(notifyUser);
                    }
                }, 500);

            }else {
                ToastUtil.showToast(user.getMsg());
            }
        }

    }

    @Override
    public void onError(String error) {
        ToastUtil.showToast(error);
    }
}
