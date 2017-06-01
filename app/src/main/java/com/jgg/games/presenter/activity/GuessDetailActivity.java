package com.jgg.games.presenter.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.jgg.games.R;
import com.jgg.games.callback.AppBarStateChangeListener;
import com.jgg.games.http.base.CommonCallback;
import com.jgg.games.model.entity.AnnouncementEntity;
import com.jgg.games.model.entity.CommonEntity;
import com.jgg.games.model.entity.GameTypeEntity;
import com.jgg.games.model.entity.MatchEntity;
import com.jgg.games.model.manager.BetManager;
import com.jgg.games.presenter.base.BaseTabActitity;
import com.jgg.games.presenter.fragment.EmptyFragment;
import com.jgg.games.presenter.fragment.IndexBetFragment;
import com.jgg.games.utils.ToastUtil;
import com.jgg.games.view.delegate.GuessDetailDelegate;
import com.jgg.games.widget.MarqueeFactory;
import com.jgg.games.widget.NoticeMarquee;

import java.util.ArrayList;
import java.util.List;


/**
 * 首页
 *
 */
public class GuessDetailActivity extends BaseTabActitity<GuessDetailDelegate> {

    public final static String MATCH_BEAN = "match_bean";

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private MatchEntity entity;

    public static void startActivity(Context context,MatchEntity entity){
        Intent intent = new Intent(context,GuessDetailActivity.class);
        intent.putExtra(MATCH_BEAN,entity);
        context.startActivity(intent);
    }

    @Override
    protected Class<GuessDetailDelegate> getDelegateClass() {
        return GuessDetailDelegate.class;
    }

    @Override
    protected void initValue() {
        if (getIntent() != null){
            entity = (MatchEntity) getIntent().getSerializableExtra(MATCH_BEAN);
        }
        super.initValue();
        EmptyFragment fragment = new EmptyFragment();
        EmptyFragment fragment1 = new EmptyFragment();
        EmptyFragment fragment2 = new EmptyFragment();
        EmptyFragment fragment3 = new EmptyFragment();
        mFragments.add(fragment);
        mFragments.add(fragment1);
        mFragments.add(fragment2);
        mFragments.add(fragment3);
        getAnnounce();
        viewDelegate.setData(entity);
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.OnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                switch (state) {
                    case COLLAPSED:
                        viewDelegate.setTitleState(true,false,true,false,true);
                        viewDelegate.setTitle("sfsd fsdf sd fds ");
                        break;
                    case EXPANDED:
                    case IDLE:
                        viewDelegate.setTitleState(true,false,false,false,true);
                        break;
                }
            }
        });
    }

    public void getAnnounce(){
        BetManager.getInstance().getAnnounce(new CommonCallback<CommonEntity<AnnouncementEntity>>() {
            @Override
            public void onSuccess(CommonEntity<AnnouncementEntity> result) {
                AnnouncementEntity entry = result.getData();
                if (entry != null){
                    if (entry.getAnnouncements() != null && entry.getAnnouncements().size()>0){
                        viewDelegate.setMarqueeVisiable(true);
                        viewDelegate.setFactory(entry.getAnnouncements());
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                viewDelegate.onStart();
                            }
                        }, 500);
                    }else {
                        viewDelegate.setMarqueeVisiable(false);
                        onStop();
                    }
                }else {
                    viewDelegate.setMarqueeVisiable(false);
                    onStop();
                }
            }

            @Override
            public void onError(String error) {
                ToastUtil.showToast(error);
            }
        });
    }

    @Override
    protected List<Fragment> getFragments() {
        return mFragments;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewDelegate.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        viewDelegate.onStop();
    }
}
