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
public class DefaultFooter extends BaseIndicator {
    private TextView mStringIndicator;
    private ProgressWheel progress_wheell;
    private int default_rim_color;
    private Context context;

    public DefaultFooter(Context context){
        this.context = context;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.layout_refresh_foot, parent, true);
        View child = v.getChildAt(v.getChildCount() - 1);
        mStringIndicator = (TextView) child.findViewById(R.id.tv_footer);
        progress_wheell = (ProgressWheel) v.findViewById(R.id.progress_wheell);
        default_rim_color = progress_wheell.getRimColor();
        return child;
    }

    @Override
    public void onAction() {
        mStringIndicator.setText(context.getResources().getString(R.string.pull_release_load));
    }

    @Override
    public void onUnaction() {
        mStringIndicator.setText(context.getResources().getString(R.string.pull_load));
    }

    @Override
    public void onRestore() {
        mStringIndicator.setText(context.getResources().getString(R.string.pull_load));
        progress_wheell.setRimColor(default_rim_color);
        progress_wheell.stopSpinning();
    }

    @Override
    public void onLoading() {
        mStringIndicator.setText(context.getResources().getString(R.string.pull_loading));
        progress_wheell.setRimColor(Color.parseColor("#00000000"));
        progress_wheell.spin();
    }
}
