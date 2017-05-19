package com.jgg.games.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.jgg.games.callback.ChangeTabBack;
import com.jgg.games.widget.MyTabWidget;

/**
 * Created by zhp on 2016/11/3 0003.
 * fragment 管理工具
 */

public class FragmentUtil implements MyTabWidget.OnMyTabSelectionChanged {
    private FragmentManager frgMgr ;
    private LinkedHashMap<Fragment,Bundle> mCntFrgs = new LinkedHashMap<>();
    private List<Fragment> listKey = new ArrayList<>();
    private Fragment mCurrentFrgment;
    private int curWorkarea;
    private int curTab;
    private ChangeTabBack back;

    /**
     * 添加fragment
     * @param fragment
     * @param bundle
     */
    public void addFragment(Fragment fragment, Bundle bundle){
        mCntFrgs.put(fragment,bundle);
    }

    /**
     * 显示当前的fragment
     * @param frgMgr 如果是最上层fragment就使用mActivity.getSupportFragmentManager()
     *               子fragment就用getChildFragmentManager()
     * @param curWorkarea FrameLayout的id
     * @param tab MyTabWidget的控件
     * @param curIndex 当前要显示第几个页面
     */
    public void showCurFragment(FragmentManager frgMgr, MyTabWidget tab, int curIndex, int curWorkarea){
        this.frgMgr = frgMgr;
        this.curWorkarea = curWorkarea;
        FragmentTransaction ft = frgMgr.beginTransaction();
        for (Fragment key : mCntFrgs.keySet()) {
            Bundle bundle1 = mCntFrgs.get(key);
            if (bundle1!=null){
                key.setArguments(bundle1);
            }
            listKey.add(key);
        }
        mCurrentFrgment = listKey.get(0);
        ft.add(curWorkarea,mCurrentFrgment);
        ft.commit();
        if (tab !=null) {
            tab.setCurrentTab(curIndex);
            tab.setSelectionChangedListener(this);
        }
    }

    @Override
    public void onTabSelectionChanged(int tabIndex, boolean clicked) {
        if (curTab != tabIndex) {
            changeTabView(tabIndex);
            if (back != null){
                back.changeTab(tabIndex);
            }
        }
        curTab = tabIndex;
    }

    public void changeTabView(int tabIndex) {
        FragmentTransaction ft = frgMgr.beginTransaction();
        //判断当前的Fragment是否为空，不为空则隐藏
        if (mCurrentFrgment != null) {
            ft.hide(mCurrentFrgment);
        }
        //先根据Tag从FragmentTransaction事物获取之前添加的Fragment
        Fragment fragment = frgMgr.findFragmentByTag(listKey.get(tabIndex).getClass().getName());

        if (fragment == null) {
            //如fragment为空，则之前未添加此Fragment。便从集合中取出
            fragment = listKey.get(tabIndex);
        }
        mCurrentFrgment = fragment;
        //判断此Fragment是否已经添加到FragmentTransaction事物中
        if (!fragment.isAdded()) {
            ft.add(curWorkarea, fragment, fragment.getClass().getName());
        } else {
            ft.show(fragment);
        }
        ft.commit();
    }

    public void setChangeBack(ChangeTabBack back) {
        this.back = back;
    }



}
