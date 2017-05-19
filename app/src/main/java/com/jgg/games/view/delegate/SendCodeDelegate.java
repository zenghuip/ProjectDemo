package com.jgg.games.view.delegate;

import android.widget.EditText;
import android.widget.TextView;

import com.jgg.games.R;
import com.jgg.games.presenter.base.HeaderDelegate;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public abstract class SendCodeDelegate extends HeaderDelegate {

    private EditText etPhone;
    private EditText etCode;
    private TextView tvGetCode;



    @Override
    public void initWidget() {
        super.initWidget();
        etPhone = get(R.id.et_phone);
        etCode = get(R.id.et_code);
        tvGetCode = get(R.id.tv_getcode);
    }

    public String getPhone(){
        return etPhone.getText().toString().trim();
    }


    public String getCode(){
        return etCode.getText().toString().trim();
    }


    // 设置验证码按钮
    public void setCode(String code){
        tvGetCode.setText(code);
    }

    public void setCodeEnable(boolean enable){
        tvGetCode.setEnabled(enable);
    }

    public void setPhone(String phone){
        etPhone.setText(phone);
    }

}
