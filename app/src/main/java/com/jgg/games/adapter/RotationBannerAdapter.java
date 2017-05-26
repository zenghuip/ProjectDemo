package com.jgg.games.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.jgg.games.model.entity.IndexBannerEntity;
import com.jgg.games.presenter.activity.WebviewActivity;
import com.jgg.games.utils.DisplayUtil;
import com.jgg.games.utils.ImageUtil;
import com.jgg.games.utils.IntentUtils;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by zhp on 2016/12/13 0013.
 * 轮播图
 */

public class RotationBannerAdapter implements BGABanner.Adapter<ImageView, IndexBannerEntity> {
    private Context mContext;


    public RotationBannerAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView imageView, final IndexBannerEntity model, int position) {
        imageView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.getScreenWidth(mContext) / 16 * 9));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        if (model.getImage() != null) {
            ImageUtil.displayImg(model.getImage().getUrl(), imageView);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.intentWebView(mContext,"http://news.cos.99.com/news/05232017/033459574.shtml");
            }
        });
    }
}
