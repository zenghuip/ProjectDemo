package com.jgg.games.view.delegate;

import android.widget.TextView;

import com.jgg.games.R;
import com.jgg.games.presenter.base.HeaderDelegate;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class SettingDelegate extends HeaderDelegate {
    private TextView tvInfo;
    private TextView tvExit;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        tvInfo = get(R.id.tv_info);
        tvExit = get(R.id.tv_exit);
    }

    @Override
    public void initValue() {
        super.initValue();
        setTitleAndBack(R.string.setting_title);
    }
}
