package com.jgg.games.pullrefreshview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anthony.rvhelper.R;
import com.pnikosis.materialishprogress.ProgressWheel;


/**
 * Created by Anthony on 2016/7/18.
 */
public class DefaultHeader extends BaseIndicator {
    private TextView mStringIndicator;
    private ProgressWheel progress_wheel;
    private int default_rim_color;
    private Context context;

    public DefaultHeader(Context context){
        this.context = context;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.layout_refresh_header, parent, true);
        View child = v.getChildAt(v.getChildCount() - 1);
        mStringIndicator = (TextView) child.findViewById(R.id.tv_header);
        progress_wheel = (ProgressWheel) v.findViewById(R.id.progress_wheel);
        default_rim_color = progress_wheel.getRimColor();
        return child;
    }

    @Override
    public void onAction() {
        mStringIndicator.setText(context.getResources().getString(R.string.pull_release));
    }

    @Override
    public void onUnaction() {
        mStringIndicator.setText(context.getResources().getString(R.string.pull_refresh));
    }

    @Override
    public void onRestore() {
        mStringIndicator.setText(context.getResources().getString(R.string.pull_refresh));
        progress_wheel.setRimColor(default_rim_color);
        progress_wheel.stopSpinning();
    }

    @Override
    public void onLoading() {
        mStringIndicator.setText(context.getResources().getString(R.string.pull_loading));
        progress_wheel.setRimColor(Color.parseColor("#00000000"));
        progress_wheel.spin();
    }
}
