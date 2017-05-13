package com.helldefender.enjoylife.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.presenter.impl.DetailContentPresenterImpl;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;
import com.helldefender.enjoylife.ui.adapter.DetailContentRVAdapter;
import com.helldefender.enjoylife.ui.adapter.base.MultiViewType;
import com.helldefender.enjoylife.utils.ToolbarUtil;
import com.helldefender.enjoylife.view.DetailContentView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.helldefender.enjoylife.ui.adapter.DetailContentRVAdapter.DETAIL_TYPE_COMMENT;
import static com.helldefender.enjoylife.ui.adapter.DetailContentRVAdapter.DETAIL_TYPE_CONTENT;


/**
 * Created by Helldefender on 2017/2/7.
 */

public class DetailContentActivity extends BaseActivity implements DetailContentView {

    @Inject
    DetailContentPresenterImpl detailContentPresenter;

    @BindView(R.id.toolbar_detailContent)
    Toolbar mToolBar;

    @BindView(R.id.appBar_detailContent)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.tab_detailContent)
    FloatingActionButton mFAB;

    @BindView(R.id.collapsingToolBar_detailContent)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.rv_detailContent)
    RecyclerView mRecyclerView;

    private List<String> data;

    private CollapsingToolbarLayoutState state;

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    public static void start(Context context) {
        start(context, null);
    }

    public static void start(Context context, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(context, DetailContentActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }

    @Override
    public void showLoadingLayout() {

    }

    @Override
    public void showEmptyLayout() {

    }

    @Override
    public void showErrorLayout(int message) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_content;
    }

    @Override
    public int getEmptyLayoutId() {
        return 0;
    }

    @Override
    public void initInject() {
        mActivityComponent.inject(this);
    }

    @Override
    public void initPresenter() {
        mPresenter = detailContentPresenter;
        mPresenter.attachView(this);
        //mPresenter.onCreate();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //handleStatusBar();


        //setUpStatusBar(R.color.myColor);
        handleStatusBar();

        ToolbarUtil toolbarUtil = new ToolbarUtil();
        //toolbarUtil.titleString = "标题";
        //toolbarUtil.isNeedNavigate = false;

        mToolBar.setTitleTextColor(Color.RED);
        setToolBar(R.id.toolbar_detailContent, toolbarUtil);

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        collapsingToolbarLayout.setTitle("我是标题");//设置title为EXPANDED

                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        collapsingToolbarLayout.setTitle("我是标题");//设置title不显示
                        //mTitleText.setVisibility(View.VISIBLE);//隐藏播放按钮
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                            //mTitleText.setVisibility(View.GONE);//由折叠变为中间状态时隐藏播放按钮
                        }
                        collapsingToolbarLayout.setTitle("我是标题");//设置title为INTERNEDIATE
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        data = new ArrayList<String>();
        data.add("测试");
        data.add("测试");
        data.add("测试");
        data.add("测试");
        data.add("测试");
        data.add("测试");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new DetailContentRVAdapter(this, new MultiViewType<String>() {
            @Override
            public int getViewTypeSpanCount(int viewType) {
                return 0;
            }

            @Override
            public int getItemViewType(int position) {
                if (position == 0) {
                    return DETAIL_TYPE_CONTENT;
                }

                return DETAIL_TYPE_COMMENT;
            }

            @Override
            public int getLayoutId(int viewType) {
                if (viewType == DETAIL_TYPE_CONTENT) {
                    return R.layout.item_detail_rv_content;
                }
                return R.layout.item_detail_rv_comment;
            }
        }, data));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_content, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }
}
