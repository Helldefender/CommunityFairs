package com.helldefender.enjoylife.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.event.LoggingStatusEvent;
import com.helldefender.enjoylife.listener.OnLoadMoreListener;
import com.helldefender.enjoylife.listener.OnRefreshListener;
import com.helldefender.enjoylife.presenter.impl.HomePagePresenterImpl;
import com.helldefender.enjoylife.ui.activity.DetailContentActivity;
import com.helldefender.enjoylife.ui.adapter.HomePageAdapter;
import com.helldefender.enjoylife.ui.adapter.base.BaseAdapter;
import com.helldefender.enjoylife.ui.adapter.base.MultiViewType;
import com.helldefender.enjoylife.ui.fragment.base.BaseFragment;
import com.helldefender.enjoylife.view.HomePageView;
import com.helldefender.enjoylife.widget.BannerView;
import com.helldefender.enjoylife.widget.recyclerview.LoadMoreFooterView;
import com.helldefender.enjoylife.widget.recyclerview.RefreshRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.helldefender.enjoylife.ui.adapter.HomePageAdapter.TYPE_BANNER;
import static com.helldefender.enjoylife.ui.adapter.HomePageAdapter.TYPE_CONTENT;

/**
 * Created by Helldefender on 2017/2/5.
 */

public class HomePageFragment extends BaseFragment implements HomePageView, OnLoadMoreListener, OnRefreshListener {

    private static final String TAG = HomePageFragment.class.getSimpleName();

    @Inject
    HomePagePresenterImpl homePagePresenter;

    @BindView(R.id.rv_homepage_refresh)
    RefreshRecyclerView homeRecyclerView;

    Unbinder unbinder;

    private BannerView bannerView;

    private static final int SPAN_COUNT = 6;

    private LoadMoreFooterView loadMoreFooterView;

    private HomePageAdapter homePageAdapter;

    private List<String> data;

    private List<String> newData;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            homeRecyclerView.setRefreshing(false);
        }
    };

    @Override
    public void showLoadingLayout() {
        //statusLayoutManager.showLoading();
    }

    @Override
    public void showEmptyLayout() {
        statusLayoutManager.showEmptyData();
    }

    @Override
    public void showErrorLayout(int message) {
        statusLayoutManager.showError();
    }

    @Override
    protected void initInject() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initPresenter() {
        mPresenter = homePagePresenter;
        mPresenter.attachView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected int getEmptyLayoutId() {
        return R.layout.layout_status_empty;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        initRecyclerView();
    }

    @Override
    public void onRefresh() {
        loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
        refresh();
    }

    @Override
    public void onLoadMore() {
        if (loadMoreFooterView.canLoadMore() && homePageAdapter.getItemCount() > 0) {
            loadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
            loadMore();
        }
    }

    private void initRecyclerView() {
        //fragment中的context
        homeRecyclerView.setLayoutManager(new GridLayoutManager(getHoldingActivity(), SPAN_COUNT, GridLayoutManager.VERTICAL, false));

        loadMoreFooterView = (LoadMoreFooterView) homeRecyclerView.getLoadMoreFooterView();

        data = new ArrayList<String>();
        data.add("测试");
        data.add("测试");
        data.add("测试");
        data.add("测试");
        data.add("测试");
        data.add("测试");
//        data.add("测试");
//        data.add("测试");
//        data.add("测试");
//        data.add("测试");
//        data.add("测试");
//        data.add("测试");
//        data.add("测试");
//        data.add("测试");
//        data.add("测试");

        newData = new ArrayList<String>();
        newData.add("测试");
        newData.add("测试");
        newData.add("测试");
        newData.add("我是新添加的数据");
        newData.add("我是新添加的数据");
        newData.add("我是新添加的数据");

        //fragment中的context
        homePageAdapter = new HomePageAdapter(getHoldingActivity(), new MultiViewType<String>() {

            @Override
            public int getViewTypeSpanCount(int viewType) {
                switch (viewType) {
                    case TYPE_BANNER:
                    case TYPE_CONTENT:
                        return SPAN_COUNT;
                    default:
                        return 2;
                }
            }

            @Override
            public int getItemViewType(int position) {
                if (position == 0) {
                    return TYPE_BANNER;
                }

                return TYPE_CONTENT;

            }

            @Override
            public int getLayoutId(int viewType) {
                if (viewType == TYPE_BANNER) {
                    return R.layout.item_homepage_rv_banner;
                }
                return R.layout.item_homepage_rv_content;
            }
        }, data, homeRecyclerView.getRefreshHeaderLayout(), homeRecyclerView.getLoadMoreFooterLayout());

        bannerView = (BannerView) LayoutInflater.from(getHoldingActivity()).inflate(R.layout.layout_banner_view, null);

//        TextView textView = new TextView(getHoldingActivity());
//        textView.setText("Header View");
//        textView.setGravity(Gravity.CENTER);
//        textView.setTextSize(25);
//        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, JUtils.dip2px(200));
//        textView.setLayoutParams(layoutParams);

        //homePageAdapter.addHeaderView(textView);

        homeRecyclerView.setAdapter(homePageAdapter);

        homeRecyclerView.setOnRefreshListener(this);
        homeRecyclerView.setOnLoadMoreListener(this);

        homePageAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position, int viewType) {
                DetailContentActivity.start(getHoldingActivity());
                //homePageAdapter.getRefreshHeaderViewCount() + homePageAdapter.getHeaderViewsCount();
                //Log.d(TAG, "onItemClick:    position" + position + "viewType" + viewType);

            }
        });
    }

    private void refresh() {
//        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack(homePageAdapter.getData(), newData), true);
//
//        diffResult.dispatchUpdatesTo(homePageAdapter);
//        data = newData;
//        homePageAdapter.setData(newData);

        handler.sendMessageDelayed(handler.obtainMessage(), 4000);
    }

    private void loadMore() {
        loadMoreFooterView.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadMoreFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
            }
        }, 2000);
    }

    @Subscribe
    public void onLoggingStatusChange(LoggingStatusEvent loggingStatusEvent) {
        //Logger.d("订阅者收到事件");
        statusLayoutManager.showLoading();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
