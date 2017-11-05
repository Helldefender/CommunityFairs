package com.helldefender.enjoylife.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;
import com.helldefender.enjoylife.ui.adapter.TabViewPagerAdapter;
import com.helldefender.enjoylife.ui.fragment.BlankFragment;
import com.helldefender.enjoylife.ui.fragment.DiscoveryAppBarFragment;
import com.helldefender.enjoylife.ui.fragment.DiscoveryFragment;
import com.helldefender.enjoylife.ui.fragment.HomePageFragment;
import com.helldefender.enjoylife.ui.fragment.MessageFragment;
import com.helldefender.enjoylife.ui.fragment.UserFragment;
import com.helldefender.enjoylife.ui.fragment.base.BaseFragment;
import com.helldefender.enjoylife.widget.TabViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private static final int TAB_HOMEPAGE = 0;

    private static final int TAB_DISCOVERY = 1;

    private static final int TAB_MESSAGE = 2;

    private static final int TAB_USER = 3;

//    @FragmentType("BlankFragment")
//    BlankFragment blankFragment;
//
//    @FragmentType("DiscoveryAppBarFragment")
//    DiscoveryAppBarFragment discoveryAppBarFragment;

    @BindView(R.id.viewpager)
    TabViewPager viewpager;

    @BindView(R.id.radio_homepage)
    RadioButton radioHomepage;

    @BindView(R.id.radio_discovery)
    RadioButton radioDiscovery;

    @BindView(R.id.radio_message)
    RadioButton radioMessage;

    @BindView(R.id.radio_user)
    RadioButton radioUser;

    private int appBarLayoutId;

    public static void start(Context context) {
        start(context, null);
    }

    public static void start(Context context, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initInject() {
        mActivityComponent.inject(this);
    }

    @Override
    public void initPresenter() {
    }

    @Override
    protected void widgetClick(View view) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handleStatusBar();

        initView();

        addPageChangeListener();
    }

    private void initView() {
        //mActivityComponent;

        initViewPager();
    }

    private void initViewPager() {
        List<Fragment> fragmentList = new ArrayList<Fragment>();

        HomePageFragment homePageFragment = new HomePageFragment();
        DiscoveryFragment discoveryFragment = new DiscoveryFragment();
        MessageFragment messageFragment = new MessageFragment();
        UserFragment userFragment = new UserFragment();

        fragmentList.add(homePageFragment);
        fragmentList.add(discoveryFragment);
        fragmentList.add(messageFragment);
        fragmentList.add(userFragment);

        viewpager.setOffscreenPageLimit(0);

        new TabViewPagerAdapter(getSupportFragmentManager(), viewpager, fragmentList);
    }

    private void addPageChangeListener() {
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int id) {
                switch (id) {
                    case TAB_HOMEPAGE:
                        appBarLayoutId = 0;
                        radioHomepage.setChecked(true);
                        break;
                    case TAB_DISCOVERY:
                        appBarLayoutId = R.layout.fragment_discovery_appbar;
                        radioDiscovery.setChecked(true);
                        break;
                    case TAB_MESSAGE:
                        appBarLayoutId = 0;
                        radioMessage.setChecked(true);
                        break;
                    case TAB_USER:
                        appBarLayoutId = 0;
                        radioUser.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    @OnClick({R.id.radio_homepage, R.id.radio_discovery, R.id.radio_message, R.id.radio_user, R.id.img_posted})
    public void onClickRadioBtn(View view) {
        switch (view.getId()) {
            case R.id.radio_homepage:
                viewpager.setCurrentItem(TAB_HOMEPAGE, false);
                replaceAppBarLayout();
                doubleClick(view);
                break;
            case R.id.radio_discovery:
                viewpager.setCurrentItem(TAB_DISCOVERY, false);
                replaceAppBarLayout();
                break;
            case R.id.radio_message:
                viewpager.setCurrentItem(TAB_MESSAGE, false);
                replaceAppBarLayout();
                break;
            case R.id.radio_user:
                viewpager.setCurrentItem(TAB_USER, false);
                replaceAppBarLayout();
                break;
            case R.id.img_posted:
                PublishActivity.start(MainActivity.this, null);
                break;
        }
    }

    private void replaceAppBarLayout() {
        BaseFragment baseFragment = getAppBarFragment();
        addAppBarFragment(baseFragment);
    }

    private BaseFragment getAppBarFragment() {
        if (appBarLayoutId != 0) {
//            Log.d("DAI", "discovery" + (discoveryAppBarFragment == null));
//            return discoveryAppBarFragment;
            return DiscoveryAppBarFragment.getInstance();
        }
//        Log.d("DAI", "blank" + (blankFragment == null));
//        return blankFragment;
        return BlankFragment.getInstance();
    }

    private void addAppBarFragment(BaseFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout_main_fragmentContainer, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    long firstClickTime = 0;

    long secondClickTime = 0;

    public void doubleClick(View view) {

        if (firstClickTime > 0) {
            secondClickTime = SystemClock.uptimeMillis();
            if (secondClickTime - firstClickTime < 500) {
                //homePageFragment.scrollToTop();
            }
            firstClickTime = 0;
            return;
        }

        firstClickTime = SystemClock.uptimeMillis();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    firstClickTime = 0;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

//    private void replaceAppBarLayout() {
//        BaseFragment baseFragment = getAppBarFragment();
//        addAppBarFragment(baseFragment);
//    }
//
//    private BaseFragment getAppBarFragment() {
//        if (appBarLayoutId != 0)
//            return AppBarFragment.getInstance();
//        return BlankFragment.getInstance();
//    }
//
//    private void addAppBarFragment(BaseFragment fragment) {
//        if (fragment != null) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.appbar_fragment_container, fragment, fragment.getClass().getSimpleName())
//                    .addToBackStack(fragment.getClass().getSimpleName())
//                    .commitAllowingStateLoss();
//        }
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
