
package com.jgg.themvp.presenter;

import android.os.Bundle;

import com.jgg.themvp.view.IDelegate;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;


/**
 * Presenter base class for Activity
 * Presenter层的实现基类
 *
 * @param <T> View delegate class type
 */
public abstract class ActivityPresenter<T extends IDelegate> extends RxAppCompatActivity {
    protected T viewDelegate;

    public ActivityPresenter() {
        try {
            viewDelegate = getDelegateClass().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("create IDelegate error");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("create IDelegate error");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDelegate.create(getLayoutInflater(), null, savedInstanceState);
        setContentView(viewDelegate.getRootView());
        viewDelegate.initWidget();
        viewDelegate.initValue();
        initView();
        initValue();
        getData();
        bindEvenListener();
    }

    protected void initValue() {
    }
    protected void initView() {
    }
    protected void getData() {
    }

    protected void bindEvenListener() {
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (viewDelegate == null) {
            try {
                viewDelegate = getDelegateClass().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("create IDelegate error");
            } catch (IllegalAccessException e) {
                throw new RuntimeException("create IDelegate error");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewDelegate = null;
    }

    protected abstract Class<T> getDelegateClass();
}
