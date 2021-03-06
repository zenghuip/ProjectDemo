package com.jgg.games.view.delegate;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.jgg.games.R;
import com.jgg.games.base.CosApp;
import com.jgg.games.model.entity.UserEntity;
import com.jgg.games.presenter.base.HeaderDelegate;
import com.jgg.games.utils.CalUtils;
import com.jgg.games.utils.ImageUtil;
import com.jgg.games.utils.SharedPreUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class IndexFrgDelegate extends HeaderDelegate {

    private TextView tvDataCenter;
    private TextView tvRank;
    private TextView tvSearch;
    private TextView tvGold;
    private TextView tvScore;
    private ImageView ivHead;
    private RelativeLayout rlHead;
    private SlidingTabLayout mTabs;
    private ViewPager viewPager;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_index;
    }


    @Override
    public void initWidget() {
        super.initWidget();
        showTitle(false);
        tvDataCenter = get(R.id.tv_datacenter);
        tvGold = get(R.id.tv_gold);
        tvScore = get(R.id.tv_score);
        ivHead = get(R.id.iv_head);
        rlHead = get(R.id.rl_head);
        tvRank = get(R.id.tv_rank);
        tvSearch = get(R.id.tv_search);
        mTabs = get(R.id.tb_top);
        viewPager = get(R.id.index_fl);
    }

    @Override
    public void initValue() {
        super.initValue();
        viewPager.setOffscreenPageLimit(0);
        mTabs.setSnapOnTabClick(true);
        setHeadData();
    }

    public void setTabVisible(boolean show){
        setViewGoneOrVisible(mTabs,show);
    }

    public void setViewAdapter(List<String> names, ArrayList<Fragment> mFragments){
        mTabs.setViewPager(viewPager,names,this.getActivity(),mFragments);
    }

    public void setHeadData(){
        UserEntity user = SharedPreUtil.getUser();

        if (user != null){
            ImageUtil.displayImg(user.getAvatar(),ivHead);
            tvScore.setText(CalUtils.digChangeStr(user.getCredit()));
            tvGold.setText(CalUtils.digChangeStr(user.getGold()));
        }
    }
}
