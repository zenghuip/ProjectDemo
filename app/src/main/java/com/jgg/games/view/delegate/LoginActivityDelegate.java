package com.jgg.games.view.delegate;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jgg.games.R;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class LoginActivityDelegate extends SendCodeDelegate {

    private TextView tvGetCode;
    private TextView tvLogin;
    private LinearLayout lyWeixin;
    private LinearLayout lyQQ;
    private RelativeLayout rlContent;


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    public void initWidget() {
        super.initWidget();
        tvGetCode = get(R.id.tv_getcode);
        tvLogin = get(R.id.tv_login);
        lyWeixin = get(R.id.ly_weixin);
        lyQQ = get(R.id.ly_qq);
        rlContent = get(R.id.rl_content);
    }

}
