package com.jgg.games.view.base;

import android.view.View;

import com.jgg.games.R;
import com.jgg.refreshview.XRefreshView;
import com.jgg.refreshview.XRefreshViewFooter;
import com.jgg.refreshview.recyclerview.BaseRecyclerAdapter;

/**
 * Created by Administrator on 2017/4/13 0013.
 *
 */

public abstract class PullToRefreshDelegate extends HeaderDelegate {

    public static final int RECYCLER_BOTH = 0; // 设置刷新加载
    public static final int RECYCLER_DISABLE = 1; //  不刷新不加载
    public static final int RECYCLER_REFRESH = 2; // 只刷新
    public static final int RECYCLER_LOAD = 3; // 只加载

    private XRefreshView xRefreshView;
    public int refreshModel = RECYCLER_BOTH;

    @Override
    public void initWidget() {
        super.initWidget();
        xRefreshView = get(R.id.xf_view);
    }

    public void setRefreshViewStyle(){
        //设置刷新完成以后，headerview固定的时间
        xRefreshView.setPinnedTime(500);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setPullRefreshEnable(true);
        //设置在下拉刷新被禁用的情况下，是否允许界面被下拉,默认是true
        xRefreshView.setMoveHeadWhenDisablePullRefresh(false);
        //设置在被刷新的view滑倒最底部的时候，是否允许被刷新的view继续往上滑动，默认是true
        xRefreshView.enableRecyclerViewPullUp(false);
        setRefreshModel(refreshModel);
    }

    // 设置recycler 类型
    public void setRefreshModel(int refreshModel) {
        this.refreshModel = refreshModel;
        switch (refreshModel) {
            case RECYCLER_BOTH:
                xRefreshView.setPullLoadEnable(true);
                xRefreshView.setPullRefreshEnable(true);
                break;
            case RECYCLER_DISABLE:
                xRefreshView.setPullLoadEnable(false);
                xRefreshView.setPullRefreshEnable(false);
                break;
            case RECYCLER_REFRESH:
                xRefreshView.setPullLoadEnable(false);
                xRefreshView.setPullRefreshEnable(true);
                break;
            case RECYCLER_LOAD:
                xRefreshView.setPullLoadEnable(true);
                xRefreshView.setPullRefreshEnable(false);
                break;
        }
    }

    // 设置刷新加载监听
    public void setRefreshOrLoadListener(XRefreshView.SimpleXRefreshListener listener){
        xRefreshView.setXRefreshViewListener(listener);
    }

    public void stopRefresh(){
        xRefreshView.stopRefresh();
    }

    /**
     * 加载是否成功 false 会提示加载失败重新加载（网络请求失败的情况）
     * @param isSuc
     */
    public void stopLoadMore(boolean isSuc){
        xRefreshView.stopLoadMore(isSuc);
    }

    // 加载完成，没有更多数据
    public void loadComplete(){
        xRefreshView.setLoadComplete(true);
    }

    // 设置空布局
    public void setEmptyView(int layout){
        xRefreshView.setEmptyView(layout);
    }

    private void setEmptyViewClickListener(XRefreshView xRefreshView, View.OnClickListener listener) {
        View emptyView = xRefreshView.getEmptyView();
        if (emptyView != null) {
            emptyView.setOnClickListener(listener);
        }
    }

    public void setFootView(){
        xRefreshView.setCustomFooterView(new XRefreshViewFooter(this.getActivity()));
    }


}
