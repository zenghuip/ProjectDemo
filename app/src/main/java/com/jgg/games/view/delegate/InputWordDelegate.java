package com.jgg.games.view.delegate;

import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jgg.games.R;
import com.jgg.games.view.base.HeaderDelegate;

/**
 * Created by Administrator on 2017/4/13 0013.
 * 信息输入，修改昵称，或者其他
 */

public class InputWordDelegate extends HeaderDelegate {

    private ImageView ivClear;
    private EditText etContent;


    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_input_text;
    }

    @Override
    public void initWidget() {
        super.initWidget();

        etContent = get(R.id.et_content);
        ivClear = get(R.id.iv_clear);

    }

    /**
     * 初始数据
     * @param content
     * @param hint
     * @param limitLength 限制输入长度
     * @param line edittext 总行数
     */
    public void initContent(String content,int hint,int limitLength,int line){
        etContent.setText(content);
        etContent.setHint(getString(hint));
        etContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(limitLength)});
        etContent.setLines(line);
    }


    public void clear(){
        ivClear.setVisibility(View.GONE);
        etContent.setText("");
    }

    public String getContent(){
        return etContent.getText().toString().trim();
    }

    public void showClear(boolean show){
        if (show){
            ivClear.setVisibility(View.VISIBLE);
        }else {
            ivClear.setVisibility(View.GONE);
        }
    }
}
