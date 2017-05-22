package com.jgg.games.widget.dialog.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by Administrator on 2017/5/21 0021.
 */

public abstract class BaseDialog extends DialogFragment {

    private View view;

    protected  <T extends View> T findView(int id) {
        return (T) view.findViewById(id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        view = inflater.inflate(getLayoutId(), container);
        initView();
        initValue();
        return view;
    }


    public abstract int getLayoutId();
    public abstract void initView();
    public abstract void initValue();
}
