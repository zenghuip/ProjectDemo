package com.jgg.games.view.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jgg.themvp.view.AppDelegate;

import com.jgg.games.R;
import com.jgg.games.utils.ToastUtil;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public abstract class HeaderDelegate extends AppDelegate {
    public final static int TITLE_OF_NONE = 0; // 没有背景的头部
    public final static int TITLE_OF_GUESS = 1; // 竞猜类型
    public final static int TITLE_OF_MINE = 2; // 个人中心类型
    public final static int TITLE_OF_GUILD = 3; // 公会类型
    public final static int TITLE_OF_MATCH = 4; // 赛事类型

    public TextView tvTitle; // 标题
    public TextView tvGuide;// 引导
    public TextView tvRight;// 右边 确定或者其他
    public ImageView ivShare; // 分享
    public ImageView ivSetting; // 设置
    public ImageView ivBack; // 返回
    public RelativeLayout rlTitle; //

    private boolean showTitle = true;

    @Override
    public void initWidget() {
        super.initWidget();
        if (!showTitle){
            return;
        }
        tvTitle = get(R.id.tv_title);
        ivBack = get(R.id.iv_back);
        ivSetting = get(R.id.iv_setting);
        ivShare = get(R.id.iv_share);
        tvGuide = get(R.id.tv_guide);
        rlTitle = get(R.id.rl_title);
        tvRight = get(R.id.tv_right);
    }


    public void showTitle(boolean show){
        this.showTitle = show;
        if (rlTitle == null){
            return;
        }
        if (showTitle){
            rlTitle.setVisibility(View.VISIBLE);
        }else {
            rlTitle.setVisibility(View.GONE);
        }
    }

    public void setTitle(int title){
        setViewGoneOrVisible(tvTitle,true);
        tvTitle.setText(rootView.getContext().getResources().getString(title));
    }

    public void setRightText(int rightText){
        setViewGoneOrVisible(tvRight,true);
        tvRight.setText(rootView.getContext().getResources().getString(rightText));
    }

    /**
     * 只显示标题
     * @param title
     */
    public void onlyTitle(int title){
        showTitle(true);
        setViewGoneOrVisible(ivBack, false);
        setViewGoneOrVisible(ivSetting, false);
        setViewGoneOrVisible(ivShare, false);
        setViewGoneOrVisible(tvGuide, false);
        setTitle(title);
    }


    /**
     * 标题状态
     * @param back 返回
     * @param guide 引导
     * @param title 标题
     * @param setting 设置
     * @param share 分享
     */
    public void setTitleState(boolean back,boolean guide,boolean title,boolean setting,boolean share){
        showTitle(true);
        setViewGoneOrVisible(ivBack,back);
        setViewGoneOrVisible(ivShare,share);
        setViewGoneOrVisible(tvGuide,guide);
        setViewGoneOrVisible(tvTitle,title);
        setViewGoneOrVisible(ivSetting,setting);

    }

    /**
     *
     * 标题状态 有标题
     * @param back 返回
     * @param guide 引导
     * @param titleStr 标题
     * @param setting 设置
     * @param share 分享
     */

    public void setTitleState(boolean back,boolean guide,boolean setting,boolean share,int titleStr){
        setTitleState(back,guide,true,setting,share);
        if (titleStr != 0){
            setTitle(titleStr);
        }
    }

    public void setTitleAndBack(int titleStr){
        setTitleState(true,false,false,false,titleStr);
        if (titleStr != 0){
            setTitle(titleStr);
        }
    }

    /**
     * 显示返回 右边按钮 标题
     * @param titleStr
     * @param rightStr
     */
    public void setTitleBackRight(int titleStr,int rightStr){
        setTitleState(true,false,false,false,titleStr);
        if (titleStr != 0){
            setTitle(titleStr);
        }
        if (rightStr != 0){
            setRightText(rightStr);
        }
    }

    public void setTitleBgType(int type){
        switch (type){
            case TITLE_OF_NONE:
                rlTitle.setBackgroundResource(R.color.common_main_color);
                break;

            case TITLE_OF_GUESS:
                rlTitle.setBackgroundResource(R.drawable.bg_title_guess);


                break;

            case TITLE_OF_MINE:
                rlTitle.setBackgroundResource(R.drawable.bg_title_mine);
                break;

            case TITLE_OF_GUILD:
                rlTitle.setBackgroundResource(R.color.common_main_color);
                break;

            case TITLE_OF_MATCH:
                rlTitle.setBackgroundResource(R.drawable.bg_title_match);
                break;
        }

    }


    public void setViewGoneOrVisible(View view, boolean visible){
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public boolean isShowTitle() {
        return showTitle;
    }

    public void share(){
        ToastUtil.showToast(R.string.waiting);
    }

    public String getString(int string){
        return getActivity().getResources().getString(string);
    }

    public void finish()
    {
        getActivity().finish();
    }
}
