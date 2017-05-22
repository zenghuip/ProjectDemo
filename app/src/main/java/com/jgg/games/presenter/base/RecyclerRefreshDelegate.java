package com.jgg.games.presenter.base;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.jgg.games.R;
import com.jgg.games.recycleview.adapter.MultiItemTypeAdapter;
import com.jgg.games.recycleview.wrapper.HeaderAndFooterWrapper;

/**
 * Created by Administrator on 2017/4/13 0013.
 *
 */

public abstract class RecyclerRefreshDelegate extends PullRefreshDelegate {
    public static final int HORIZONTAL_LISTVIEW = 0; // 水平列表
    public static final int VERTICAL_LISTVIEW = 1; // 垂直列表
    public static final int GRIDVIEW = 2; // 网格列表
    public static final int HORIZONTAL_STAGGEREDGRID = 3; // 水平瀑布列表
    public static final int VERTICAL_STAGGEREDGRID = 4; // 垂直瀑布列表

    public RecyclerView recyclerView;

    public HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private boolean showEmptyOfTitle; // 有头部时显示暂无数据要不要覆盖头部

    @Override
    public void initWidget() {
        super.initWidget();
        recyclerView = get(R.id.recycler_view);
    }

    @Override
    public void initValue() {
        super.initValue();
        setListType(VERTICAL_LISTVIEW,0);
    }

    // 设置recycleview类型 listview 或者GridView
    private void setRecyclerViewLayoutManager(int type,int spanCount){
        recyclerView.setHasFixedSize(true);
        switch (type){
            case HORIZONTAL_LISTVIEW:
                LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(layoutManager);
                break;
            case VERTICAL_LISTVIEW:
                LinearLayoutManager layoutManager2 = new LinearLayoutManager(this.getActivity());
                layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager2);
                break;
            case GRIDVIEW: // 要在adapter之前设置
                GridLayoutManager gManager = new GridLayoutManager(this.getActivity(),spanCount);
                recyclerView.setLayoutManager(gManager);
                break;
            case VERTICAL_STAGGEREDGRID:
                StaggeredGridLayoutManager sManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
                sManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(sManager);
                break;
            case HORIZONTAL_STAGGEREDGRID:
                StaggeredGridLayoutManager sManagerh = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.HORIZONTAL);
                sManagerh.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(sManagerh);
                break;
        }
    }

    public void setListType(int type,int spanCount){
        setRecyclerViewLayoutManager(type,spanCount);
    }


    public void setAdapter(MultiItemTypeAdapter adapter){
        if (mHeaderAndFooterWrapper != null){ // 是否有加头部
            recyclerView.setAdapter(mHeaderAndFooterWrapper);
        }else {
            recyclerView.setAdapter(adapter);
        }
    }



    public void addHeadView(View view,MultiItemTypeAdapter adapter){
        if (mHeaderAndFooterWrapper == null){
            mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
        }
        mHeaderAndFooterWrapper.addHeaderView(view);
    }

    public boolean showEmpty(){
        if (mHeaderAndFooterWrapper != null && !showEmptyOfTitle){
            return false;
        }
        return true;
    }

    public void setShowEmptyOfTitle(boolean showEmptyOfTitle) {
        this.showEmptyOfTitle = showEmptyOfTitle;
    }
}
