package com.jgg.games.presenter.activity;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;

import com.jgg.games.base.AppManager;
import com.jgg.games.callback.OnChooseTelCallBack;
import com.jgg.games.callback.OnLoginCallBack;
import com.jgg.games.umeng.AuthListener;
import com.jgg.games.presenter.base.SendCodeActitity;
import com.jgg.games.utils.IntentUtils;
import com.jgg.games.utils.KeyboardUtils;
import com.jgg.games.utils.SharedPreUtil;
import com.jgg.games.view.delegate.LoginActivityDelegate;
import com.jgg.games.widget.dialog.LoginHistoryTelPop;
import com.jgg.rxretrofitlibrary.retrofit_rx.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

import com.jgg.games.R;
import com.jgg.games.model.entity.UserEntity;
import com.jgg.games.model.manager.UserManager;
import com.jgg.games.utils.StringUtil;
import com.jgg.games.utils.ToastUtil;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class LoginActivity extends SendCodeActitity<LoginActivityDelegate> implements OnLoginCallBack {

    private SHARE_MEDIA plat = SHARE_MEDIA.WEIXIN;
    private LoginHistoryTelPop pop;

    @Override
    protected void initValue() {
        super.initValue();
        viewDelegate.setTitle(R.string.login_title);

        pop = new LoginHistoryTelPop(this, new OnChooseTelCallBack() {
            @Override
            public void onChooseTel(String tel) {
                viewDelegate.setPhone(tel);
            }
        });

    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.setOnClickListener(this,R.id.tv_login,R.id.tv_getcode,R.id.ly_weixin,R.id.ly_qq,R.id.et_phone,R.id.et_code,R.id.rl_content);
    }

    @Override
    protected Class<LoginActivityDelegate> getDelegateClass() {
        return LoginActivityDelegate.class;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_login:
                String phone =  viewDelegate.getPhone();
                String code = viewDelegate.getCode();

                if (StringUtil.isEmpty(phone) || phone.length() != 11 ){
                    ToastUtil.showToast(R.string.login_phone_hint_toast);
                    return;
                }

                if (StringUtil.isEmpty(code)){
                    ToastUtil.showToast(R.string.login_code_hint);
                    return;
                }

                showDialog();
                UserManager.getInstance().loginByPhone(phone,code,this);

                break;

            case R.id.ly_weixin:
                plat = SHARE_MEDIA.WEIXIN;
                loginByQQorWeixin();
                break;

            case R.id.ly_qq:
                plat = SHARE_MEDIA.QQ;
                loginByQQorWeixin();
                break;
            case R.id.et_phone:
                viewDelegate.showHistoryTel(pop);
                break;
            case R.id.et_code:
            case R.id.rl_content:
                if (pop != null && pop.isShowing()) {
                    pop.dismiss();
                }
                break;
        }

    }

    private void loginByQQorWeixin() {
        showDialog(false);
        UMShareAPI.get(this).doOauthVerify(this, plat, new AuthListener(this,this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    @Override
    public void onSuccess(UserEntity user) {
        if (user != null){
            boolean finish = false;
            SharedPreUtil.setUid(user.getUid());
            if (StringUtil.isEmptyNotNull(user.getName())){
                user.setAvatar(SharedPreUtil.getWeixinOrQqHead());
                user.setName(SharedPreUtil.getWeixinOrQqName());
                DatabaseManager.getInstance().insert(user); // 保存对像
                IntentUtils.intentParam(this,UpdateInfoActitity.class,UpdateInfoActitity.FROM,UpdateInfoActitity.LOGIN);
            }else {
                if (SharedPreUtil.getUser() != null) {
                    DatabaseManager.getInstance().update(user);
                }else {
                    DatabaseManager.getInstance().insert(user); // 保存对像
                }
                SharedPreUtil.setIslogin(true);
                IntentUtils.intentNoparams(this, MainActivity.class);
                finish = true;
            }
            SharedPreUtil.setToken(user.getToken());
            SharedPreUtil.setSid(user.getSid());
            SharedPreUtil.setPhone(user.getTelephone());
            if (finish){
                AppManager.getAppManager().finishActivity(LoginActivity.class);
            }
        }
    }

    @Override
    public void onFail(String error) {
        dismissDialog();
        ToastUtil.showToast(error);
    }

}
