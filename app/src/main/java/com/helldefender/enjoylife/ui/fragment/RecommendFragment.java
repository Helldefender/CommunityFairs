package com.helldefender.enjoylife.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.delete.server.HttpMethods;
import com.helldefender.enjoylife.delete.server.ProgressSubscriber;
import com.helldefender.enjoylife.delete.server.SubscriberOnNextListener;
import com.helldefender.enjoylife.delete.server.entity.TypeBean;
import com.helldefender.enjoylife.ui.adapter.RecommendViewPagerAdapter;
import com.helldefender.enjoylife.ui.adapter.base.HeaderRecyclerAdapter;
import com.helldefender.enjoylife.ui.fragment.base.BaseFragment;
import com.jude.utils.JUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helldefender on 2017/2/12.
 */

public class RecommendFragment extends BaseFragment {

    private static final int INDICATOR_NUMBER = 4;

    private RelativeLayout mRelativeLayout;

    private RelativeLayout.LayoutParams relativeLP;

    private LinearLayout mLinearLayout;

    private ViewPager mViewPager;

    private RelativeLayout.LayoutParams viewPagerLP;

    private RelativeLayout.LayoutParams linearLP;

    private RecommendViewPagerAdapter recommendViewPagerAdapter;

    private ImageView[] imageIndicators;

    private ArrayList<RelativeLayout> views;

    private ImageHandler handler = new ImageHandler(new WeakReference<RecommendFragment>((this)));

    public RecyclerView mRecyclerView;

    private LinearLayoutManager mLinearLayoutManager;

    private HeaderRecyclerAdapter mHeaderRecyclerAdapter;

    //private HomePagerRecyclerAdapter homePagerRecyclerAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private List<TypeBean.FirstPagesBean> data;

