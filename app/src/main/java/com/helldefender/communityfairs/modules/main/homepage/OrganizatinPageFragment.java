//package com.helldefender.communityfairs.modules.main.homepage;
//
//import android.os.Bundle;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.helldefender.communityfairs.R;
//import com.helldefender.communityfairs.model.entity.Event;
//import com.helldefender.communityfairs.app.BaseAdapter;
//import com.helldefender.communityfairs.bindingadapter.MultiViewType;
//import com.helldefender.communityfairs.app.BaseFragment;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.Unbinder;
//
///**
// * Created by Helldefender on 2017/6/15.
// */
//
//public class OrganizatinPageFragment extends BaseFragment {
//
//    @BindView(R.id.rv_organization_event)
//    RecyclerView mRecyclerView;
//
//    Unbinder unbinder;
//
//    private OrganizationPageRvAdapter organizationPageRvAdapter;
//
//    private List<Event> mDataList;
//
//    @Override
//    protected void initInject() {
//
//    }
//
//    @Override
//    protected void initPresenter() {
//
//    }
//
//    @Override
//    protected void initViews(View view, Bundle savedInstanceState) {
//        initRecyclerView();
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_organization_event;
//    }
//
//    @Override
//    protected int getEmptyLayoutId() {
//        return 0;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder = ButterKnife.bind(this, rootView);
//        return rootView;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
//
//    private void initRecyclerView() {
//        mRecyclerView.setLayoutManager(new GridLayoutManager(getHoldingActivity(), 1, GridLayoutManager.VERTICAL, false));
//
//
//        mDataList = new ArrayList<Event>();
//        Event event = new Event();
//        mDataList.add(event);
//        mDataList.add(event);
//
//        //fragment中的context
//        organizationPageRvAdapter = new OrganizationPageRvAdapter(getHoldingActivity(), new MultiViewType<Event>() {
//            @Override
//            public int getViewTypeSpanCount(int viewType) {
//                switch (viewType) {
//                    case OrganizationPageRvAdapter.TYPE_HEADER:
//                    case OrganizationPageRvAdapter.TYPE_CONTENT:
//                        return 1;
//                    default:
//                        return 1;
//                }
//            }
//
//            @Override
//            public int getItemViewType(int position) {
//                if (position == 0) {
//                    return OrganizationPageRvAdapter.TYPE_HEADER;
//                }
//                return OrganizationPageRvAdapter.TYPE_CONTENT;
//            }
//
//            @Override
//            public int getLayoutId(int viewType) {
//                if (viewType == OrganizationPageRvAdapter.TYPE_HEADER) {
//                    return R.layout.item_organizatin_header;
//                }
//                return R.layout.item_homepage_rv_content;
//            }
//        }, mDataList);
//
//        mRecyclerView.setAdapter(organizationPageRvAdapter);
//
//        organizationPageRvAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(View view, int position, int viewType) {
//
//            }
//        });
//    }
//}
