package com.jgg.games.view.delegate;

import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.jgg.games.R;
import com.jgg.games.adapter.ViewPagerAdapter;
import com.jgg.games.callback.AppBarStateChangeListener;
import com.jgg.games.presenter.base.HeaderDelegate;
import com.jgg.games.widget.MyTabWidget;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public abstract class BaseTabDelegate extends HeaderDelegate {

    private Toolbar toolbar;
    private MyTabWidget tab;
    private ViewPager viewPager;
    private AppBarLayout appBar;

    @Override
    public void initWidget() {
        super.initWidget();
        toolbar = get(R.id.toolbar);
        tab = get(R.id.tab);
        viewPager = get(R.id.viewpager);
        appBar = get(R.id.appbar);
    }

    @Override
    public void initValue() {
        super.initValue();
        tab.setCurrentTab(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tab.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tab.setSelectionChangedListener(new MyTabWidget.OnMyTabSelectionChanged() {
            @Override
            public void onTabSelectionChanged(int tabIndex, boolean clicked) {
                viewPager.setCurrentItem(tabIndex);
            }
        });

        toolbar.setTitle("");

    }

    public void OnOffsetChangedListener(AppBarStateChangeListener listener){
        appBar.addOnOffsetChangedListener(listener);
    }


    public void setViewPagerLimit(int size){
        viewPager.setOffscreenPageLimit(size);
    }

    public void setAdapter(ViewPagerAdapter adapter){
        viewPager.setAdapter(adapter);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
}
