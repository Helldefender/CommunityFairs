package com.helldefender.communityfairs.modules.main.discovery;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.app.BaseFragment;
import com.helldefender.communityfairs.app.ViewModelFactory;
import com.helldefender.communityfairs.databinding.FragmentDiscoveryBinding;

/**
 * Created by Helldefender on 2017/2/16.
 */

public class DiscoveryFragment extends BaseFragment<FragmentDiscoveryBinding, DiscoveryViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discovery;
    }

    @Override
    protected int getVariableId() {
        return BR.viewModel;
    }

    @Override
    protected DiscoveryViewModel getViewModel() {
        return ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(DiscoveryViewModel.class);
    }

    @Override
    protected void initViews(FragmentDiscoveryBinding binding, Bundle savedInstanceState) {

    }
}
