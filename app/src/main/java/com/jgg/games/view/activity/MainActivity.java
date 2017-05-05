package com.jgg.games.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.jgg.games.R;
import com.jgg.games.view.base.BaseActivity;
import com.jgg.games.view.delegate.MainActivityDelegate;
import com.jgg.games.view.fragment.BusiMainFragment;

public class MainActivity extends BaseActivity<MainActivityDelegate> {


    @Override
    protected Class<MainActivityDelegate> getDelegateClass() {
        return MainActivityDelegate.class;
    }


    @Override
    protected void initValue() {
        super.initValue();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment busiMainFragment = BusiMainFragment.newInstance();
        ft.replace(R.id.work_area, busiMainFragment);
        ft.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        viewDelegate.onKeyDown(this,keyCode,event);
        return super.onKeyDown(keyCode, event);
    }

}
