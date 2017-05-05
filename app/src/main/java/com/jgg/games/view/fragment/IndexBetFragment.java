package com.jgg.games.view.fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jgg.games.R;
import com.jgg.games.adapter.BetAdapter;
import com.jgg.games.adapter.RotationBannerAdapter;
import com.jgg.games.adapter.base.CommonRecyclerAdapter;
import com.jgg.games.http.base.CommonCallback;
import com.jgg.games.model.entity.AnnouncementEntity;
import com.jgg.games.model.entity.CommonEntity;
import com.jgg.games.model.entity.IndexBannerEntity;
import com.jgg.games.model.entity.MatchEntity;
import com.jgg.games.model.entity.MatchListEntity;
import com.jgg.games.model.manager.BetManager;
import com.jgg.games.utils.ToastUtil;
import com.jgg.games.view.base.RecyclerRefreshFragment;
import com.jgg.games.view.delegate.IndexBetFrgDelegate;
import com.jgg.games.widget.MarqueeFactory;
import com.jgg.games.widget.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;


/**
 * 首页
 *
 */
public class IndexBetFragment extends RecyclerRefreshFragment<MatchEntity,IndexBetFrgDelegate> {
    public static final String GAME_TYPE_ID = "game_type_id";
    private BetAdapter adapter;
    private List<MatchEntity> matchList = new ArrayList<>();
    private String gameId;
    private RotationBannerAdapter mTopAdapter;

    @Override
    protected Class<IndexBetFrgDelegate> getDelegateClass() {
        return IndexBetFrgDelegate.class;
    }

    @Override
    protected void initValue() {
        if (getArguments() != null ){
            gameId = getArguments().getString(GAME_TYPE_ID);
        }
        super.initValue();
        mTopAdapter = new RotationBannerAdapter(activity);

        viewDelegate.addHead(adapter);
    }

    @Override
    protected CommonRecyclerAdapter getAdapter() {
        adapter = new BetAdapter(activity,matchList, R.layout.item_bill_head);
        return adapter;
    }

    @Override
    protected void getData() {

        if (isRefresh){
            getBanner();
            getAnnounce();
        }

        BetManager.getInstance(activity).getMatchList(gameId,mCurrentPage, mPageCount, new CommonCallback<CommonEntity<MatchListEntity>>() {
            @Override
            public void onSuccess(CommonEntity<MatchListEntity> result) {
                matchList = result.getData().getMatches();
                setList(matchList);
            }

            @Override
            public void onError(String error) {
                ToastUtil.showToast(error);
                loadComplete();
            }
        });
    }

    public void getAnnounce(){
        BetManager.getInstance(activity).getAnnounce(new CommonCallback<CommonEntity<AnnouncementEntity>>() {
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

    public void getBanner(){
        BetManager.getInstance(activity).getBanner(0,new CommonCallback<CommonEntity<IndexBannerEntity>>() {
            @Override
            public void onSuccess(CommonEntity<IndexBannerEntity> result) {
                IndexBannerEntity entry = result.getData();
                if (entry != null){
                    List<String> tipList = new ArrayList<>();
                    if (entry.getAppMenuItems() != null && entry.getAppMenuItems().size()>0){
                        for(IndexBannerEntity bannerEntry:entry.getAppMenuItems()){
                            tipList.add(bannerEntry.getName());
                        }
                        viewDelegate.setBannerAdapter(mTopAdapter,entry.getAppMenuItems(),tipList);
                        viewDelegate.setBannerVisiable(true);
                    }else {
                        viewDelegate.setBannerVisiable(false);
                    }
                }
            }

            @Override
            public void onError(String error) {

            }
        });
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
