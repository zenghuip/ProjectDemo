package com.jgg.games.view.base;

import android.widget.ListView;

import com.jgg.games.R;
import com.jgg.games.adapter.base.CommonAdapter;

/**
 * Created by Administrator on 2017/4/13 0013.
 *
 */

public abstract class ListRefreshDelegate extends PullToRefreshDelegate {

    private ListView listView;

    @Override
    public void initWidget() {
        super.initWidget();
        listView = get(R.id.list_view);
    }

    public void setAdapter(CommonAdapter adapter){
        listView.setAdapter(adapter);
    }
}
