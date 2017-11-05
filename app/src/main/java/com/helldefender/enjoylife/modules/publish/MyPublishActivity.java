package com.helldefender.enjoylife.modules.publish;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.model.Event;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;
import com.helldefender.enjoylife.ui.adapter.PublishRVAdapter;
import com.helldefender.enjoylife.ui.adapter.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Helldefender on 2017/6/15.
 */

public class MyPublishActivity extends BaseActivity {

    @BindView(R.id.tv_organization_publish)
    TextView mTitleTv;

    @BindView(R.id.rv_my_publish)
    RecyclerView mRecyclerView;


    private PublishRVAdapter publishRVAdapter;

    private List<Event> mDataList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_publish;
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

        mTitleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDataList = new ArrayList<Event>();

        Event event1 = new Event();
        event1.setName("数据库大赛");
        event1.setStatus(1);
        Event event2 = new Event();
        event1.setName("数模校赛");
        event1.setStatus(2);
        event2.setNum(10);
        Event event3 = new Event();
        event1.setName("发现杯");
        event1.setStatus(3);

        mDataList.add(event1);
        mDataList.add(event2);
        mDataList.add(event3);

        publishRVAdapter = new PublishRVAdapter(this, R.layout.item_publish, mDataList);

        mRecyclerView.setAdapter(publishRVAdapter);

        publishRVAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position, int viewType) {

            }
        });
    }
}