    private SubscriberOnNextListener recommendListener;

//    @Override
//    protected void handleView(View view, Bundle savedInstanceState) {
//        initView(view);
//
//        setRecyclerView(view);
//
//        setSwipeRefreshLayout(view);
//
//        //setRecommendListener();
//
////        loadMoreNewsListener = new SubscriberOnNextListener<NewsBean>() {
////            @Override
////            public void onNext(NewsBean newsBean) {
////                for (NewsBean.StoriesBean storiesBean : newsBean.getStories()) {
////                    storiesBean.setData(newsBean.getDate());
////                    storiesList.add(storiesBean);
////                }
////                homePagerRecyclerAdapter.notifyDataSetChanged();
////                mHeaderRecyclerAdapter.notifyDataSetChanged();
////            }
////        };
//
//        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                handler.sendMessage(Message.obtain(handler, ImageHandler.MSG_PAGE_CHANGED, position, 0));
//                int total = imageIndicators.length;
//                position %= total;
//                for (int i = 0; i < total; i++) {
//                    if (i == position) {
//                        imageIndicators[i].setImageResource(R.mipmap.carousel_strip);
//                    } else {
//                        imageIndicators[i].setImageResource(R.mipmap.carousel_circle);
//                    }
//                }
//            }
//
//            //复写该方法实现轮播效果的暂停和恢复
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                switch (state) {
//                    case ViewPager.SCROLL_STATE_DRAGGING:
//                        handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
//                        break;
//                    case ViewPager.SCROLL_STATE_IDLE:
//                        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });
//        mViewPager.setCurrentItem(Integer.MAX_VALUE / 2);
//
//        handler.sendEmptyMessageDelayed(ImageHandler.MSG_KEEP_SILENT, ImageHandler.MSG_DELAY);
//
//        homePagerRecyclerAdapter.setOnItemClickListener(new HomePagerRecyclerAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Bundle bundle = new Bundle();
//                //bundle.putInt("",);
//                Intent intent = new Intent(getHoldingActivity(), DetailContentActivity.class);
//                //intent.putExtras(bundle);
////                intent.putExtra("id", data.get(position).getId());
////                intent.putExtra("content", data.get(position).getContent());
////                intent.putExtra("title", data.get(position).getTheme());
////                intent.putExtra("time", data.get(position).getTime());
////                intent.putExtra("photo", data.get(position).getPhoto());
////                intent.putExtra("u_photo", data.get(position).getU_photo());
////                intent.putExtra("username", data.get(position).getUsername());
////                intent.putExtra("liking", data.get(position).getLiking());
////                intent.putExtra("watching", data.get(position).getWatching());
//                startActivity(intent);
//            }
//        });
//
//        recommendViewPagerAdapter.setOnItemClickListener(new RecommendViewPagerAdapter.onItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                //Bundle bundle = new Bundle();
//                //bundle.putInt("newsId", topStoriesList.get(position % 5).getId());
//                Intent intent = new Intent(getHoldingActivity(), DetailContentActivity.class);
//                //intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });
////
////        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
////            @Override
////            public boolean onTouch(View v, MotionEvent event) {
////                switch (event.getAction()) {
////                    case MotionEvent.ACTION_MOVE:
////                        int firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
////                        if (firstVisibleItem > 0){
////                            //实现搜索框由透明到有色的渐变 边框弧度 状态栏高度
////                            //searchLayout.getBackground().setAlpha();
////                            searchLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
////                        }else{
////                            Log.d("Dai","v.getTop---"+v.getTop()+v.getY());
////                            searchLayout.getBackground().setAlpha(0);
////                        }
//////                            baseActivity.getSupportActionBar().setTitle(storiesList.get(firstVisibleItem - 1).getWeekend());
//////                        int lastVisibablePosition = mLinearLayoutManager.findLastVisibleItemPosition();
//////                        if (lastVisibablePosition >= mLinearLayoutManager.getItemCount() - 2) {
//////                            final int preDate = Integer.parseInt(storiesList.get(lastVisibablePosition - 1).getData());
//////                            httpRequestLoadMore(preDate);
//////                        }
////                        break;
////                }
////                return false;
////            }
////        });
//
////        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
////            @Override
////            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
////                super.onScrolled(recyclerView, dx, dy);
////                int firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
////                if (firstVisibleItem > 0){
////                    //实现搜索框由透明到有色的渐变 边框弧度 状态栏高度
////                    searchLayout.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
////                }else{
////                    int top = recyclerView.getChildAt(0).getTop();
////                    int alpha=(int) (top*255/750);
////                    Log.d("Dai","alpha---"+top);
////                    if (alpha > 255) {
////                        alpha=255;
////                    }
////                    searchLayout.getBackground().setAlpha(alpha);
////                }
////            }
////        });
//
//
//    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected int getEmptyLayoutId() {
        return 0;
    }

    private void initView(View view) {
        data = new ArrayList<TypeBean.FirstPagesBean>();
        data.add(new TypeBean.FirstPagesBean());
        data.add(new TypeBean.FirstPagesBean());
        data.add(new TypeBean.FirstPagesBean());
        data.add(new TypeBean.FirstPagesBean());
        data.add(new TypeBean.FirstPagesBean());
        data.add(new TypeBean.FirstPagesBean());
        data.add(new TypeBean.FirstPagesBean());
        data.add(new TypeBean.FirstPagesBean());
        data.add(new TypeBean.FirstPagesBean());



        mRelativeLayout = new RelativeLayout(getHoldingActivity());
        relativeLP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, JUtils.dip2px(200));
        mRelativeLayout.setLayoutParams(relativeLP);

