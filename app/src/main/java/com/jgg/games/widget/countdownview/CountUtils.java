package com.jgg.games.widget.countdownview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jgg.games.R;
import com.jgg.games.utils.TimeUtil;

/**
 */
public class CountUtils {

    public static int dp2px(Context context, float dpValue) {
        if (dpValue <= 0) return 0;
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static float sp2px(Context context, float spValue) {
        if (spValue <= 0) return 0;
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return spValue * scale;
    }

    public static String formatNum(int time) {
        return time < 10 ? "0" + time : String.valueOf(time);
    }

    public static String formatMillisecond(int millisecond) {
        String retMillisecondStr;

        if (millisecond > 99) {
            retMillisecondStr = String.valueOf(millisecond / 10);
        } else if (millisecond <= 9) {
            retMillisecondStr = "0" + millisecond;
        } else {
            retMillisecondStr = String.valueOf(millisecond);
        }

        return retMillisecondStr;
    }

    public static void refreshTime(Context context,CountdownView mTvOptionTime, TextView tvTime,long countTime){
        DynamicConfig.Builder config = new DynamicConfig.Builder();
        long timeResult = countTime;

        if (timeResult > 0) {
            mTvOptionTime.start(timeResult);
            mTvOptionTime.setVisibility(View.VISIBLE);
            tvTime.setVisibility(View.GONE);
            if (timeResult > TimeUtil.ONE_WEEK) {
                mTvOptionTime.setVisibility(View.GONE);
                tvTime.setVisibility(View.VISIBLE);
                tvTime.setText(context.getString(R.string.one_week));
            } else if (timeResult > TimeUtil.ONE_DAY) {
                //一天
                config.setShowDay(true);
                config.setShowHour(true);
                config.setShowMinute(true);
                config.setShowSecond(false);
                //一个小时
            } else if (timeResult > TimeUtil.ONE_HOUR) {
                config.setShowDay(false);
                config.setShowHour(true);
                config.setShowMinute(true);
                config.setShowSecond(true);
            } else {
                config.setShowDay(false);
                config.setShowHour(true);
                config.setShowMinute(true);
                config.setShowSecond(true);
            }
            mTvOptionTime.dynamicShow(config.build());
        } else {
            mTvOptionTime.stop();
            mTvOptionTime.setVisibility(View.GONE);
        }
    }


}
