package com.jgg.games.view.fragment;

import com.jgg.games.view.base.BaseFragment;
import com.jgg.games.view.delegate.EmptyFragDelegate;

public class EmptyFragment extends BaseFragment<EmptyFragDelegate> {


    @Override
    protected Class<EmptyFragDelegate> getDelegateClass() {
        return EmptyFragDelegate.class;
    }
}
