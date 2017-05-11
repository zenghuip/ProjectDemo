package com.jgg.games.adapter;

import android.content.Context;

import com.jgg.games.model.entity.MatchEntity;
import com.jgg.games.recycleview.adapter.CommonRecyclerAdapter;
import com.jgg.games.recycleview.base.RecyclerViewHolder;

import java.util.List;

/**
 * Created by nd on 2017/3/25.
 */
public class BetAdapter extends CommonRecyclerAdapter<MatchEntity> {


    public BetAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, MatchEntity matchEntity, int position) {

    }


}
