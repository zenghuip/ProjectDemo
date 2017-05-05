package com.jgg.games.back.umeng;

import android.app.Activity;
import android.util.Log;

import com.jgg.games.R;
import com.jgg.games.model.manager.UserManager;
import com.jgg.games.utils.CommonUI;
import com.jgg.games.utils.SharedPreUtil;
import com.jgg.games.utils.StringUtils;
import com.jgg.games.utils.ToastUtil;
import com.jgg.games.view.base.BaseActivity;
import com.jgg.rxretrofitlibrary.retrofit_rx.subscribers.CustomProgress;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class AuthListener implements UMAuthListener {
    private String OPEN_ID = "uid"; // QQ :openid	WEIXIN:unionid	WEIBO:id
    private String HEAD_URL = "iconuurl"; // 头像
    private String NINAME = "name"; // 昵称


    public AuthListener(BaseActivity activity) {
        this.activity = activity;
    }

    BaseActivity activity;

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
        ToastUtil.showToast(R.string.umeng_authorize_suc);
        final String openId = map.get(OPEN_ID);
        if (!StringUtils.isEmpty(openId)) {
            SharedPreUtil.setOpenId(openId);
            UMShareAPI.get(activity).getPlatformInfo(activity, share_media,
                    new UMAuthListener() {

                        @Override
                        public void onError(SHARE_MEDIA arg0, int arg1, Throwable arg2) {
                            activity.dismissDialog();
                            ToastUtil.showToast(R.string.umeng_getinfo_error);
                        }

                        @Override
                        public void onStart(SHARE_MEDIA share_media) {

                        }

                        @Override
                        public void onComplete(SHARE_MEDIA arg0, int arg1, Map<String, String> map) {
                            activity.dismissDialog();
                            String headUrl = map.get(HEAD_URL);
                            String niName = map.get(NINAME);
                            SharedPreUtil.setWeixinOrQqHead(headUrl);
                            SharedPreUtil.setWeixinOrQqName(niName);
                            UserManager.getInstance().loginByOpenId(activity,openId);
                        }

                        @Override
                        public void onCancel(SHARE_MEDIA arg0, int arg1) {
                            activity.dismissDialog();
                        }
                    });
        }


    }

    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
        activity.dismissDialog();
        ToastUtil.showToast(R.string.umeng_authorize_error);
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {
        activity.dismissDialog();
        ToastUtil.showToast(R.string.umeng_authorize_cancel);
    }
}
