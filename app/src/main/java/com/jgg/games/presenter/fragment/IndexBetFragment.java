package com.jgg.games.presenter.fragment;

import android.os.Handler;
import android.util.Log;

import com.jgg.games.R;
import com.jgg.games.adapter.BetAdapter;
import com.jgg.games.adapter.RotationBannerAdapter;
import com.jgg.games.http.base.CommonCallback;
import com.jgg.games.model.entity.AnnouncementEntity;
import com.jgg.games.model.entity.CommonEntity;
import com.jgg.games.model.entity.GameTypeEntity;
import com.jgg.games.model.entity.IndexBannerEntity;
import com.jgg.games.model.entity.MatchEntity;
import com.jgg.games.model.entity.MatchListEntity;
import com.jgg.games.model.manager.BetManager;
import com.jgg.games.recycleview.adapter.MultiItemTypeAdapter;
import com.jgg.games.utils.ToastUtil;
import com.jgg.games.presenter.base.PullRefreshFragment;
import com.jgg.games.view.delegate.IndexBetFrgDelegate;

import java.util.ArrayList;
import java.util.List;


/**
 * 首页
 *
 */
public class IndexBetFragment extends PullRefreshFragment<MatchEntity,IndexBetFrgDelegate> {

    public static final String GAME = "game";
    private BetAdapter adapter;
    private List<MatchEntity> matchList = new ArrayList<>();

    private RotationBannerAdapter mTopAdapter;

    private String gameId,curTitle;
    private GameTypeEntity gameTypeEntity;

    @Override
    protected Class<IndexBetFrgDelegate> getDelegateClass() {
        return IndexBetFrgDelegate.class;
    }

    @Override
    protected void getIntentData() {
        if (getArguments() != null ){
            gameTypeEntity = (GameTypeEntity) getArguments().getSerializable(GAME);
            if (gameTypeEntity != null){
                gameId = gameTypeEntity.getId();
                curTitle = gameTypeEntity.getName();
                Log.d("dddd",curTitle+"----");
            }

        }
    }

    @Override
    protected void initValue() {
        super.initValue();
        mTopAdapter = new RotationBannerAdapter(activity);
    }

    @Override
    protected MultiItemTypeAdapter<MatchEntity> getAdapter() {
        adapter = new BetAdapter(activity, R.layout.item_bet,curTitle);
        return adapter;
    }

    @Override
    protected void getData() {

        if (isRefresh) {
            getBanner();
            getAnnounce();

        }

        BetManager.getInstance().getMatchList(gameId,mCurrentPage, mPageCount, new CommonCallback<CommonEntity<MatchListEntity>>() {
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

    public void getBanner(){
        BetManager.getInstance().getBanner(0,new CommonCallback<CommonEntity<IndexBannerEntity>>() {
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

    @Override
    protected void addHeadView() {
        super.addHeadView();
        viewDelegate.addHead(adapter);
    }
}
