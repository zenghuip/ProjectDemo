package com.jgg.games.view.delegate;

import android.widget.ImageView;
import android.widget.TextView;

import com.jgg.games.R;
import com.jgg.games.model.entity.UserEntity;
import com.jgg.games.presenter.base.HeaderDelegate;
import com.jgg.games.utils.ImageUtil;
import com.jgg.games.utils.SharedPreUtil;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class MineFrgDelegate extends HeaderDelegate {

    private ImageView ivHead;
    private TextView tvName;
    private TextView tvGold;
    private TextView tvCredit;


    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_mine;
    }


    @Override
    public void initWidget() {
        super.initWidget();
        ivHead = get(R.id.iv_head);
        tvName = get(R.id.tv_name);
        tvGold = get(R.id.tv_gold);
        tvCredit = get(R.id.tv_credit);
    }

    @Override
    public void initValue() {
        super.initValue();
        setTitleState(false,true,false,true,false);
    }

    public void initData(){
        UserEntity user = SharedPreUtil.getUser();
        if (user != null){
            tvName.setText(user.getName());
            ImageUtil.displayImg(user.getAvatar(),ivHead);
            tvGold.setText(String.valueOf(user.getGold()));
            tvCredit.setText(String.valueOf(user.getCredit()));
        }
    }
}
