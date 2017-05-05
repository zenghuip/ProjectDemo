package com.jgg.games.widget.pickerview;

import android.content.Context;
import android.view.View;

import com.jgg.games.R;
import com.jgg.games.widget.pickerview.adapter.ArrayWheelAdapter;
import com.jgg.games.widget.pickerview.config.PickerConfig;
import com.jgg.games.widget.pickerview.wheel.OnWheelChangedListener;
import com.jgg.games.widget.pickerview.wheel.WheelView;


/**
 * Created by jzxiang on 16/4/20.
 */
public class OptionsWheel {
    Context mContext;

    WheelView picker;
    ArrayWheelAdapter adapter;

    PickerConfig mPickerConfig;
    OnWheelChangedListener pickerListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            updateData();
        }
    };


    public OptionsWheel(View view, PickerConfig pickerConfig) {
        mPickerConfig = pickerConfig;

        mContext = view.getContext();
        initialize(view);
    }

    public void initialize(View view) {
        initView(view);
        initYear();
    }


    void initView(View view) {
        picker = (WheelView) view.findViewById(R.id.options);
        picker.addChangingListener(pickerListener);
    }

    void initYear() {


        adapter = new ArrayWheelAdapter(mContext, mPickerConfig.list);
        adapter.setConfig(mPickerConfig);
        picker.setViewAdapter(adapter);
        if (mPickerConfig.list.length>0) {
            picker.setCurrentItem(mPickerConfig.list.length/2);
        }
    }



    void updateData() {

        adapter = new ArrayWheelAdapter(mContext, mPickerConfig.list);
        adapter.setConfig(mPickerConfig);
        picker.setViewAdapter(adapter);
    }


    public String getCurrentString() {
        return adapter.getItemText(picker.getCurrentItem()).toString();
    }

    public int getCurrentItem() {
        return picker.getCurrentItem();
    }

}
