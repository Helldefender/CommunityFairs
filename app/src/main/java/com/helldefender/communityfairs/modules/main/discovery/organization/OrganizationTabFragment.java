package com.helldefender.communityfairs.modules.main.discovery.organization;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.app.BaseFragment;
import com.helldefender.communityfairs.app.ViewModelFactory;
import com.helldefender.communityfairs.databinding.FragmentOrganizationTabBinding;

/**
 * Created by Helldefender on 2018/1/7 for CommunityFairs.
 * Function:
 * Description:
 */

public class OrganizationTabFragment extends BaseFragment<FragmentOrganizationTabBinding, OrganizationTabViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_organization_tab;
    }

    @Override
    protected int getVariableId() {
        return BR.viewModel;
    }

    @Override
    protected OrganizationTabViewModel getViewModel() {
        return ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(OrganizationTabViewModel.class);
    }

    @Override
    protected void initViews(FragmentOrganizationTabBinding binding, Bundle savedInstanceState) {

    }

    public RecyclerView getRecyclerView() {
        return binding.rvOrganizationTab;
    }


    public static OrganizationTabFragment newInstance() {
        return new OrganizationTabFragment();
    }
}
