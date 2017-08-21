package com.jgg.games.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jgg.games.R;
import com.jgg.games.model.entity.BetEntity;
import com.jgg.games.model.entity.MatchEntity;
import com.jgg.games.presenter.activity.GuessDetailActivity;
import com.jgg.games.recycleview.adapter.CommonRecyclerAdapter;
import com.jgg.games.recycleview.base.RecyclerViewHolder;
import com.jgg.games.utils.ResConverUtil;
import com.jgg.games.utils.StringUtil;
import com.jgg.games.utils.TimeUtil;
import com.jgg.games.widget.countdownview.CountdownView;

/**
 * Created by nd on 2017/3/25.
 */
public class GuessListAdapter extends CommonRecyclerAdapter<BetEntity> {

    public GuessListAdapter(Context context, int itemLayoutId) {
        super(context, itemLayoutId);
    }

    @Override
    protected void convert(RecyclerViewHolder viewHolder, final BetEntity item, int position) {

    }

}
