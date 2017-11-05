package com.helldefender.enjoylife.modules;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.model.Event;
import com.helldefender.enjoylife.ui.activity.DetailContentActivity;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;
import com.helldefender.enjoylife.ui.adapter.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Helldefender on 2017/6/14.
 */

public class MoreEventActivity extends BaseActivity {
    @BindView(R.id.tv_event_more_title)
    TextView tvEventMoreTitle;
    @BindView(R.id.rv_event_more)
    RecyclerView mRecyclerView;

    private EventAdapter eventAdapter;

    private GridLayoutManager gridLayoutManager;

    private List<Event> data;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_event_more;
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

        tvEventMoreTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initRecyclerView() {
        gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(gridLayoutManager);

        data = new ArrayList<Event>();

        Event event = new Event();
        data.add(event);
        data.add(event);
        data.add(event);
        data.add(event);
        data.add(event);
        data.add(event);
        data.add(event);
        data.add(event);
        data.add(event);
        data.add(event);

        eventAdapter = new EventAdapter(this, R.layout.item_rv_event_more, data);

        mRecyclerView.setAdapter(eventAdapter);

        eventAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position, int viewType) {
                startActivity(DetailContentActivity.class);
            }
        });
    }
}
