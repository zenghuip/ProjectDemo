package com.jgg.games.presenter.base;

import android.os.Handler;

import com.jgg.games.pullrefreshview.PullToRefreshView;
import com.jgg.games.recycleview.adapter.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class PullRefreshFragment<T,E extends RecyclerRefreshDelegate>  extends BaseFragment<E> {

    private MultiItemTypeAdapter<T> adapter;
    private List<T> mList = new ArrayList<>();

    protected int mCurrentPage = 0; // offset
    private int curPage = 0; // 当前页面
    protected int mPageCount = 10; // limit
    public boolean isRefresh = true;


    @Override
    protected void onLazyLoad() {
        if (viewDelegate.reset){
            reset();
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    viewDelegate.setAutoRefresh();
                }
            }, 500);
        }

    }

    @Override
    protected void initView() {
        getIntentData();
        super.initView();
        adapter = getAdapter();
        viewDelegate.initValue();
        addHeadView();
        viewDelegate.setAdapter(adapter);
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.setRefreshOrLoadListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reset();
            }

            @Override
            public void onLoadMore() {
                isRefresh = false;

                loadMore();

            }
        });
    }

    public void reset() {
        isRefresh = true;
        viewDelegate.refresh();
        curPage = 0;
        loadMore();
    }

    public void loadMore() {
        if (!viewDelegate.havaNet()){
            loadComplete();
            return;
        }
        if (!isRefresh){
            curPage++;
        }
        mCurrentPage = curPage* mPageCount;
        getData();
    }


    public void setList(List<T> list) {
        if (list == null){
            return;
        }
        if (isRefresh) {
            mList.clear();
            adapter.clearData();
            if (list.size() == 0 && viewDelegate.showEmpty()){
                viewDelegate.addEmptyView();
            }else {
                viewDelegate.removeEmptyView();
            }
        }
        mList.addAll(list);
        notifyData();

        if (list.size() < mPageCount) {
            viewDelegate.noMoreData();
        }
        loadComplete();
    }

    public void notifyData(){
        adapter.setDatas(mList);

    }


    public void loadComplete() {
        viewDelegate.stopRefresh();
    }

    protected abstract MultiItemTypeAdapter getAdapter();
    protected abstract void getData();

    protected void getIntentData(){

    }

    // 加头部  可加可不加
    protected void addHeadView(){

    }
}