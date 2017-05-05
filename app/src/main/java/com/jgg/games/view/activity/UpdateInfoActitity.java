package com.jgg.games.view.activity;

import android.os.Bundle;
import android.view.View;

import com.jgg.games.base.AppManager;
import com.jgg.games.event.EventBusManager;
import com.jgg.games.http.base.CommonCallback;
import com.jgg.games.model.entity.BaseCodeEntity;
import com.jgg.games.model.entity.CommonEntity;
import com.jgg.games.model.manager.UserManager;
import com.jgg.games.widget.pickerview.OptionsPickerDialog;
import com.jgg.games.widget.pickerview.TimePickerDialog;
import com.jgg.games.widget.pickerview.listener.OnDateSetListener;
import com.jgg.games.widget.pickerview.listener.OnOptionsSetListener;
import com.jgg.games.utils.CommonUI;
import com.jgg.games.utils.IntentUtils;
import com.jgg.games.utils.StringUtils;
import com.jgg.games.utils.ToastUtil;
import com.jgg.games.view.base.BaseFragmentActivity;
import com.jgg.games.view.fragment.UpdateNameFragment;

import java.util.List;

import com.jgg.games.R;
import com.jgg.games.model.entity.UserEntity;
import com.jgg.games.utils.ImageUtil;
import com.jgg.games.utils.SharedPreUtil;
import com.jgg.games.view.base.ImageActivity;
import com.jgg.games.view.delegate.UserInfoDelegate;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class UpdateInfoActitity extends ImageActivity<UserInfoDelegate> {
    public static final String FROM = "from";
    public static final String LOGIN = "login";
    public static final String MINE = "mine";

    private UserEntity user;
    private  String[] sexs;
    private String name,phone;
    private int sex;
    private long birth;
    private String from;


    @Override
    protected Class<UserInfoDelegate> getDelegateClass() {
        return UserInfoDelegate.class;
    }

    @Override
    protected void initValue() {
        EventBus.getDefault().register(this);
        super.initValue();
        if (getIntent() != null){
            from = getIntent().getStringExtra(FROM);
        }
        // 查询所有用户
        user = SharedPreUtil.getUser();
        viewDelegate.setInitData(user);
        sex = user.getSex();
        birth = user.getBirth();
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.setOnClickListener(this,R.id.iv_head,R.id.tv_name,R.id.tv_birth,R.id.tv_sex,R.id.tv_tel,R.id.tv_save);
    }


    @Override
    public void onClick(final View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_head:
                ImageUtil.intentSingle(this,true,true,SINGLE_CODE);
                break;

            case R.id.tv_name:
                Bundle bundle = new Bundle();
                bundle.putString(UpdateNameFragment.CONTENT,viewDelegate.getName());
                BaseFragmentActivity.startActivity(this,UpdateNameFragment.class,bundle);

                break;

            case R.id.tv_birth:
                CommonUI.setYmdDialogStyle(this, new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        birth = millseconds / 1000;
                        viewDelegate.setBirth(String.valueOf(millseconds));
                    }
                }).show(getSupportFragmentManager(), "time");
                break;

            case R.id.tv_sex:
                sexs= getResources().getStringArray(R.array.sex);
                CommonUI.setOptionsDialogStyle(this, sexs, new OnOptionsSetListener() {
                    @Override
                    public void onDateSet(OptionsPickerDialog optionsPickerView, String result,int position) {
                        sex = position;
                        viewDelegate.setSex(position);
                    }
                }).show(getSupportFragmentManager(),"option");
                break;

            case R.id.tv_tel:

                break;

            case R.id.tv_save:
                name = viewDelegate.getName();
                if (StringUtils.isEmptyNotNull(name)){
                    ToastUtil.showToast(R.string.text_myinfo_name_hint);
                    return;
                }
                if (name.length()>6){
                    ToastUtil.showToast(R.string.text_myinfo_name_lenght);
                    return;
                }
                phone = viewDelegate.getPhone();
                postImg();
                break;
        }
    }


    @Override
    public void setImage(List<String> url) {
        if (url != null && url.size()>0) {
            viewDelegate.setHeadImg(url.get(0));
        }
    }

    @Override
    public void doPost() {
        UserManager.getInstance(this).updateUserInfo(name, headUrl,  sex, birth, new CommonCallback<CommonEntity<BaseCodeEntity>>() {
            @Override
            public void onSuccess(CommonEntity<BaseCodeEntity> result) {
                BaseCodeEntity data = result.getData().getUpdateUserInformation();
                if (data != null ){
                    if(data.getStatus() == 0){
                        UserManager.getInstance(UpdateInfoActitity.this).getUserInfo();
                        if (from.equals(LOGIN)){
                            AppManager.getAppManager().finishActivity(LoginActivity.class);
                            IntentUtils.intentNoparams(UpdateInfoActitity.this, MainActivity.class);
                            SharedPreUtil.setIslogin(true);
                        }
                        finish();
                    }else {
                        ToastUtil.showToast(data.getMsg());
                    }
                }
            }

            @Override
            public void onError(String error) {
                ToastUtil.showToast(error);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setContent(EventBusManager.NotifyInputText event) {
        viewDelegate.setName(event.content);
    }
}
