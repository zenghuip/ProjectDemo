package com.jgg.games.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class DisplayUtil {
    private static int mScreenWidth = 0;
    private static int mScreenHeight = 0;

    public static int getScreenWidth(Context context) {
        if (mScreenWidth == 0) {
            mScreenWidth = context.getResources().getDisplayMetrics().widthPixels;
        }
        return mScreenWidth;
    }


    /**
     * 取得屏幕宽度
     *
     * @param activity activity上下文
     * @return 屏幕宽度
     */
    public static int getScreenHeight(Activity activity) {
        if (mScreenHeight == 0) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            mScreenHeight = dm.heightPixels;
        }
        return mScreenHeight;
    }

}
