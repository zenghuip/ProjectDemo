package com.jgg.games.view.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.jgg.games.back.umeng.AuthListener;
import com.jgg.games.utils.CommonUI;
import com.jgg.games.view.base.SendCodeActitity;
import com.jgg.games.view.delegate.LoginActivityDelegate;
import com.jgg.rxretrofitlibrary.retrofit_rx.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

import com.jgg.games.R;
import com.jgg.games.model.entity.UserEntity;
import com.jgg.games.model.manager.UserManager;
import com.jgg.games.utils.StringUtils;
import com.jgg.games.utils.ToastUtil;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class LoginActivity extends SendCodeActitity<LoginActivityDelegate> {
    private List<UserEntity> userList = new ArrayList<>();

    private SHARE_MEDIA plat = SHARE_MEDIA.WEIXIN;

    @Override
    protected void initValue() {
        super.initValue();
        viewDelegate.setTitle(R.string.login_title);
        // 查询所有用户
        userList = DatabaseManager.getInstance().getQueryAll(UserEntity.class);
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.setOnClickListener(this,R.id.tv_login,R.id.tv_getcode,R.id.ly_weixin,R.id.ly_qq);
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

                if (StringUtils.isEmpty(phone) || phone.length() != 11 ){
                    ToastUtil.showToast(R.string.login_phone_hint_toast);
                    return;
                }


                if (StringUtils.isEmpty(code)){
                    ToastUtil.showToast(R.string.login_code_hint);
                    return;
                }

                UserManager.getInstance(this).loginByPhone(phone,code);

                break;

            case R.id.ly_weixin:
                plat = SHARE_MEDIA.WEIXIN;
                loginByQQorWeixin();
                break;

            case R.id.ly_qq:
                plat = SHARE_MEDIA.QQ;
                loginByQQorWeixin();
                break;
        }

    }

    private void loginByQQorWeixin() {
        showDialog(false);
        UMShareAPI.get(this).doOauthVerify(this, plat, new AuthListener(this));
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
}
