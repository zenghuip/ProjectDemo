package com.jgg.games.widget.pickerview.config;


import com.jgg.games.widget.pickerview.data.Type;
import com.jgg.games.widget.pickerview.data.WheelCalendar;
import com.jgg.games.widget.pickerview.listener.OnDateSetListener;
import com.jgg.games.widget.pickerview.listener.OnOptionsSetListener;

/**
 * Created by jzxiang on 16/5/15.
 */
public class PickerConfig {

    public Type mType = DefaultConfig.TYPE;
    public int mThemeColor = DefaultConfig.COLOR;
    public int mLineColor = DefaultConfig.COLOR;
    public int mCancelColor = DefaultConfig.COLOR;
    public int mSureColor = DefaultConfig.COLOR;
    public int mTitleSize = DefaultConfig.TITLE_SIZE;

    public String mCancelString = DefaultConfig.CANCEL;
    public String mSureString = DefaultConfig.SURE;
    public String mTitleString = DefaultConfig.TITLE;
    public int mToolBarTVColor = DefaultConfig.TOOLBAR_TV_COLOR;

    public int mWheelTVNormalColor = DefaultConfig.TV_NORMAL_COLOR;
    public int mWheelTVSelectorColor = DefaultConfig.TV_SELECTOR_COLOR;
    public int mWheelTVNormalSize = DefaultConfig.DEFAULT_SIZE;
    public int mWheelTVSelectorSize = DefaultConfig.SELECT_SIZE;
    public int mWheelTVSize = DefaultConfig.TV_SIZE;
    public boolean cyclic = DefaultConfig.CYCLIC;

    public String mYear = DefaultConfig.YEAR;
    public String mMonth = DefaultConfig.MONTH;
    public String mDay = DefaultConfig.DAY;
    public String mHour = DefaultConfig.HOUR;
    public String mMinute = DefaultConfig.MINUTE;

    public String[] list;

    public int minuteDoubleNum = DefaultConfig.MINUTE_DOUBLENUM;

    /**
     * The min timeMillseconds
     */
    public WheelCalendar mMinCalendar = new WheelCalendar(0);

    /**
     * The max timeMillseconds
     */
    public WheelCalendar mMaxCalendar = new WheelCalendar(0);

    /**
     * The default selector timeMillseconds
     */
    public WheelCalendar mCurrentCalendar = new WheelCalendar(System.currentTimeMillis());

    public OnDateSetListener mCallBack;
    public OnOptionsSetListener mOptionsCallBack;
}
