package com.jgg.games.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.jgg.games.R;
import com.jgg.games.model.entity.AnnouncementEntity;

public class NoticeMarquee extends MarqueeFactory<TextView, AnnouncementEntity> {
    private LayoutInflater inflater;

    public NoticeMarquee(Context mContext) {
        super(mContext);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public TextView generateMarqueeItemView(AnnouncementEntity data) {
        TextView mView = (TextView) inflater.inflate(R.layout.notice_item, null);
        mView.setText(data.getContent());
        return mView;
    }
}