package com.helldefender.communityfairs.modules.main.homepage;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.app.BaseFragment;
import com.helldefender.communityfairs.app.ViewModelFactory;
import com.helldefender.communityfairs.databinding.FragmentHomepageBinding;
import com.helldefender.communityfairs.listener.OnLoadMoreListener;
import com.helldefender.communityfairs.listener.OnRefreshListener;
import com.helldefender.communityfairs.widget.recyclerview.LoadMoreFooterView;

/**
 * Created by Helldefender on 2017/2/5.
 */

public class HomePageFragment extends BaseFragment<FragmentHomepageBinding, HomePageViewModel> implements OnLoadMoreListener, OnRefreshListener {

    private static final String TAG = HomePageFragment.class.getSimpleName();

    private LoadMoreFooterView loadMoreFooterView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected int getVariableId() {
        return BR.viewModel;
    }

    @Override
    protected HomePageViewModel getViewModel() {
        return ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(HomePageViewModel.class);
    }

    @Override
    protected void initViews(FragmentHomepageBinding binding, Bundle savedInstanceState) {

    }

    @Override
    public void onRefresh() {
        loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
    }

    @Override
    public void onLoadMore() {
//        if (loadMoreFooterView.canLoadMore() && homePageAdapter.getItemCount() > 0) {
//            loadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
//        }
    }
}
