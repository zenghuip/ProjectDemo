package com.jgg.games.presenter.base;

import android.support.v4.app.Fragment;

import com.jgg.games.adapter.ViewPagerAdapter;
import com.jgg.games.view.delegate.BaseTabDelegate;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseTabActitity<T extends BaseTabDelegate> extends BaseActivity<T>{

    private ViewPagerAdapter adapter;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void initValue() {
        super.initValue();
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        setSupportActionBar(viewDelegate.getToolbar());
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }

    @Override
    protected void getData() {
        super.getData();
        fragments = getFragments();
        if (fragments == null || fragments.size() == 0){
            viewDelegate.setViewPagerLimit(1);
        }else {
            viewDelegate.setViewPagerLimit(fragments.size()-1);
        }
        viewDelegate.setAdapter(adapter);
        if (fragments.size()>0){
            adapter.setFragments(fragments);
        }
    }

    protected abstract List<Fragment> getFragments();

}
