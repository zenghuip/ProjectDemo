package com.jgg.games.view.fragment;

import android.view.View;

import com.jgg.games.R;
import com.jgg.games.event.EventBusManager;
import com.jgg.games.utils.StringUtil;
import com.jgg.games.utils.ToastUtil;
import com.jgg.games.view.base.BaseFragment;
import com.jgg.games.view.delegate.InputWordDelegate;

import org.greenrobot.eventbus.EventBus;


/**
 * 修改昵称
 * @author android2
 *
 */
public class UpdateNameFragment extends BaseFragment<InputWordDelegate> {
    public static final String CONTENT = "content";
    private String content;

    @Override
    protected void initValue() {
        super.initValue();
        viewDelegate.setTitleBackRight(R.string.text_myinfo_update,R.string.confirm);
        if (getArguments() != null){
            content = getArguments().getString(CONTENT);
            viewDelegate.initContent(content,R.string.text_myinfo_name_hint,7,1);
        }
    }

    @Override
    protected Class<InputWordDelegate> getDelegateClass() {
        return InputWordDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.setOnClickListener(this,R.id.tv_right);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_right:
                String content = viewDelegate.getContent();
                if (StringUtil.isEmptyNotNull(content)){
                    ToastUtil.showToast(R.string.text_myinfo_name_hint);
                    return;
                }

                EventBusManager.NotifyInputText inputText = new EventBusManager.NotifyInputText();
                inputText.content = content;
                EventBus.getDefault().post(inputText);
                viewDelegate.finish();

                break;
        }
    }
}
