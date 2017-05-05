package com.jgg.games.view.delegate;

import com.jgg.games.R;
import com.jgg.games.view.base.HeaderDelegate;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class SplashActDelegate extends HeaderDelegate {

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initWidget() {
        showTitle(false);
        super.initWidget();
    }

}
