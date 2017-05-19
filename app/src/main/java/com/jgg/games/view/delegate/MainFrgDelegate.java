package com.jgg.games.view.delegate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.jgg.games.R;
import com.jgg.games.callback.ChangeTabBack;
import com.jgg.games.utils.FragmentUtil;
import com.jgg.games.presenter.base.HeaderDelegate;
import com.jgg.games.widget.MyTabWidget;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class MainFrgDelegate extends HeaderDelegate {
    private MyTabWidget mTabs;  // 底部按钮
    private FragmentUtil fragmentUtil;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_main;
    }


    @Override
    public void initWidget() {
        super.initWidget();
        showTitle(false);
        mTabs = get(R.id.tabs);
        fragmentUtil = new FragmentUtil();
    }


    public void addFragment(Fragment fragment, Bundle bundle){
        fragmentUtil.addFragment(fragment,bundle);
    }

    public void showCurFragment(FragmentManager fragmentManager){
        fragmentUtil.showCurFragment(fragmentManager, mTabs, 0, R.id.main_workarea);
    }

    public void setTabChange(ChangeTabBack back){
        fragmentUtil.setChangeBack(back);
    }
}
