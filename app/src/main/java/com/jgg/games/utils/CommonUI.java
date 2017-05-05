package com.jgg.games.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.jgg.games.R;
import com.jgg.games.widget.pickerview.OptionsPickerDialog;
import com.jgg.games.widget.pickerview.TimePickerDialog;
import com.jgg.games.widget.pickerview.data.Type;
import com.jgg.games.widget.pickerview.listener.OnDateSetListener;
import com.jgg.games.widget.pickerview.listener.OnOptionsSetListener;
import com.jgg.rxretrofitlibrary.retrofit_rx.subscribers.CustomProgress;

/**
 * Created by Administrator on 2017/4/14 0014.
 * 有关控件的封装显示
 */

public class CommonUI {
    private static CustomProgress progress;


    private static  TimePickerDialog.Builder timePickerDialog(Context context, Type type,int doubleNum,OnDateSetListener back){
        TimePickerDialog.Builder dialog = new TimePickerDialog.Builder()
                .setType(type)
                .setCallBack(back)
                .setTitleSize(14)
                .setMinuteDoubleNum(doubleNum)
                .setCancelTextColor(ContextCompat.getColor(context,R.color.color_tv_purple_2f))
                .setSureTextColor(ContextCompat.getColor(context,R.color.color_tv_purple_2f))
                .setLineColor(ContextCompat.getColor(context,R.color.color_tv_gray_e2))
                .setThemeColor(ContextCompat.getColor(context,R.color.boxing_white))
                .setWheelItemTextNormalColor(ContextCompat.getColor(context,R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(ContextCompat.getColor(context,R.color.color_tv_purple_2f))
                .setCyclic(true);
        return dialog;
    }


    private static  OptionsPickerDialog.Builder optionPickerDialog(Context context, String[] list,OnOptionsSetListener back){
        OptionsPickerDialog.Builder dialog = new OptionsPickerDialog.Builder()
                .setCallBack(back)
                .setTitleSize(14)
                .setList(list)
                .setSelectSize(17)
                .setDefaultSize(14)
                .setCancelTextColor(ContextCompat.getColor(context,R.color.color_tv_purple_2f))
                .setSureTextColor(ContextCompat.getColor(context,R.color.color_tv_purple_2f))
                .setLineColor(ContextCompat.getColor(context,R.color.color_tv_gray_e2))
                .setThemeColor(ContextCompat.getColor(context,R.color.boxing_white))
                .setWheelItemTextNormalColor(ContextCompat.getColor(context,R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(ContextCompat.getColor(context,R.color.color_tv_purple_2f));
        return dialog;
    }

    /**
     * 显示年月日
     * @param context
     * @param back
     * @return
     */
    public static TimePickerDialog setYmdDialogStyle(Context context, OnDateSetListener back){
        return timePickerDialog(context,Type.YEAR_MONTH_DAY,1,back).build();
    }


    /**
     *
     * @param context
     * @param doubleNum 分钟选择间隔 10-20-30
     * @param back
     * @return
     */
    public static TimePickerDialog setMdhmDialogStyle(Context context,int doubleNum,OnDateSetListener back){
        return timePickerDialog(context,Type.MONTH_DAY_HOUR_MIN,10,back).setHourText("")
                .setMinuteText("").build();
    }

    /**
     * 选择单个数据   男女，或者城市
     * @param context
     * @param list
     * @param back
     * @return
     */
    public static OptionsPickerDialog setOptionsDialogStyle(Context context, String[] list, OnOptionsSetListener back){
        return optionPickerDialog(context,list,back).build();
    }

}
