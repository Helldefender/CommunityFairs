package com.helldefender.communityfairs.modules.community.detail.apply;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;

import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.app.BaseActivity;
import com.helldefender.communityfairs.app.ViewModelFactory;

/**
 * 功能：
 * 描述：
 * Created by Helldefender on 2018/1/13.
 */

public class ApplyActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply;
    }

    @Override
    protected int getVariableId() {
        return BR.viewModel;
    }

    @Override
    protected ViewModel getViewModel() {
        return ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(ApplyViewModel.class);
    }
}
