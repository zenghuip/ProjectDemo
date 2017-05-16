package com.jgg.games.http.callback;

import android.app.Activity;

import com.jgg.rxretrofitlibrary.retrofit_rx.database.DatabaseManager;

import com.jgg.games.base.AppManager;
import com.jgg.games.http.base.CommonCallback;
import com.jgg.games.model.entity.BaseCodeEntity;
import com.jgg.games.model.entity.CommonEntity;
import com.jgg.games.model.entity.UserEntity;
import com.jgg.games.utils.IntentUtils;
import com.jgg.games.utils.SharedPreUtil;
import com.jgg.games.utils.StringUtil;
import com.jgg.games.utils.ToastUtil;
import com.jgg.games.view.activity.LoginActivity;
import com.jgg.games.view.activity.MainActivity;
import com.jgg.games.view.activity.UpdateInfoActitity;

/**
 * Created by Administrator on 2017/3/27 0027.
 * 登录回调
 */

public class LoginGetUserCallback implements CommonCallback<CommonEntity<BaseCodeEntity>> {
    private Activity activity;

    public LoginGetUserCallback(Activity activity){
        this.activity = activity;
    }

    @Override
    public void onSuccess(CommonEntity<BaseCodeEntity> response) {

        BaseCodeEntity login = response.getData().getLogin();
        if (login != null){
            if (login.getStatus() == 0){
                UserEntity user = login.getUser();
                boolean finish = false;
                SharedPreUtil.setUid(user.getUid());
                if (user != null && StringUtil.isEmptyNotNull(user.getName())){
                    user.setAvatar(SharedPreUtil.getWeixinOrQqHead());
                    user.setName(SharedPreUtil.getWeixinOrQqName());
                    DatabaseManager.getInstance().insert(user); // 保存对像
                    IntentUtils.intentParam(activity,UpdateInfoActitity.class,UpdateInfoActitity.FROM,UpdateInfoActitity.LOGIN);
                }else {
                    if (SharedPreUtil.getUser() != null) {
                        DatabaseManager.getInstance().update(user);
                    }else {
                        DatabaseManager.getInstance().insert(user); // 保存对像
                    }
                    SharedPreUtil.setIslogin(true);
                    IntentUtils.intentNoparams(activity, MainActivity.class);
                    finish = true;
                }
                SharedPreUtil.setToken(user.getToken());
                SharedPreUtil.setSid(user.getSid());
                SharedPreUtil.setPhone(user.getTelephone());
                if (finish){
                    AppManager.getAppManager().finishActivity(LoginActivity.class);
                }

            }else {
                ToastUtil.showToast(login.getMsg());
            }
        }
    }

    @Override
    public void onError(String error) {
        ToastUtil.showToast(error);
    }

}
