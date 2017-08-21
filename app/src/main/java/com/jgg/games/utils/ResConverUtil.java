package com.jgg.games.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jgg.games.R;
import com.jgg.games.model.entity.MatchEntity;
import com.jgg.games.widget.countdownview.CountUtils;
import com.jgg.games.widget.countdownview.CountdownView;

/**
 * Created by Administrator on 2017/5/16 0016.
 * 资源转换工具
 */

public class ResConverUtil {

    // 首页背景
    public static int indexBetBg(Context mContext,String curTitle){
        int res = R.drawable.bg_dota;
        if (!StringUtil.isEmpty(curTitle)) {
            if (curTitle.contains(mContext.getResources().getString(R.string.index_wzry))) {
                res = R.drawable.bg_wzry;
            } else if (curTitle.contains(mContext.getResources().getString(R.string.index_yxlm))) {
                res = R.drawable.bg_yxlm;
            } else if (curTitle.contains(mContext.getResources().getString(R.string.index_csgo))) {
                res = R.drawable.bg_csgo;
            } else if (curTitle.contains(mContext.getResources().getString(R.string.index_xjzb))) {
                res = R.drawable.bg_xjzb;
            } else if (curTitle.contains(mContext.getResources().getString(R.string.index_swxf))) {
                res = R.drawable.bg_swxf;
            }
        }
        return res;
    }

    /**
     * 竞猜状态显示
     * @param mContext
     * @param state
     * @param tvScore
     * @param tvBtn
     * @param tvTime 时间显示
     * @param countdownView
     * @param countTime 倒计时时间
     */
    public static void indexBetState(final Context mContext, final int state, final TextView tvScore, TextView tvBtn, final TextView tvTime, final CountdownView countdownView, final long countTime){
        if (state == MatchEntity.STATE_END){
            countdownView.setVisibility(View.GONE);
            tvScore.setVisibility(View.VISIBLE);
            if (tvBtn != null) {
                tvBtn.setEnabled(false);
                tvBtn.setText(mContext.getResources().getString(R.string.bet_end));
            }
            tvTime.setVisibility(View.GONE);
            countdownView.stop();
        }else if (state == MatchEntity.STATE_BEFORE){
            tvScore.setVisibility(View.GONE);
            tvTime.setVisibility(View.GONE);
            countdownView.setVisibility(View.VISIBLE);
            if (tvBtn != null) {
                tvBtn.setEnabled(true);
                tvBtn.setText(mContext.getResources().getString(R.string.bet_ing));
            }

        }else if (state == MatchEntity.STATE_START){
            tvScore.setVisibility(View.VISIBLE);
            countdownView.setVisibility(View.GONE);
            tvTime.setVisibility(View.GONE);
            if (tvBtn != null) {
                tvBtn.setEnabled(false);
                tvBtn.setText(mContext.getResources().getString(R.string.bet_timeout));
            }
            countdownView.stop();
        }

        countdownView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                // 倒计时
                CountUtils.refreshTime(mContext,countdownView,tvTime,countTime,state);
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                countdownView.stop();
            }
        });
    }
}
