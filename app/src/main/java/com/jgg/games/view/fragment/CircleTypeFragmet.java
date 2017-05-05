package com.jgg.games.view.fragment;

import com.jgg.games.R;
import com.jgg.games.adapter.CircleTypeAdapter;
import com.jgg.games.adapter.base.CommonRecyclerAdapter;
import com.jgg.games.http.base.CommonCallback;
import com.jgg.games.model.entity.CommonEntity;
import com.jgg.games.model.entity.TiebaTypeEntity;
import com.jgg.games.model.manager.CircleManager;
import com.jgg.games.utils.ToastUtil;
import com.jgg.games.view.base.RecyclerRefreshFragment;
import com.jgg.games.view.delegate.CircleTypeFrgDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class CircleTypeFragmet extends RecyclerRefreshFragment<TiebaTypeEntity,CircleTypeFrgDelegate> {

    private List<TiebaTypeEntity> list = new ArrayList<>();
    private TiebaTypeEntity endTiebaType;

    @Override
    protected Class<CircleTypeFrgDelegate> getDelegateClass() {
        return CircleTypeFrgDelegate.class;
    }


    @Override
    protected CommonRecyclerAdapter getAdapter() {
        return new CircleTypeAdapter(activity,list, R.layout.item_circle_type);
    }

    @Override
    protected void getData() {
        CircleManager.getInstance(activity).getCircleType(new CommonCallback<CommonEntity<TiebaTypeEntity>>() {
            @Override
            public void onSuccess(CommonEntity<TiebaTypeEntity> result) {
                if (result.getData() != null && result.getData().getPostCategories() != null){
                    list = result.getData().getPostCategories();
                    setList(list);
                    viewDelegate.setListType(list);
                }

            }

            @Override
            public void onError(String error) {
                ToastUtil.showToast(error);
            }
        });

    }
}
