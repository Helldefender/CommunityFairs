//package com.helldefender.communityfairs.modules.main.discovery.organization;
//
//import android.os.Bundle;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.view.ViewPager;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.helldefender.communityfairs.R;
//import com.helldefender.communityfairs.modules.main.discovery.FragmentPageAdapter;
//import com.helldefender.communityfairs.modules.main.discovery.event.OrganizationEventFragment;
//import com.helldefender.communityfairs.modules.main.homepage.OrganizatinPageFragment;
//import com.helldefender.communityfairs.app.BaseActivity;
//import com.helldefender.communityfairs.utils.TabIndicatorUtil;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
///**
// * Created by Helldefender on 2017/6/14.
// */
//
//public class OrganizationActivity extends BaseActivity {
//
//    private final int TAB_INDICATOR_MARGIN_LEFT = 55;
//
//    private final int TAB_INDICATOR_MARGIN_RIGHT = 55;
//
//    //借出详情
//    public static final String TYPE_OUT_PAGE = "outPage";
//
//    //借入详情
//    public static final String TYPE_IN_PAGE = "inPage";
//
//    @BindView(R.id.img_organization_back)
//    ImageView imgOrganizationBack;
//    @BindView(R.id.tv_organization_follow)
//    TextView tvOrganizationFollow;
//    @BindView(R.id.img_organization_v)
//    ImageView imgOrganizationV;
//    @BindView(R.id.tv_organization_name)
//    TextView tvOrganizationName;
//    @BindView(R.id.tv_organization_events)
//    TextView tvOrganizationEvents;
//    @BindView(R.id.tv_organization_follow_num)
//    TextView tvOrganizationFollowNum;
//    @BindView(R.id.ll_organization_header)
//    LinearLayout llOrganizationHeader;
////    @BindView(R.id.appbar_organization)
////    AppBarLayout mAppBarLayout;
//    @BindView(R.id.vp_organization)
//    ViewPager mViewPager;
//    @BindView(R.id.tabLayout_organization)
//    TabLayout mTabLayout;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_organization;
//    }
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
//    protected void widgetClick(View view) {
//
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
//
//        handleStatusBar();
//
//        initViewPager();
//    }
//
//    private void initViewPager() {
//        List<Fragment> mFragmentList = new ArrayList<Fragment>();
//
//        mFragmentList.add(new OrganizatinPageFragment());
//        mFragmentList.add(new OrganizationEventFragment());
//
//
//        String[] mTabTitles = new String[]{"主页", "活动"};
//
//        mViewPager.setAdapter(new FragmentPageAdapter(getSupportFragmentManager(), mFragmentList, mTabTitles));
//        mTabLayout.setupWithViewPager(mViewPager);
//
//        TabIndicatorUtil.setTabIndicatorWidth(this, mTabLayout, TAB_INDICATOR_MARGIN_LEFT, TAB_INDICATOR_MARGIN_RIGHT);
//
//        imgOrganizationBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        tvOrganizationFollow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvOrganizationFollow.setText("已关注");
//            }
//        });
//    }
//}
