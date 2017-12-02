package com.helldefender.enjoylife.modules.main.discovery;

import android.os.Bundle;
import android.view.View;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.app.BaseFragment;

/**
 * Created by Helldefender on 2017/5/21.
 */

public class BlankFragment extends BaseFragment {
    @Override
    protected void initInject() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blank;
    }

    @Override
    protected int getEmptyLayoutId() {
        return 0;
    }

    public static BlankFragment getInstance() {
        return new BlankFragment();
    }
}
