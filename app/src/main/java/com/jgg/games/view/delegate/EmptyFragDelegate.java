package com.jgg.games.view.delegate;

import com.jgg.games.R;
import com.jgg.games.presenter.base.HeaderDelegate;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class EmptyFragDelegate extends HeaderDelegate {


    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_empty;
    }


    @Override
    public void initWidget() {
        showTitle(false);
        super.initWidget();

    }

}
