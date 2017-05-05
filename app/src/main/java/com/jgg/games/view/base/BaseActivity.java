package com.jgg.games.view.base;

import android.os.Bundle;
import android.view.View;

import com.jgg.games.widget.dialog.ShareDialog;
import com.jgg.rxretrofitlibrary.retrofit_rx.subscribers.CustomProgress;
import com.jgg.themvp.presenter.ActivityPresenter;

import com.jgg.games.R;
import com.jgg.games.base.AppManager;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public abstract class BaseActivity<T extends HeaderDelegate> extends ActivityPresenter<T> implements View.OnClickListener{

    public ShareDialog shareDialog;
    public CustomProgress progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initValue() {
        super.initValue();
        shareDialog = new ShareDialog(this);
        progressDialog = new CustomProgress(this,R.style.Custom_Progress);
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        if (viewDelegate.isShowTitle()) {
            viewDelegate.setOnClickListener(this, R.id.iv_back, R.id.iv_setting,R.id.tv_guide,R.id.iv_share);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                viewDelegate.finish();
                break;
            case R.id.iv_setting:

                break;

            case R.id.tv_guide:

                break;

            case R.id.iv_share:
                viewDelegate.share();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        dismissDialog();
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void finish() {
        dismissDialog();
        super.finish();
    }

    // 通用dialog 点击可以取消
    public void showDialog(){
        showDialog(true);
    }

    /**
     *
     * @param canCancel 点击是否取消
     */
    public  void showDialog(boolean canCancel){
        if (!hasWindowFocus()){
            return;
        }
        if (!isFinishing() && progressDialog != null && !progressDialog.isShowing()){
            progressDialog.setCancelable(canCancel);
            progressDialog.setCanceledOnTouchOutside(canCancel);
            progressDialog.show();
        }
    }

    public void dismissDialog(){
        if (!isFinishing() && progressDialog != null){
            progressDialog.dismiss();
        }
    }
}
