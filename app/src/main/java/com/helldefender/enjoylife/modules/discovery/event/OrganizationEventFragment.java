package com.helldefender.enjoylife.modules.discovery.event;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.model.Event;
import com.helldefender.enjoylife.ui.adapter.base.BaseAdapter;
import com.helldefender.enjoylife.ui.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Helldefender on 2017/6/15.
 */

public class OrganizationEventFragment extends BaseFragment {

    @BindView(R.id.rv_organization_event)
    RecyclerView mRecyclerView;

    Unbinder unbinder;

    private OrganizationEventRvAdapter organizationEventRvAdapter;

    private List<Event> mDataList;

    @Override
    protected void initInject() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        initRecyclerView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_organization_event;
    }

    @Override
    protected int getEmptyLayoutId() {
        return 0;
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

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));


        mDataList = new ArrayList<Event>();
        Event event = new Event();
        mDataList.add(event);
        mDataList.add(event);
        mDataList.add(event);
        mDataList.add(event);
        mDataList.add(event);
        mDataList.add(event);

        organizationEventRvAdapter = new OrganizationEventRvAdapter(getHoldingActivity(),R.layout.item_homepage_rv_content,mDataList);

        mRecyclerView.setAdapter(organizationEventRvAdapter);

        organizationEventRvAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position, int viewType) {

            }
        });
    }
}
