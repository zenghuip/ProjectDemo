package com.jgg.games.view.delegate;

import android.app.Activity;
import android.view.KeyEvent;

import com.jgg.games.R;
import com.jgg.games.utils.ToastUtil;
import com.jgg.games.view.base.HeaderDelegate;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class MainActivityDelegate extends HeaderDelegate {
    private long exitTime = 0;


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        showTitle(false);
        super.initWidget();

    }


    ////再按一次退出
    public boolean onKeyDown(Activity context, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.showToast(R.string.exit_tip);
                exitTime = System.currentTimeMillis();
            } else {
                context.finish();
                System.exit(0);
            }
            return true;
        }
        return false;
    }
}
