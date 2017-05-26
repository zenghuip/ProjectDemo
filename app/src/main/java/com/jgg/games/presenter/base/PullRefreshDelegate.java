package com.jgg.games.presenter.base;

import android.view.View;
import android.widget.TextView;

import com.jgg.games.R;
import com.jgg.games.pullrefreshview.PullToRefreshView;

/**
 * Created by Administrator on 2017/4/13 0013.
 *
 */

public abstract class PullRefreshDelegate extends HeaderDelegate {

    public static final int RECYCLER_BOTH = 0; // 设置刷新加载
    public static final int RECYCLER_DISABLE = 1; //  不刷新不加载
    public static final int RECYCLER_REFRESH = 2; // 只刷新
    public static final int RECYCLER_LOAD = 3; // 只加载

    private PullToRefreshView refreshView;
    private TextView tvNodata;
    public int refreshModel = RECYCLER_BOTH;
    public boolean reset; // 不刷新时直接获取数据

    @Override
    public void initWidget() {
        super.initWidget();
        refreshView = get(R.id.refresh_view);
        tvNodata = get(R.id.tv_nodata);
    }

    // 设置recycler 类型
    public void setRefreshModel(int refreshModel) {
        this.refreshModel = refreshModel;
        switch (refreshModel) {
            case RECYCLER_BOTH:
                refreshView.setCanLoadMore(true);
                refreshView.setCanRefresh(true);
                break;
            case RECYCLER_DISABLE:
                refreshView.setCanLoadMore(false);
                refreshView.setCanRefresh(false);
                reset = true;
                break;
            case RECYCLER_REFRESH:
                refreshView.setCanLoadMore(false);
                refreshView.setCanRefresh(true);
                break;
            case RECYCLER_LOAD:
                refreshView.setCanLoadMore(true);
                refreshView.setCanRefresh(false);
                reset = true;
                break;
        }
    }

    // 设置刷新加载监听
    public void setRefreshOrLoadListener(PullToRefreshView.OnRefreshListener listener){
        refreshView.setListener(listener);
    }

    // 设置自动刷新
    public void setAutoRefresh(){
        refreshView.onAutoRefresh();
    }

    public void stopRefresh(){
        refreshView.onFinishLoading();
    }

    public void refresh(){
        if (refreshModel == RECYCLER_BOTH || refreshModel == RECYCLER_LOAD){
            refreshView.setCanLoadMore(true);
        }
    }


    // 加载完成，没有更多数据
    public void noMoreData(){
        if (refreshModel == RECYCLER_BOTH || refreshModel == RECYCLER_LOAD){
            refreshView.setCanLoadMore(false);
        }
    }

    public void addEmptyView(){
        tvNodata.setVisibility(View.VISIBLE);
    }

    public void removeEmptyView(){
        tvNodata.setVisibility(View.GONE);
    }
}
