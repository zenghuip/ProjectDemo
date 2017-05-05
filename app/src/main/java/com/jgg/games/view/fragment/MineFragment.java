package com.jgg.games.view.fragment;

import android.view.View;

import com.jgg.games.R;
import com.jgg.games.event.EventBusManager;
import com.jgg.games.utils.IntentUtils;
import com.jgg.games.view.activity.UpdateInfoActitity;
import com.jgg.games.view.base.BaseFragment;
import com.jgg.games.view.delegate.MineFrgDelegate;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 *  我的
 * @author android2
 *
 */
public class MineFragment extends BaseFragment<MineFrgDelegate> {

    @Override
    protected Class<MineFrgDelegate> getDelegateClass() {
        return MineFrgDelegate.class;
    }

    @Override
    protected void initView() {
        super.initView();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.setOnClickListener(this,R.id.iv_head,R.id.tv_get_gold,R.id.tv_mine_guess,R.id.tv_mine_shop,R.id.tv_mine_activity,R.id.tv_mine_task,R.id.tv_mine_tool);
    }

    @Override
    protected void initValue() {
        super.initValue();
        viewDelegate.initData();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_head:
                IntentUtils.intentParam(activity,UpdateInfoActitity.class,UpdateInfoActitity.FROM,UpdateInfoActitity.MINE);
                break;
            case R.id.tv_get_gold:

                break;
            case R.id.tv_mine_guess:
                break;
            case R.id.tv_mine_task:
                break;
            case R.id.tv_mine_shop:
                break;
            case R.id.tv_mine_activity:
                break;
            case R.id.tv_mine_tool:
                break;


        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setContent(EventBusManager.NotifyUser event) {
        viewDelegate.initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
