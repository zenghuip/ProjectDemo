package com.jgg.games.view.activity;

import com.jgg.games.http.base.CommonCallback;
import com.jgg.games.model.entity.BaseCodeEntity;
import com.jgg.games.model.entity.CommonEntity;
import com.jgg.games.model.manager.UserManager;
import com.jgg.games.utils.IntentUtils;
import com.jgg.games.utils.SharedPreUtil;
import com.jgg.games.view.base.BaseActivity;
import com.jgg.games.view.delegate.SplashActDelegate;

public class SplashActivity extends BaseActivity<SplashActDelegate> {

    @Override
    protected Class<SplashActDelegate> getDelegateClass() {
        return SplashActDelegate.class;
    }

    @Override
    protected void initValue() {
        super.initValue();
        if (SharedPreUtil.getIslogin()){
            UserManager.getInstance(this).checkToken(SharedPreUtil.getToken(), new CommonCallback<CommonEntity<BaseCodeEntity>>() {
                @Override
                public void onSuccess(CommonEntity<BaseCodeEntity> result) {
                    BaseCodeEntity data = result.getData().getValidateToken();
                    if (data != null && data.getStatus() == 0){ // token没过期
                        SharedPreUtil.setToken(data.getToken());
                        UserManager.getInstance(SplashActivity.this).getUserInfo();
                        goMain();
                    }else {
                        goLogin();
                    }
                }

                @Override
                public void onError(String error) {

                }
            });

        }else {
            goLogin();
        }

    }

    private void goLogin(){
        IntentUtils.intentNoparams(this,LoginActivity.class);
        finish();
    }

    private void goMain(){
        IntentUtils.intentNoparams(this,MainActivity.class);
        finish();
    }

}
