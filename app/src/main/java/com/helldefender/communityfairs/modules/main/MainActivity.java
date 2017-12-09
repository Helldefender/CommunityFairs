package com.helldefender.communityfairs.modules.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;

import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.app.BaseActivity;
import com.helldefender.communityfairs.app.ViewModelFactory;
import com.helldefender.communityfairs.databinding.ActivityMainBinding;
import com.helldefender.communityfairs.modules.main.homepage.HomePageFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    private static final int TAB_HOMEPAGE = 0;

    private static final int TAB_DISCOVERY = 1;

    private static final int TAB_MESSAGE = 2;

    private static final int TAB_USER = 3;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getVariableId() {
        return BR.viewModel;
    }

    @Override
    protected MainViewModel getViewModel() {
        return ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(MainViewModel.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        addPageChangeListener();
    }

    private void initView() {
        initViewPager();
    }

    private void initViewPager() {
        List<Fragment> fragmentList = new ArrayList<Fragment>();

        HomePageFragment homePageFragment = new HomePageFragment();
        //DiscoveryFragment discoveryFragment = new DiscoveryFragment();
        //MessageFragment messageFragment = new MessageFragment();
        //UserFragment userFragment = new UserFragment();

        fragmentList.add(homePageFragment);
        fragmentList.add(homePageFragment);
        fragmentList.add(homePageFragment);
        fragmentList.add(homePageFragment);
        //fragmentList.add(discoveryFragment);
        //fragmentList.add(messageFragment);
        //fragmentList.add(userFragment);

        binding.viewpager.setOffscreenPageLimit(0);

        new TabViewPagerAdapter(getSupportFragmentManager(), binding.viewpager, fragmentList);
    }

    private void addPageChangeListener() {
        binding.viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int id) {
                switch (id) {
                    case TAB_HOMEPAGE:
                        binding.radioHomepage.setChecked(true);
                        break;
                    case TAB_DISCOVERY:
                        binding.radioDiscovery.setChecked(true);
                        break;
                    case TAB_MESSAGE:
                        binding.radioMessage.setChecked(true);
                        break;
                    case TAB_USER:
                        binding.radioUser.setChecked(true);
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

    public void onClickRadioBtn(View view) {
        switch (view.getId()) {
            case R.id.radio_homepage:
                binding.viewpager.setCurrentItem(TAB_HOMEPAGE, false);
                break;
            case R.id.radio_discovery:
                binding.viewpager.setCurrentItem(TAB_DISCOVERY, false);
                break;
            case R.id.radio_message:
                binding.viewpager.setCurrentItem(TAB_MESSAGE, false);
                break;
            case R.id.radio_user:
                binding.viewpager.setCurrentItem(TAB_USER, false);
                break;
            case R.id.img_posted:
                //PublishActivity.start(MainActivity.this, null);
                break;
        }
    }

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
