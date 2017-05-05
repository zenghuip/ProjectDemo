package com.jgg.games.view.base;

import com.jgg.games.adapter.base.CommonAdapter;
import com.jgg.refreshview.XRefreshView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/20 0020.
 * listview 刷新加载
 */

public abstract class ListRefreshFragment<T,E extends ListRefreshDelegate>  extends BaseFragment<E>{

    private CommonAdapter<T> adapter;
    private List<T> mList = new ArrayList<>();

    protected int mCurrentPage = 0; // offset
    protected int curPage = 0; // 当前页面
    protected int mPageCount = 10; // limit
    public boolean isRefresh = true;


    @Override
    protected void initValue() {
        adapter = getAdapter();
        viewDelegate.setFootView();
        viewDelegate.setRefreshViewStyle();
        viewDelegate.setAdapter(adapter);
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.setRefreshOrLoadListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {
                reset();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                isRefresh = false;
                curPage++;
                loadMore();
            }
        });
    }

    public void reset() {
        isRefresh = true;
        curPage = 0;
        loadMore();
    }

    public void loadMore() {
        mCurrentPage = curPage* mPageCount;
        getData();
    }

    public void setList(List<T> list) {
        if (list == null){
            return;
        }
        if (isRefresh) {
            mList.clear();
        }
        mList.addAll(list);
        adapter.setDatas(mList);
        if (viewDelegate.refreshModel == viewDelegate.RECYCLER_BOTH || viewDelegate.refreshModel == viewDelegate.RECYCLER_LOAD) {
            if (list.size() < mPageCount) {
                viewDelegate.loadComplete();
            }
        }

        loadComplete();
    }

    public void loadComplete() {
        viewDelegate.stopRefresh();
        viewDelegate.stopLoadMore(true);
    }

    protected abstract CommonAdapter getAdapter();
    protected abstract void getData();
}
