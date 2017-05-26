package com.helldefender.enjoylife.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.presenter.impl.DiscoveryAppBarImpl;
import com.helldefender.enjoylife.ui.adapter.OrganizationRvAdapter;
import com.helldefender.enjoylife.ui.fragment.base.BaseFragment;
import com.helldefender.enjoylife.view.DisCoveryAppBarView;
import com.helldefender.enjoylife.widget.ClearEditText;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Helldefender on 2017/5/21.
 */

public class DiscoveryAppBarFragment extends BaseFragment implements DisCoveryAppBarView {

    @Inject
    DiscoveryAppBarImpl discoveryAppBarPresenter;

    @BindView(R.id.et_discovery_search)
    ClearEditText clearEditText;

    @BindView(R.id.tv_discovery_cancel)
    TextView cancelTextView;

    @BindView(R.id.tv_discovery_moreOrganization)
    TextView moreOrganizationTextView;

    @BindView(R.id.rv_discovery_organization)
    RecyclerView mOrganizationRecyclerView;

//    @BindView(R.id.tv_discovery_moreEvent)
//    TextView moreEventTextView;

    Unbinder unbinder;

    private OrganizationRvAdapter organizationRvAdapter;

    @Override
    public void showLoadingLayout() {

    }

    @Override
    public void showEmptyLayout() {

    }

    @Override
    public void showErrorLayout(int message) {

    }

    @Override
    protected void initInject() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initPresenter() {
        mPresenter = discoveryAppBarPresenter;
        mPresenter.attachView(this);

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        initRecyclerView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discovery_appbar;
    }

    @Override
    protected int getEmptyLayoutId() {
        return 0;
    }

    private void initRecyclerView() {
        List<String> data = new ArrayList<String>();
        data.add("我是数据");
        data.add("我是数据");
        data.add("我是数据");
        data.add("我是数据");
        data.add("我是数据");
        data.add("我是数据");
        data.add("我是数据");
        data.add("我是数据");

        organizationRvAdapter = new OrganizationRvAdapter(getHoldingActivity(), R.layout.item_discovery_rv_organization, data);
        mOrganizationRecyclerView.setLayoutManager(new GridLayoutManager(getHoldingActivity(), 4, GridLayoutManager.VERTICAL, false));
        mOrganizationRecyclerView.setAdapter(organizationRvAdapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static DiscoveryAppBarFragment getInstance() {
        return new DiscoveryAppBarFragment();
    }
}