package com.jgg.games.adapter;

import android.content.Context;

import com.jgg.games.R;
import com.jgg.games.model.entity.ShareEntity;
import com.jgg.games.recycleview.adapter.CommonAdapter;
import com.jgg.games.recycleview.base.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class ShareAdapter extends CommonAdapter<ShareEntity> {

    public ShareAdapter(Context context, int itemLayoutId, List<ShareEntity> mDatas) {
        super(context, itemLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder helper, ShareEntity item) {
        helper.setImageResource(R.id.iv_icon,item.mIcon);
        helper.setText(R.id.tv_name,item.mShowWord);
    }
}
