package com.helldefender.enjoylife.modules.attend;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.model.Event;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;
import com.helldefender.enjoylife.ui.adapter.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Helldefender on 2017/6/15.
 */

public class MyAttendActivity extends BaseActivity {


    @BindView(R.id.tv_organization_attend)
    TextView tvOrganizationAttend;

    @BindView(R.id.search_top_layout)
    LinearLayout searchTopLayout;

    @BindView(R.id.rv_organization_attend)
    RecyclerView mRecyclerView;

    private List<Event> mDataList;

    private AttendRvAdapter attendRvAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_attend;
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void widgetClick(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        handleStatusBar();

        initRecyclerView();

        tvOrganizationAttend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDataList = new ArrayList<Event>();
        Event event = new Event();
        mDataList.add(event);
        mDataList.add(event);
        mDataList.add(event);
        mDataList.add(event);
        mDataList.add(event);
        mDataList.add(event);

        attendRvAdapter = new AttendRvAdapter(this, R.layout.item_attend, mDataList);

        mRecyclerView.setAdapter(attendRvAdapter);

        attendRvAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position, int viewType) {

            }
        });
    }
}
