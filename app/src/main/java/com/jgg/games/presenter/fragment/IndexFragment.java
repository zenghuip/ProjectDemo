package com.jgg.games.presenter.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.jgg.games.R;
import com.jgg.games.http.base.CommonCallback;
import com.jgg.games.model.entity.CommonEntity;
import com.jgg.games.model.entity.GameTypeEntity;
import com.jgg.games.model.manager.BetManager;
import com.jgg.games.utils.ToastUtil;
import com.jgg.games.presenter.base.BaseFragment;
import com.jgg.games.view.delegate.IndexFrgDelegate;

import java.util.ArrayList;
import java.util.List;


/**
 * 首页
 *
 */
public class IndexFragment extends BaseFragment<IndexFrgDelegate> {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private List<GameTypeEntity> list = new ArrayList<>();


    @Override
    protected Class<IndexFrgDelegate> getDelegateClass() {
        return IndexFrgDelegate.class;
    }


    @Override
    protected void onLazyLoad() {
        BetManager.getInstance().getGameType(new CommonCallback<CommonEntity<GameTypeEntity>>() {
            @Override
            public void onSuccess(CommonEntity<GameTypeEntity> result) {
                if (result.getData() != null) {
                    list = result.getData().getGameCategorys();
                    if (list == null || list.size() == 0) {
                        viewDelegate.setTabVisible(false);
                    } else {
                        viewDelegate.setTabVisible(true);
                        ArrayList<String> names = new ArrayList<>();
                        for (int i = 0; i < list.size(); i++) {
                            GameTypeEntity entry = list.get(i);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(IndexBetFragment.GAME, entry);
                            IndexBetFragment indexFragment = new IndexBetFragment();
                            indexFragment.setArguments(bundle);
                            mFragments.add(indexFragment);
                            names.add(entry.getName());
                        }
                        viewDelegate.setViewAdapter(names, mFragments);
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
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.setOnClickListener(this, R.id.tv_rank);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_rank:
                break;
        }
    }
}
