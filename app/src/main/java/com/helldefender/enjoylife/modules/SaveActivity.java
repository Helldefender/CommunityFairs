package com.helldefender.enjoylife.modules;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.modules.discovery.FragmentPageAdapter;
import com.helldefender.enjoylife.modules.setting.SaveEventFragment;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;
import com.helldefender.enjoylife.utils.TabIndicatorUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Helldefender on 2017/6/16.
 */

public class SaveActivity extends BaseActivity {

    private final int TAB_INDICATOR_MARGIN_LEFT = 55;

    private final int TAB_INDICATOR_MARGIN_RIGHT = 55;

    @BindView(R.id.title_save)
    TextView titleSave;
    @BindView(R.id.tabLayout_save_msg)
    TabLayout mTabLayout;
    @BindView(R.id.vp_drawer_save)
    ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_save;
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

        initViewPager();

        titleSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViewPager() {
        List<Fragment> mFragmentList = new ArrayList<Fragment>();

        mFragmentList.add(new SaveFragment());
        mFragmentList.add(new SaveEventFragment());


        String[] mTabTitles = new String[]{"活动", "组织"};

        mViewPager.setAdapter(new FragmentPageAdapter(getSupportFragmentManager(), mFragmentList, mTabTitles));
        mTabLayout.setupWithViewPager(mViewPager);

        TabIndicatorUtil.setTabIndicatorWidth(this, mTabLayout, TAB_INDICATOR_MARGIN_LEFT, TAB_INDICATOR_MARGIN_RIGHT);
    }
}
