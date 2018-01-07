package com.helldefender.communityfairs.modules.main.discovery.organization;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.view.KeyEvent;
import android.view.View;

import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.app.BaseActivity;
import com.helldefender.communityfairs.app.ViewModelFactory;
import com.helldefender.communityfairs.databinding.ActivityOrganizationBinding;
import com.helldefender.communityfairs.utils.TabIndicatorUtil;

/**
 * Created by Helldefender on 2017/6/14.
 */

public class OrganizationActivity extends BaseActivity<ActivityOrganizationBinding, OrganizationViewModel> {

    //fragment的适配器
    private TabFragmentAdapter tabFragmentAdapter;

    //是否隐藏了头部
    private boolean isHideHeaderLayout = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_organization;
    }

    @Override
    protected int getVariableId() {
        return BR.viewModel;
    }

    @Override
    protected OrganizationViewModel getViewModel() {
        return ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(OrganizationViewModel.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }

    private void init() {
        tabFragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), this);
        binding.vpOrganization.setAdapter(tabFragmentAdapter);
        binding.tabLOrganization.setupWithViewPager(binding.vpOrganization);

        binding.vpOrganization.setOffscreenPageLimit(tabFragmentAdapter.getCount());

        TabIndicatorUtil.setTabIndicatorWidth(this, binding.tabLOrganization, 45, 45);

        initAppBarLayout();
    }

    private void initAppBarLayout() {
        LayoutTransition mTransition = new LayoutTransition();
        ObjectAnimator addAnimator = ObjectAnimator.ofFloat(null, "translationY", 0, 1.f).
                setDuration(mTransition.getDuration(LayoutTransition.APPEARING));
        mTransition.setAnimator(LayoutTransition.APPEARING, addAnimator);

        final int headerHeight = getResources().getDimensionPixelOffset(R.dimen.header_height);
        binding.appBarLOrganization.setLayoutTransition(mTransition);

        binding.appBarLOrganization.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                verticalOffset = Math.abs(verticalOffset);
                if (verticalOffset >= headerHeight) {
                    isHideHeaderLayout = true;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            AppBarLayout.LayoutParams mParams = (AppBarLayout.LayoutParams) binding.llHeaderOrganization.getLayoutParams();
                            mParams.setScrollFlags(0);
                            binding.llHeaderOrganization.setLayoutParams(mParams);
                            binding.llHeaderOrganization.setVisibility(View.GONE);
                        }
                    }, 100);
                }
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isHideHeaderLayout) {
                isHideHeaderLayout = false;
                ((OrganizationTabFragment) tabFragmentAdapter.getFragments().get(0)).getRecyclerView().scrollToPosition(0);
                binding.llHeaderOrganization.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AppBarLayout.LayoutParams mParams = (AppBarLayout.LayoutParams) binding.llHeaderOrganization.getLayoutParams();
                        mParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL |
                                AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
                        binding.llHeaderOrganization.setLayoutParams(mParams);
                    }
                }, 300);
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
