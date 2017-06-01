package com.jgg.games.view.delegate;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jgg.games.R;
import com.jgg.games.model.entity.AnnouncementEntity;
import com.jgg.games.model.entity.MatchEntity;
import com.jgg.games.presenter.base.HeaderDelegate;
import com.jgg.games.utils.ImageUtil;
import com.jgg.games.utils.ResConverUtil;
import com.jgg.games.utils.StringUtil;
import com.jgg.games.utils.TimeUtil;
import com.jgg.games.widget.MarqueeFactory;
import com.jgg.games.widget.MarqueeView;
import com.jgg.games.widget.MyTabWidget;
import com.jgg.games.widget.NoticeMarquee;
import com.jgg.games.widget.countdownview.CountdownView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class GuessDetailDelegate extends BaseTabDelegate {

    private ImageView ivLeftLogo,ivRightLogo;
    private TextView tvLeftName,tvRightName;
    private TextView tvScore,tvTime;
    private ImageView ivVideo;
    private ImageView ivBg;
    private CountdownView cvTime;
    private MarqueeView marqueeView;


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_guess_detail;
    }


    @Override
    public void initWidget() {
        super.initWidget();

        setTitleState(true,false,true,false,true);
        ivLeftLogo = get(R.id.iv_left_logo);
        ivRightLogo = get(R.id.iv_right_logo);
        tvLeftName = get(R.id.tv_left_name);
        tvRightName = get(R.id.tv_right_name);
        tvScore = get(R.id.tv_score);
        tvTime = get(R.id.tv_time);
        ivVideo = get(R.id.iv_video);
        cvTime = get(R.id.cv_time);
        marqueeView = get(R.id.tv_marquee);
        ivBg = get(R.id.iv_bg);
    }


    public void setMarqueeVisiable(boolean show){
        setViewGoneOrVisible(marqueeView,show);
    }


    public void onResume() {
        if (marqueeView != null && !marqueeView.isFlipping() && marqueeView.getVisibility() == View.VISIBLE){
            marqueeView.startFlipping();
        }
    }

    public void onStop() {
        if (marqueeView != null && marqueeView.isFlipping() && marqueeView.getVisibility() == View.VISIBLE){
            marqueeView.stopFlipping();
        }
    }

    public void setFactory(List<AnnouncementEntity> entity){
        MarqueeFactory<TextView, AnnouncementEntity> marqueeFactory = new NoticeMarquee(this.getActivity());
        marqueeView.setMarqueeFactory(marqueeFactory);
        marqueeFactory.resetData(entity);
    }

    public void onStart() {
        if (marqueeView != null)
            marqueeView.startFlipping();
    }

    public void setData(MatchEntity bean){
        if (bean != null) {
            tvLeftName.setText(bean.getLefttTeamName());
            tvRightName.setText(bean.getRightTeamName());
            tvTime.setText(bean.getStringStartTime(TimeUtil.PATTERN_YEAR_MONTH_DAY));
            tvScore.setText(bean.getMatchScore());
            ImageUtil.displayImg(bean.getLefttTeamLogo(), ivLeftLogo);
            ImageUtil.displayImg(bean.getRightTeamLogo(), ivRightLogo);
            if (StringUtil.isEmpty(bean.getVideoURL())) {
                ivVideo.setVisibility(View.GONE);
            } else {
                ivVideo.setVisibility(View.VISIBLE);
            }
            if (bean.getGame() != null) {
                ImageUtil.displayImg(bean.getGame().getLogo(),ivBg);
            }
            ResConverUtil.indexBetState(this.getActivity(),bean.getState(),tvScore,null,tvTime,cvTime,bean.getCountTime());
        }
    }

}
