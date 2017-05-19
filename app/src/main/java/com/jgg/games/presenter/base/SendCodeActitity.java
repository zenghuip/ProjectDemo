package com.jgg.games.presenter.base;

import android.os.CountDownTimer;
import android.view.View;

import com.jgg.games.R;
import com.jgg.games.http.base.CommonCallback;
import com.jgg.games.model.entity.BaseCodeEntity;
import com.jgg.games.model.entity.CommonEntity;
import com.jgg.games.model.manager.UserManager;
import com.jgg.games.utils.SharedPreUtil;
import com.jgg.games.utils.StringUtil;
import com.jgg.games.utils.ToastUtil;
import com.jgg.games.view.delegate.SendCodeDelegate;

/**
 * Created by Administrator on 2017/3/20 0020.
 * 发送验证码
 */

public abstract class SendCodeActitity<T extends SendCodeDelegate> extends BaseActivity<T>{
    protected int time = 60;
    public MyCountDownTimer timer = new MyCountDownTimer(60000, 1000);


    @Override
    protected void initValue() {
        super.initValue();
        if (!StringUtil.isEmpty(SharedPreUtil.getPhone())){
            viewDelegate.setPhone(SharedPreUtil.getPhone());
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_getcode:
                String phone =  viewDelegate.getPhone();
                if (StringUtil.isEmpty(phone) || phone.length() != 11 || !StringUtil.isTelephone(phone)){
                    ToastUtil.showToast(R.string.login_phone_hint_toast);
                    return;
                }

                UserManager.getInstance().getCode(phone, new CommonCallback<CommonEntity<BaseCodeEntity>>() {

                    @Override
                    public void onSuccess(CommonEntity<BaseCodeEntity> result) {
                        BaseCodeEntity login = result.getData().getCheckCode();
                        if (login != null){
                            if (login.getStatus() == 0){
                                timer.start();
                            }else {
                                ToastUtil.showToast(login.getMsg());
                                cancelTimer();
                            }
                        }else {
                            cancelTimer();
                        }
                    }

                    @Override
                    public void onError(String error) {

                    }
                });

                break;
        }
    }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            viewDelegate.setCodeEnable(true);
            viewDelegate.setCode(getResources().getString(R.string.login_get_code));
        }

        @Override
        public void onTick(long millisUntilFinished) {
            viewDelegate.setCodeEnable(false);
            viewDelegate.setCode(getResources().getString(R.string.login_resend_code,String.valueOf((millisUntilFinished / 1000))));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }

    public void cancelTimer(){
        if (timer != null){
            timer.cancel();
        }
    }
}
