package com.jgg.games.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.jgg.games.R;
import com.jgg.games.model.entity.TiebaTypeEntity;
import com.jgg.games.recycleview.adapter.CommonRecyclerAdapter;
import com.jgg.games.recycleview.base.RecyclerViewHolder;
import com.jgg.games.utils.ConvertUtils;

import java.util.List;

/**
 * Created by nd on 2017/3/25.
 */
public class CircleTypeAdapter extends CommonRecyclerAdapter<TiebaTypeEntity> {
    private int itemWidth;
    private int itemHeight;

    public CircleTypeAdapter(Context context,int itemLayoutId) {
        super(context, itemLayoutId);

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;   // 获取屏幕分辨率宽度
        itemWidth = (screenWidth - ConvertUtils.dp2px(32)) / 2;
        itemHeight = itemWidth * 9 / 16;
    }

    @Override
    protected void convert(RecyclerViewHolder viewHolder, TiebaTypeEntity item, int position) {
        viewHolder.setText(R.id.tv_cicle_type_name, item.getName());
        viewHolder.setImageByUrl(R.id.iv_circle_icon, item.getImage());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putSerializable(CircleFragment.TOP_TYPE, item);
//                BaseFragmentActivity.startActivity(mContext,CircleFragment.class, bundle);
            }
        });
    }



}
