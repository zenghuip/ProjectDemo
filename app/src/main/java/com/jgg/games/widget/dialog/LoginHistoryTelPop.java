package com.jgg.games.widget.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.jgg.games.R;
import com.jgg.games.callback.OnChooseTelCallBack;
import com.jgg.games.model.entity.UserEntity;
import com.jgg.games.recycleview.adapter.CommonAdapter;
import com.jgg.games.recycleview.base.ViewHolder;
import com.jgg.games.widget.dialog.base.BasePopWindow;
import com.jgg.rxretrofitlibrary.retrofit_rx.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/22 0022.
 * 登录的历史号码
 */

public class LoginHistoryTelPop extends BasePopWindow {

    private List<UserEntity> telList;
    private CommonAdapter<UserEntity> adapter;
    private OnChooseTelCallBack callBack;

    private ListView listView;

    public LoginHistoryTelPop(Context context,OnChooseTelCallBack callBack) {
        super(context);
        this.callBack = callBack;


    }

    @Override
    public int getLayoutId() {
        return R.layout.pop_historytel;
    }

    @Override
    public void initView() {
        listView = findView(R.id.lv_phone);
    }

    @Override
    public void initValue() {
        telList = DatabaseManager.getInstance().getQueryAll(UserEntity.class);
        if (telList == null){
            telList = new ArrayList<>();
        }
        adapter = new CommonAdapter<UserEntity>(context,R.layout.item_pop_historytel,telList) {
            @Override
            public void convert(ViewHolder helper, final UserEntity item) {
                helper.setText(R.id.tv_phone,item.getTelephone());
                helper.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.getId() == R.id.iv_clear) {
                            telList.remove(item);
                            DatabaseManager.getInstance().delete(item);
                            notifyDataSetChanged();
                        }else {
                            if (callBack != null){
                                callBack.onChooseTel(item.getTelephone());
                            }
                            dismiss();
                        }

                    }
                },R.id.iv_clear,R.id.tv_phone);

            }
        };

        listView.setAdapter(adapter);
    }

    @Override
    public void initEvent() {

    }


    public void show(View view){
        if (telList != null || telList.size()>0){
            showAsDropDown(view);
        }
    }

}
