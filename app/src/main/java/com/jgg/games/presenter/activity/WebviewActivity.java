package com.jgg.games.presenter.activity;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.KeyEvent;

import com.jgg.games.presenter.base.BaseActivity;
import com.jgg.games.view.delegate.WebviewDelegate;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class WebviewActivity extends BaseActivity<WebviewDelegate> {
    public final static String URL = "url";

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //网页中的视频，上屏幕的时候，可能出现闪烁的情况
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
    }

    @Override
    protected Class<WebviewDelegate> getDelegateClass() {
        return WebviewDelegate.class;
    }

    @Override
    protected void getData() {
        if (getIntent() != null){
            url = getIntent().getStringExtra(URL);
            viewDelegate.webviewSetting(url);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            viewDelegate.goBack();
            return true;
        }
        return false;
    }


    @Override
    public void onPause() {
        super.onPause();
        viewDelegate.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewDelegate.onResume();
    }


}
