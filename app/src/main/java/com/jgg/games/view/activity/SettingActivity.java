package com.jgg.games.view.activity;

import android.view.View;

import com.jgg.games.R;
import com.jgg.games.base.AppManager;
import com.jgg.games.base.CosApp;
import com.jgg.games.utils.IntentUtils;
import com.jgg.games.utils.SharedPreUtil;
import com.jgg.games.view.base.BaseActivity;
import com.jgg.games.view.delegate.SettingDelegate;
import com.umeng.socialize.UMShareAPI;

public class SettingActivity extends BaseActivity<SettingDelegate> {

    @Override
    protected Class<SettingDelegate> getDelegateClass() {
        return SettingDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.setOnClickListener(this, R.id.tv_info,R.id.tv_exit);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_info:
                IntentUtils.intentParam(this,UpdateInfoActitity.class,UpdateInfoActitity.FROM,UpdateInfoActitity.MINE);
                break;
            case R.id.tv_exit:
                SharedPreUtil.clearParams();
                AppManager.getAppManager().finishAllActivity();
                UMShareAPI.get(this).release();
                IntentUtils.intentNoparams(this, LoginActivity.class);
                finish();
                break;
        }
    }
}
