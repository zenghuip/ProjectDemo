package com.jgg.games.view.delegate;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jgg.games.R;
import com.jgg.games.model.entity.UserEntity;
import com.jgg.games.utils.ImageUtil;
import com.jgg.games.utils.TimeUtils;
import com.jgg.games.view.base.HeaderDelegate;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class UserInfoDelegate extends HeaderDelegate {

    private RelativeLayout rlHead;
    private ImageView ivHead;
    private TextView tvName;
    private TextView tvSex;
    private TextView tvPhone;
    private TextView tvBirth;
    private TextView tvSave;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_update_info;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        setTitleAndBack(R.string.myinfo_title);
        rlHead = get(R.id.rl_myinfo_head);
        tvName = get(R.id.tv_name);
        tvSex = get(R.id.tv_sex);
        tvPhone = get(R.id.tv_tel);
        tvBirth = get(R.id.tv_birth);
        tvSave = get(R.id.tv_save);
        ivHead = get(R.id.iv_head);
    }

    /**
     * 初始化数据
     * @param user
     */
    public void setInitData(UserEntity user){
        if (user != null){
            setName(user.getName());
            setSex(user.getSex());
            setPhone(user.getTelephone());
            setBirth(String.valueOf(user.getLongBirthday()*1000));
        }
        setHeadImg(user.getAvatar());
    }

    public void setName(String name){
        tvName.setText(name);
    }

    public String getName(){
        return tvName.getText().toString();
    }

    public String getPhone(){
        return tvPhone.getText().toString();
    }

    public void setPhone(String phone){
        tvPhone.setText(phone);
    }

    public void setSex(int sex){
        if (sex == 1){
            tvSex.setText(getString(R.string.user_info_sex_boy));
        }else if (sex == 2){
            tvSex.setText(getString(R.string.user_info_sex_girl));
        }else {
            tvSex.setText(getString(R.string.user_info_sex_sec));
        }
    }

    public void setBirth(String birth){
        tvBirth.setText(TimeUtils.millis2String(birth,TimeUtils.PATTERN_YEAR_MONTH_DAY));
    }

    public void setHeadImg(String url){
        ImageUtil.displayCircleImage(url,ivHead);
    }

}