        mViewPager = new ViewPager(getHoldingActivity());
        viewPagerLP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, JUtils.dip2px(200));
        mViewPager.setLayoutParams(viewPagerLP);
        mRelativeLayout.addView(mViewPager);

        views = new ArrayList<RelativeLayout>();
        recommendViewPagerAdapter = new RecommendViewPagerAdapter(views);
        handleViewPageItem();

        mLinearLayout = new LinearLayout(getHoldingActivity());
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        mLinearLayout.setGravity(Gravity.CENTER);
        linearLP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLP.addRule(RelativeLayout.CENTER_HORIZONTAL);
        linearLP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        mLinearLayout.setLayoutParams(linearLP);
        mRelativeLayout.addView(mLinearLayout);

        imageIndicators = new ImageView[INDICATOR_NUMBER];
        handleIndicator();
    }

    private void setRecyclerView(View view) {
//        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
//
//        mLinearLayoutManager = new LinearLayoutManager(getHoldingActivity());
//
//        mRecyclerView.setLayoutManager(mLinearLayoutManager);
//
//        homePagerRecyclerAdapter = new HomePagerRecyclerAdapter(getHoldingActivity(), R.layout.item_homepage_recyclerview, data);
//
//        mHeaderRecyclerAdapter = new HeaderRecyclerAdapter(homePagerRecyclerAdapter);
//        mHeaderRecyclerAdapter.addHeaderView(mRelativeLayout);
//        mRecyclerView.setAdapter(mHeaderRecyclerAdapter);


    }

    private void setSwipeRefreshLayout(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHeaderRecyclerAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 1500);
            }
        });

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_bright), getResources().getColor(android.R.color.holo_green_light), getResources().getColor(android.R.color.holo_orange_light));
    }

    private void setRecommendListener() {
        //注意修改类型
        recommendListener = new SubscriberOnNextListener<TypeBean>() {
            @Override
            public void onNext(TypeBean typeBean) {
                for (TypeBean.FirstPagesBean firstPagesBean : typeBean.getFirstPages()) {
                    data.add(firstPagesBean);
                }
                mHeaderRecyclerAdapter.notifyDataSetChanged();
                //homePagerRecyclerAdapter.notifyDataSetChanged();
            }
        };

        HttpMethods.getInstance().getRecommendContent(new ProgressSubscriber(recommendListener, getHoldingActivity()), "1");

    }

    private void handleIndicator() {
        ImageView imageView;
        for (int i = 0; i < INDICATOR_NUMBER; i++) {
            imageView = new ImageView(getHoldingActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, JUtils.dip2px(25));
            layoutParams.setMargins(JUtils.dip2px(4), JUtils.dip2px(10), JUtils.dip2px(4), JUtils.dip2px(4));
            imageView.setLayoutParams(layoutParams);
            imageIndicators[i] = imageView;
            if (i == 0) {
                imageIndicators[i].setImageResource(R.mipmap.carousel_strip);
            } else {
                imageIndicators[i].setImageResource(R.mipmap.carousel_circle);
            }
            ((ViewGroup) mLinearLayout).addView(imageView);
        }
    }

    private void handleViewPageItem() {
        views.add((RelativeLayout) LayoutInflater.from(getActivity()).inflate(R.layout.item_homepage_image_carouse, null));
        views.add((RelativeLayout) LayoutInflater.from(getActivity()).inflate(R.layout.item_homepage_image_carouse, null));
        views.add((RelativeLayout) LayoutInflater.from(getActivity()).inflate(R.layout.item_homepage_image_carouse, null));
        views.add((RelativeLayout) LayoutInflater.from(getActivity()).inflate(R.layout.item_homepage_image_carouse, null));
        mViewPager.setAdapter(recommendViewPagerAdapter);
    }

    public void scrollToTop() {
        mRecyclerView.scrollToPosition(0);
    }

    private static class ImageHandler extends Handler {

        protected static final int MSG_UPDATE_IMAGE = 1;

        protected static final int MSG_KEEP_SILENT = 2;

        protected static final int MSG_BREAK_SILENT = 3;

        protected static final int MSG_PAGE_CHANGED = 4;

        protected static final long MSG_DELAY = 3000;

        private WeakReference<RecommendFragment> weakReference;
        private int currentItem = 0;

        public ImageHandler(WeakReference<RecommendFragment> weakReference) {
            this.weakReference = weakReference;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            RecommendFragment fragment = weakReference.get();

            if (fragment == null) {
                return;
            }

            if (fragment.handler.hasMessages(MSG_UPDATE_IMAGE)) {
                fragment.handler.removeMessages(MSG_UPDATE_IMAGE);
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    fragment.mViewPager.setCurrentItem(currentItem);
                    fragment.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    break;
                case MSG_BREAK_SILENT:
                    fragment.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    currentItem = msg.arg1;
                    break;
                default:
                    break;
            }
        }
    }
}
