package com.jgg.games.view.delegate;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jgg.games.R;
import com.jgg.games.adapter.RotationBannerAdapter;
import com.jgg.games.model.entity.AnnouncementEntity;
import com.jgg.games.model.entity.IndexBannerEntity;
import com.jgg.games.recycleview.adapter.MultiItemTypeAdapter;
import com.jgg.games.presenter.base.RecyclerRefreshDelegate;
import com.jgg.games.widget.MarqueeFactory;
import com.jgg.games.widget.MarqueeView;
import com.jgg.games.widget.NoticeMarquee;

import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class IndexBetFrgDelegate extends RecyclerRefreshDelegate {

    private MarqueeView marqueeView;
    private BGABanner banner;


    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_common_refresh_notitle;
    }

    @Override
    public void initWidget() {
        showTitle(false);
        super.initWidget();
    }

    @Override
    public void initValue() {
        super.initValue();
    }

    public void addHead(MultiItemTypeAdapter adapter){
        View header = LayoutInflater.from(this.getActivity()).inflate(R.layout.layout_banner, null, false);
        banner = get(header,R.id.banner);
        marqueeView = get(header, R.id.tv_marquee);
        marqueeView.setVisibility(View.GONE);
        addHeadView(header,adapter);
    }


    public void setMarqueeVisiable(boolean show){
        setViewGoneOrVisible(marqueeView,show);
    }

    public void setBannerVisiable(boolean show){
        setViewGoneOrVisible(banner,show);
    }

    public void onResume() {
        if (!marqueeView.isFlipping() && marqueeView.getVisibility() == View.VISIBLE){
            marqueeView.startFlipping();
        }
    }

    public void onStop() {
        if (marqueeView.isFlipping() && marqueeView.getVisibility() == View.VISIBLE){
            marqueeView.stopFlipping();
        }
    }

    public void setFactory(List<AnnouncementEntity> entity){
        MarqueeFactory<TextView, AnnouncementEntity> marqueeFactory = new NoticeMarquee(this.getActivity());
        marqueeView.setMarqueeFactory(marqueeFactory);
        marqueeFactory.resetData(entity);
    }

    public void setBannerAdapter(RotationBannerAdapter adapter, List<IndexBannerEntity> list,List<String> tipList){
        banner.setAdapter(adapter);
        banner.setData(list, null);
    }

    public void onStart() {
        marqueeView.startFlipping();
    }
}
