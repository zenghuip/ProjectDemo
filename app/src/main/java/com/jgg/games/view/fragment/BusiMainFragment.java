package com.jgg.games.view.fragment;

import com.jgg.games.view.base.BaseFragment;
import com.jgg.games.view.delegate.MainFrgDelegate;


/**
 * 主业务入口
 * @author android2
 *
 */
public class BusiMainFragment extends BaseFragment<MainFrgDelegate> {

	// //实例化方法
	public static BusiMainFragment newInstance() {
		return new BusiMainFragment();
	}

    @Override
    protected Class<MainFrgDelegate> getDelegateClass() {
        return MainFrgDelegate.class;
    }

    @Override
    protected void initValue() {
        super.initValue();
        IndexFragment fragment = new IndexFragment();
        EmptyFragment fragment2 = new EmptyFragment();
        EmptyFragment fragment3 = new EmptyFragment();
        CircleTypeFragmet fragment4 = new CircleTypeFragmet();
        MineFragment fragment5 = new MineFragment();
        viewDelegate.addFragment(fragment,null);
        viewDelegate.addFragment(fragment2,null);
        viewDelegate.addFragment(fragment3,null);
        viewDelegate.addFragment(fragment4,null);
        viewDelegate.addFragment(fragment5,null);
        viewDelegate.showCurFragment(getChildFragmentManager());
    }

}
