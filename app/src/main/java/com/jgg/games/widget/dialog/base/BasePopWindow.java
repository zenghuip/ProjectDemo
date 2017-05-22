package com.jgg.games.widget.dialog.base;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.jgg.games.R;

/**
 * Created by Administrator on 2017/5/21 0021.
 */

public abstract class BasePopWindow extends PopupWindow implements View.OnClickListener{

    private View view;
    public Context context;

    protected  <T extends View> T findView(int id) {
        return (T) view.findViewById(id);
    }

    public BasePopWindow(Context context){
        this.context = context;
        view = LayoutInflater.from(context).inflate(getLayoutId(), null);
        setContentView(view);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00);
        setBackgroundDrawable(dw);
        initView();
        initValue();
        initEvent();
    }

    public abstract int getLayoutId();
    public abstract void initView();
    public abstract void initValue();
    public abstract void initEvent();

    @Override
    public void onClick(View v) {

    }
}
