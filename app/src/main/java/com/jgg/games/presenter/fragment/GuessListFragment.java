package com.jgg.games.presenter.fragment;

import android.view.View;
import android.widget.TextView;

import com.jgg.games.R;
import com.jgg.games.adapter.BetAdapter;
import com.jgg.games.adapter.GuessListAdapter;
import com.jgg.games.http.base.CommonCallback;
import com.jgg.games.model.entity.BetEntity;
import com.jgg.games.model.entity.BetsEntity;
import com.jgg.games.model.entity.CommonEntity;
import com.jgg.games.model.entity.MatchEntity;
import com.jgg.games.model.manager.BetManager;
import com.jgg.games.presenter.activity.GuessDetailActivity;
import com.jgg.games.presenter.base.PullRefreshFragment;
import com.jgg.games.recycleview.adapter.MultiItemTypeAdapter;
import com.jgg.games.utils.ToastUtil;
import com.jgg.games.view.delegate.GuessListDelegate;

import java.util.ArrayList;
import java.util.List;


/**
 * 竞猜详情--竞猜
 *
 */
public class GuessListFragment extends PullRefreshFragment<BetEntity,GuessListDelegate> {

    private MatchEntity matchEntity;
    private List<BetEntity> guessModelList = new ArrayList<>();
    private List<BetEntity> tempList = new ArrayList<>();

    private int BOround;

    @Override
    protected Class<GuessListDelegate> getDelegateClass() {
        return GuessListDelegate.class;
    }

    @Override
    protected void getIntentData() {
        if (getArguments() != null ){
            matchEntity = (MatchEntity) getArguments().getSerializable(GuessDetailActivity.MATCH_BEAN);
            if (matchEntity != null){
                BOround = matchEntity.getBORound();
            }
        }
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new GuessListAdapter(activity, R.layout.item_bill_head);
    }

    @Override
    protected void getData() {
        if (matchEntity == null){
            loadComplete();
            return;
        }
        BetManager.getInstance().getBetList(matchEntity.getId(), new CommonCallback<CommonEntity<BetsEntity>>() {
            @Override
            public void onSuccess(CommonEntity<BetsEntity> result) {
                guessModelList = result.getData().getBets();
                if (guessModelList != null && guessModelList.size()>0) {
                    for (BetEntity entity : guessModelList) {
                        if (entity.getStatus() == BetEntity.STATE_START) {
                            viewDelegate.setBottomVisible(true);
                            break;
                        }
                    }
                }
                if (BOround <2 || guessModelList == null ||guessModelList.size() == 0) {
                    viewDelegate.setTopVisible(false);
                    tempList.addAll(guessModelList);
                }else {
                    tempList.addAll(BetEntity.getBoList(guessModelList,0));
                    setBoView();
                }
                setList(tempList);
            }

            @Override
            public void onError(String error) {
                ToastUtil.showToast(error);
                loadComplete();
            }
        });
    }

    public void setBoView(){
        viewDelegate.setTopVisible(true);
        for (int i = 0; i <= BOround; i++) {
            TextView tvName = viewDelegate.addTop();
            final int position = i;
            viewDelegate.setName(tvName,i);
            tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setList(BetEntity.getBoList(guessModelList,position));
                }
            });
        }

    }

}
