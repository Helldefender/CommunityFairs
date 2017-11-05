package com.helldefender.enjoylife.modules;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.model.Organization;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;
import com.helldefender.enjoylife.ui.adapter.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Helldefender on 2017/6/15.
 */

public class MoreOrganizationActivity extends BaseActivity {

    @BindView(R.id.tv_organization_more_title)
    TextView mTitleTv;

    @BindView(R.id.rv_organization_more)
    RecyclerView mRecyclerView;

    private OrganizationAdapter organizationAdapter;

    private GridLayoutManager gridLayoutManager;

    private List<Organization> data;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_organization_more;
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.tv_organization_more_title:
                finish();
                break;
        }
    }

    @Override
    protected void setWidgetListener() {
        super.setWidgetListener();
        mTitleTv.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        handleStatusBar();

        initRecyclerView();
    }

    private void initRecyclerView() {
        gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(gridLayoutManager);

        data = new ArrayList<Organization>();

        Organization organization = new Organization();

        data.add(organization);
        data.add(organization);
        data.add(organization);
        data.add(organization);
        data.add(organization);
        data.add(organization);
        data.add(organization);
        data.add(organization);

        //fragment中的context
        organizationAdapter = new OrganizationAdapter(this, R.layout.item_rv_organization_more, data);

        mRecyclerView.setAdapter(organizationAdapter);


        organizationAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position, int viewType) {
                startActivity(OrganizationActivity.class);
            }
        });
    }
}
