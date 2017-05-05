package com.jgg.games.widget.pickerview;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jgg.games.R;
import com.jgg.games.widget.pickerview.config.PickerConfig;
import com.jgg.games.widget.pickerview.listener.OnOptionsSetListener;


/**
 * Created by jzxiang on 16/4/19.
 */
public class OptionsPickerDialog extends DialogFragment implements View.OnClickListener {
    PickerConfig mPickerConfig;
    private OptionsWheel mTimeWheel;

    private static OptionsPickerDialog newIntance(PickerConfig pickerConfig) {
        OptionsPickerDialog timePickerDialog = new OptionsPickerDialog();
        timePickerDialog.initialize(pickerConfig);
        return timePickerDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    @Override
    public void onResume() {
        super.onResume();
        int height = getResources().getDimensionPixelSize(R.dimen.picker_height);

        Window window = getDialog().getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, height);//Here!
        window.setGravity(Gravity.BOTTOM);
    }

    private void initialize(PickerConfig pickerConfig) {
        mPickerConfig = pickerConfig;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.dialog_notitle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(initView());
        return dialog;
    }

    View initView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.picker_options_layout, null);
        TextView cancel = (TextView) view.findViewById(R.id.tv_cancel);
        cancel.setOnClickListener(this);
        TextView sure = (TextView) view.findViewById(R.id.tv_sure);
        sure.setOnClickListener(this);
        TextView title = (TextView) view.findViewById(R.id.tv_title);
        View toolbar = view.findViewById(R.id.toolbar);

        title.setText(mPickerConfig.mTitleString);
        cancel.setText(mPickerConfig.mCancelString);
        sure.setText(mPickerConfig.mSureString);
        toolbar.setBackgroundColor(mPickerConfig.mThemeColor);
        cancel.setTextColor(mPickerConfig.mCancelColor);
        sure.setTextColor(mPickerConfig.mSureColor);
        sure.setTextSize(mPickerConfig.mTitleSize);
        cancel.setTextSize(mPickerConfig.mTitleSize);

        mTimeWheel = new OptionsWheel(view, mPickerConfig);
        return view;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_cancel) {
            dismiss();
        } else if (i == R.id.tv_sure) {
            sureClicked();
        }
    }
    

    void sureClicked() {

        if (mPickerConfig.mOptionsCallBack != null) {
            mPickerConfig.mOptionsCallBack.onDateSet(this, mTimeWheel.getCurrentString(),mTimeWheel.getCurrentItem());
        }
        dismiss();
    }

    public static class Builder {
        PickerConfig mPickerConfig;

        public Builder() {
            mPickerConfig = new PickerConfig();
        }



        public Builder setThemeColor(int color) {
            mPickerConfig.mThemeColor = color;
            return this;
        }


        public Builder setList(String[] list) {
            mPickerConfig.list = list;
            return this;
        }



        public Builder setLineColor(int color) {
            mPickerConfig.mLineColor = color;
            return this;
        }
        public Builder setTitleSize(int size) {
            mPickerConfig.mTitleSize = size;
            return this;
        }
        public Builder setSelectSize(int size) {
            mPickerConfig.mWheelTVSelectorSize = size;
            return this;
        }
        public Builder setDefaultSize(int size) {
            mPickerConfig.mWheelTVNormalSize = size;
            return this;
        }


        public Builder setCancelStringId(String left) {
            mPickerConfig.mCancelString = left;
            return this;
        }

        public Builder setSureStringId(String right) {
            mPickerConfig.mSureString = right;
            return this;
        }

        public Builder setTitleStringId(String title) {
            mPickerConfig.mTitleString = title;
            return this;
        }

        public Builder setToolBarTextColor(int color) {
            mPickerConfig.mToolBarTVColor = color;
            return this;
        }

        public Builder setCancelTextColor(int color) {
            mPickerConfig.mCancelColor = color;
            return this;
        }

        public Builder setSureTextColor(int color) {
            mPickerConfig.mSureColor = color;
            return this;
        }

        public Builder setWheelItemTextNormalColor(int color) {
            mPickerConfig.mWheelTVNormalColor = color;
            return this;
        }

        public Builder setWheelItemTextSelectorColor(int color) {
            mPickerConfig.mWheelTVSelectorColor = color;
            return this;
        }

        public Builder setWheelItemTextSize(int size) {
            mPickerConfig.mWheelTVSize = size;
            return this;
        }

        public Builder setCallBack(OnOptionsSetListener listener) {
            mPickerConfig.mOptionsCallBack = listener;
            return this;
        }

        public OptionsPickerDialog build() {
            return newIntance(mPickerConfig);
        }

    }


}
