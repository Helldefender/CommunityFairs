package com.helldefender.enjoylife.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.listener.OnLoadMoreListener;
import com.helldefender.enjoylife.listener.OnRefreshListener;
import com.helldefender.enjoylife.presenter.impl.HomePagePresenterImpl;
import com.helldefender.enjoylife.modules.ConstellationAdapter;
import com.helldefender.enjoylife.modules.GirdDropDownAdapter;
import com.helldefender.enjoylife.modules.ListDropDownAdapter;
import com.helldefender.enjoylife.ui.activity.DetailContentActivity;
import com.helldefender.enjoylife.ui.adapter.HomePageAdapter;
import com.helldefender.enjoylife.ui.adapter.base.BaseAdapter;
import com.helldefender.enjoylife.ui.adapter.base.MultiViewType;
import com.helldefender.enjoylife.ui.fragment.base.BaseFragment;
import com.helldefender.enjoylife.view.HomePageView;
import com.helldefender.enjoylife.widget.BannerView;
import com.helldefender.enjoylife.widget.recyclerview.LoadMoreFooterView;
import com.helldefender.enjoylife.widget.recyclerview.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.helldefender.enjoylife.ui.adapter.HomePageAdapter.TYPE_BANNER;
import static com.helldefender.enjoylife.ui.adapter.HomePageAdapter.TYPE_CONTENT;
import static com.helldefender.enjoylife.ui.adapter.HomePageAdapter.TYPE_FILTER;

/**
 * Created by Helldefender on 2017/2/5.
 */

public class HomePageFragment extends BaseFragment implements HomePageView, OnLoadMoreListener, OnRefreshListener {

    private static final String TAG = HomePageFragment.class.getSimpleName();

    @Inject
    HomePagePresenterImpl homePagePresenter;

    @BindView(R.id.rv_homepage_refresh)
    RefreshRecyclerView homeRecyclerView;

//    @BindView(R.id.filter_homepage_menu)
//    FilterMenu mFilterMenu;

    Unbinder unbinder;

    private BannerView bannerView;

    private String headers[] = {"城市", "年龄", "性别", "星座"};
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter cityAdapter;
    private ListDropDownAdapter ageAdapter;
    private ListDropDownAdapter sexAdapter;
    private ConstellationAdapter constellationAdapter;

    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String ages[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    private String sexs[] = {"不限", "男", "女"};
    private String constellations[] = {"不限", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};

    private int constellationPosition = 0;

    private static final int SPAN_COUNT = 6;

    private LoadMoreFooterView loadMoreFooterView;

    private HomePageAdapter homePageAdapter;

    private GridLayoutManager gridLayoutManager;

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
        //initFilterMenu();
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

//    private void initFilterMenu() {
//        mFilterMenu.setVisibility(View.GONE);
//
//        final ListView cityView = new ListView(getHoldingActivity());
//        cityAdapter = new GirdDropDownAdapter(getHoldingActivity(), Arrays.asList(citys));
//        cityView.setDividerHeight(0);
//        cityView.setAdapter(cityAdapter);
//
//        //init age menu
//        final ListView ageView = new ListView(getHoldingActivity());
//        ageView.setDividerHeight(0);
//        ageAdapter = new ListDropDownAdapter(getHoldingActivity(), Arrays.asList(ages));
//        ageView.setAdapter(ageAdapter);
//
//        //init sex menu
//        final ListView sexView = new ListView(getHoldingActivity());
//        sexView.setDividerHeight(0);
//        sexAdapter = new ListDropDownAdapter(getHoldingActivity(), Arrays.asList(sexs));
//        sexView.setAdapter(sexAdapter);
//
//        //init constellation
//        final View constellationView = LayoutInflater.from(getHoldingActivity()).inflate(R.layout.custom_layout, null);
//        GridView constellation = ButterKnife.findById(constellationView, R.id.constellation);
//        constellationAdapter = new ConstellationAdapter(getHoldingActivity(), Arrays.asList(constellations));
//        constellation.setAdapter(constellationAdapter);
//        TextView ok = ButterKnife.findById(constellationView, R.id.ok);
//        ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mFilterMenu.setTabText(constellationPosition == 0 ? headers[3] : constellations[constellationPosition]);
//                mFilterMenu.closeMenu();
//            }
//        });
//
//        //init popupViews
//        popupViews.add(cityView);
//        popupViews.add(ageView);
//        popupViews.add(sexView);
//        popupViews.add(constellationView);
//
//        //add item click event
//        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                cityAdapter.setCheckItem(position);
//                mFilterMenu.setTabText(position == 0 ? headers[0] : citys[position]);
//                mFilterMenu.closeMenu();
//            }
//        });
//
//        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ageAdapter.setCheckItem(position);
//                mFilterMenu.setTabText(position == 0 ? headers[1] : ages[position]);
//                mFilterMenu.closeMenu();
//            }
//        });
//
//        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                sexAdapter.setCheckItem(position);
//                mFilterMenu.setTabText(position == 0 ? headers[2] : sexs[position]);
//                mFilterMenu.closeMenu();
//            }
//        });
//
//        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                constellationAdapter.setCheckItem(position);
//                constellationPosition = position;
//            }
//        });
//
//        //init context view
//        TextView contentView = new TextView(getHoldingActivity());
//        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        //contentView.setText("内容显示区域");
//        contentView.setGravity(Gravity.CENTER);
//        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
//
//        //init dropdownview
//        mFilterMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
//    }

    private void initRecyclerView() {
        gridLayoutManager = new GridLayoutManager(getHoldingActivity(), SPAN_COUNT, GridLayoutManager.VERTICAL, false);
        //fragment中的context
        homeRecyclerView.setLayoutManager(gridLayoutManager);

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
                    case TYPE_FILTER:
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
                if (position == 1) {
                    return TYPE_FILTER;
                }

                return TYPE_CONTENT;

            }

            @Override
            public int getLayoutId(int viewType) {
                if (viewType == TYPE_BANNER) {
                    return R.layout.item_homepage_rv_banner;
                } else if (viewType == TYPE_FILTER) {
                    return R.layout.item_homepage_rv_filter;
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

        homeRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int position = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
//                if (position == 0) {
//                    mFilterMenu.setVisibility(View.GONE);
//                } else {
//                    mFilterMenu.setVisibility(View.VISIBLE);
//                }
            }
        });
    }

    private void refresh() {
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

//    @Subscribe
//    public void onLoggingStatusChange(LoggingStatusEvent loggingStatusEvent) {
//        //Logger.d("订阅者收到事件");
//        statusLayoutManager.showLoading();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        EventBus.getDefault().unregister(this);
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//
//        if (mFilterMenu.isShowing()) {
//            mFilterMenu.closeMenu();
//        }

        unbinder.unbind();
    }

}
