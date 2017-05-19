package com.jgg.games.presenter.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.jgg.games.R;
import com.jgg.themvp.presenter.ActivityPresenter;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class BaseFragmentActivity extends ActivityPresenter<BaseFragActDelegate> {
    public final static String CLASS = "class";
    public final static String BUNDLE = "bundle";

    private Class<? extends Fragment> clazz;
    private Bundle bundle;

    public static void startActivity(Context context,Class<? extends Fragment> clazz, Bundle bundle){
        Intent intent = new Intent(context,BaseFragmentActivity.class);
        intent.putExtra(BUNDLE,bundle);
        intent.putExtra(CLASS,clazz);
        context.startActivity(intent);
    }

    @Override
    protected Class<BaseFragActDelegate> getDelegateClass() {
        return BaseFragActDelegate.class;
    }

    @Override
    protected void initValue() {
        super.initValue();
        if (getIntent() != null){
            clazz = (Class) getIntent().getSerializableExtra(CLASS);
            bundle = getIntent().getBundleExtra(BUNDLE);
            setInitData(clazz,bundle);
        }
    }

    public void setInitData(Class<? extends Fragment> clazz, Bundle bundle){
        if (clazz !=null) {
            try {
                addFragment(clazz.newInstance(),bundle);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    public void addFragment(Fragment fragment, Bundle bundle){
        if (bundle !=null){
            fragment.setArguments(bundle);
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl_base, fragment,"fragment");
        ft.commit();
    }

}
