package com.jgg.games.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jgg.games.R;
import com.jgg.games.model.entity.MatchEntity;
import com.jgg.games.presenter.activity.GuessDetailActivity;
import com.jgg.games.recycleview.adapter.CommonRecyclerAdapter;
import com.jgg.games.recycleview.base.RecyclerViewHolder;
import com.jgg.games.utils.IntentUtils;
import com.jgg.games.utils.ResConverUtil;
import com.jgg.games.utils.StringUtil;
import com.jgg.games.utils.TimeUtil;
import com.jgg.games.widget.countdownview.CountdownView;

/**
 * Created by nd on 2017/3/25.
 */
public class BetAdapter extends CommonRecyclerAdapter<MatchEntity> {

    private String curTitle;

    public BetAdapter(Context context, int itemLayoutId,String curTitle) {
        super(context, itemLayoutId);
        this.curTitle = curTitle;
    }

    @Override
    protected void convert(RecyclerViewHolder viewHolder, final MatchEntity item, int position) {
        LinearLayout rlBg = viewHolder.getView(R.id.ly_bet_bg);
        rlBg.setBackgroundResource(ResConverUtil.indexBetBg(mContext,curTitle));
        viewHolder.setText(R.id.tv_time_date,item.getStringStartTime(TimeUtil.PATTERN_MMDD));
        viewHolder.setText(R.id.tv_time_num,item.getStringStartTime(TimeUtil.PATTERN_HHSS));
        viewHolder.setImageByUrl(R.id.iv_left_logo,item.getLefttTeamLogo());
        viewHolder.setImageByUrl(R.id.iv_right_logo,item.getRightTeamLogo());
        viewHolder.setText(R.id.tv_left_name, item.getLefttTeamName());
        viewHolder.setText(R.id.tv_right_name, item.getRightTeamName());
        viewHolder.setText(R.id.tv_name, item.getMatchName());

        TextView tvScore = viewHolder.getView(R.id.tv_score);
        TextView tvTime = viewHolder.getView(R.id.tv_time);
        CountdownView countdownView = viewHolder.getView(R.id.cv_time);
        ImageView ivVideo = viewHolder.getView(R.id.iv_video);
        TextView tvBtn = viewHolder.getView(R.id.tv_bet);
        int state = item.getState();
        if (StringUtil.isEmpty(item.getVideoURL()) || state == MatchEntity.STATE_START){
            ivVideo.setVisibility(View.GONE);
        }else {
            ivVideo.setVisibility(View.VISIBLE);
        }

        ResConverUtil.indexBetState(mContext,state,tvScore,tvBtn,tvTime,countdownView,item.getCountTime());

        if (countdownView != null && countdownView.getVisibility() == View.VISIBLE) {
            countdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
                @Override
                public void onEnd(CountdownView cv) {
                    notifyDataSetChanged();
                }
            });
        }

        viewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuessDetailActivity.startActivity(mContext,item);
            }
        },viewHolder.itemView);
    }

}
