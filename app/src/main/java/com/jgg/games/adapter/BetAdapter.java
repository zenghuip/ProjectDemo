package com.jgg.games.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.jgg.games.R;
import com.jgg.games.adapter.base.CommonRecyclerAdapter;
import com.jgg.games.adapter.base.RecyclerViewHolder;
import com.jgg.games.model.entity.MatchEntity;
import com.jgg.games.model.entity.TiebaTypeEntity;
import com.jgg.games.utils.ConvertUtils;

import java.util.List;

/**
 * Created by nd on 2017/3/25.
 */
public class BetAdapter extends CommonRecyclerAdapter<MatchEntity> {


    public BetAdapter(Context context, List<MatchEntity> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder viewHolder, MatchEntity item) {

    }


}
