package com.jgg.games.view.delegate;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jgg.games.R;
import com.jgg.games.presenter.base.RecyclerRefreshDelegate;
import com.jgg.games.widget.MyTabWidget;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class GuessListDelegate extends RecyclerRefreshDelegate {

    private ScrollView scTop;
    private MyTabWidget lyTop;
    private RelativeLayout rlBottom;
    private TextView tvNum;
    private TextView tvTime;
    private TextView tvNext;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_guesslist;
    }

    @Override
    public void initWidget() {
        showTitle(false);
        super.initWidget();
        scTop = get(R.id.sv_top);
        lyTop = get(R.id.ly_top);
        rlBottom = get(R.id.rl_bottom);
        tvNum = get(R.id.tv_num);
        tvTime = get(R.id.tv_time);
        tvNext = get(R.id.tv_next);
        setRefreshModel(RECYCLER_DISABLE);
    }

    @Override
    public void initValue() {
        super.initValue();
    }

    public void setTopVisible(boolean show){
        setViewGoneOrVisible(scTop,show);
    }

    public void setBottomVisible(boolean show){
        setViewGoneOrVisible(rlBottom,show);
    }

    public TextView addTop(){
        View header = LayoutInflater.from(this.getActivity()).inflate(R.layout.item_bet_top, null, false);
        TextView tvName= get(header,R.id.tv_name);
        lyTop.addView(header);
        return tvName;
    }

    public void setName(TextView tvName,int i){
        if (i == 0){
            tvName.setText(getString(R.string.guess_list_qc));
        }else {
            tvName.setText(getActivity().getResources().getString(R.string.guess_list_djc,i));
        }
    }
}
