package com.helldefender.communityfairs.modules.main.discovery;

import android.arch.lifecycle.ViewModel;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.app.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by Helldefender on 2017/2/16.
 */

public class DiscoveryFragment extends BaseFragment{



    @BindView(R.id.rv_discovery_event)
    RecyclerView mEventRecyclerView;

    Unbinder unbinder;
    @BindView(R.id.tv_discovery_moreEvent)
    TextView tvDiscoveryMoreEvent;

//    @Override
//    protected void initViews(View view, Bundle savedInstanceState) {
//        //initPresenter();
//        initRecyclerView();
//
//        tvDiscoveryMoreEvent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discovery;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

    @Override
    protected ViewModel getViewModel() {
        return null;
    }

    @Override
    protected void initViews(ViewDataBinding binding, Bundle savedInstanceState) {

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
//
//        discoveryRecyclerView.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));
//        discoveryRecyclerView.setAdapter(new DiscoveryRVAdapter(getHoldingActivity(), new MultiViewType<String>() {
//            @Override
//            public int getViewTypeSpanCount(int viewType) {
//                return 0;
//            }
//
//            @Override
//            public int getItemViewType(int position) {
//                if (position == 0) {
//                    return DiscoveryRVAdapter.DISCOVERY_TYPE_ORGANIZATION__TITLE;
//                } else if (position == 2) {
//                    return DiscoveryRVAdapter.DISCOVERY_TYPE_ORGANIZATION;
//                } else if (position == 3) {
//                    return DiscoveryRVAdapter.DISCOVERY_TYPE_EVENT_TITLE;
//                }
//                return DiscoveryRVAdapter.DISCOVERT_TYPE_EVENT;
//            }
//
//            @Override
//            public int getLayoutId(int viewType) {
//                if (viewType == DiscoveryRVAdapter.DISCOVERY_TYPE_ORGANIZATION__TITLE) {
//
//                }else if(viewType == DiscoveryRVAdapter.DISCOVERY_TYPE_ORGANIZATION){
//
//                } else if (viewType == DiscoveryRVAdapter.DISCOVERY_TYPE_EVENT_TITLE) {
//
//                }
//                return
//            }
//        }, data));

//        eventRvAdapter = new OrganizationRvAdapter(getHoldingActivity(), R.layout.item_discovery_rv_event, data);
//        mEventRecyclerView.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));
//        mEventRecyclerView.setAdapter(eventRvAdapter);
//        mEventRecyclerView.addItemDecoration(new DividerItemDecoration(getHoldingActivity(), DividerItemDecoration.VERTICAL));
//
//        eventRvAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position, int viewType) {
//
//            }
//        });
    }
}
