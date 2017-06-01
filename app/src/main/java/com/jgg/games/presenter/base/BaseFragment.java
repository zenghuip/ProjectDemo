package com.jgg.games.presenter.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.jgg.games.utils.KeyboardUtils;
import com.jgg.games.widget.dialog.ShareDialog;
import com.jgg.games.utils.IntentUtils;
import com.jgg.games.presenter.activity.SettingActivity;
import com.jgg.rxretrofitlibrary.retrofit_rx.subscribers.CustomProgress;
import com.jgg.themvp.presenter.FragmentPresenter;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import com.jgg.games.R;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public abstract class BaseFragment<T extends HeaderDelegate> extends FragmentPresenter<T> implements View.OnClickListener {

    public RxAppCompatActivity activity;
    public ShareDialog shareDialog;
    public CustomProgress progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        registeredEventBus();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (RxAppCompatActivity) context;
    }

    // 注册eventbus
    public void registeredEventBus(){

    }

    @Override
    protected void initValue() {
        super.initValue();
        shareDialog = new ShareDialog(activity);
        progressDialog = new CustomProgress(activity,R.style.Custom_Progress);
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

            case R.id.tv_guide:

                break;
            case R.id.iv_setting:
                IntentUtils.intentNoparams(activity, SettingActivity.class);
                break;
            case R.id.iv_share:
                viewDelegate.share();
                break;
        }
    }


    public void showDialog(boolean canCancel){
        if (!activity.hasWindowFocus()){
            return;
        }
        if (activity !=null && progressDialog != null && !progressDialog.isShowing()){
            progressDialog.setCancelable(canCancel);
            progressDialog.setCanceledOnTouchOutside(canCancel);
            progressDialog.show();
        }
    }
    public void showDialog(){
        showDialog(true);
    }

    public void dismissDialog(){
        if (activity !=null && progressDialog != null){
            progressDialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        dismissDialog();
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }



}
