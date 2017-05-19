package com.jgg.games.view.delegate;

import android.support.v7.widget.GridLayoutManager;

import com.jgg.games.R;
import com.jgg.games.model.entity.TiebaTypeEntity;
import com.jgg.games.presenter.base.RecyclerRefreshDelegate;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13 0013.
 * 信息输入，修改昵称，或者其他
 */

public class CircleTypeFrgDelegate extends RecyclerRefreshDelegate {


    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_common_refresh;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        onlyTitle(R.string.fragment_title_4);
    }

    @Override
    public void initValue() {
        super.initValue();
        setListType(GRIDVIEW,2);
        setRefreshModel(RECYCLER_REFRESH);
    }


    // 最后一格只显示yil
    public void setListType(final List<TiebaTypeEntity> listType) {
        GridLayoutManager gManager = new GridLayoutManager(this.getActivity(),2);
        gManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position == listType.size()-1){
                    return 2;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(gManager);
    }
}
