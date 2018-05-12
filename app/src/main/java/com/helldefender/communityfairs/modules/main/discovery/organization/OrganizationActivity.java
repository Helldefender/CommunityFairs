package com.helldefender.communityfairs.modules.main.discovery.organization;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.PagerAdapter;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.app.BaseActivity;
import com.helldefender.communityfairs.app.ViewModelFactory;
import com.helldefender.communityfairs.databinding.ActivityOrganizationBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helldefender on 2017/6/14.
 */

public class OrganizationActivity extends BaseActivity<ActivityOrganizationBinding, OrganizationViewModel> {

    private List<View> mViewList = new ArrayList<>();
    private PageAdapter mPageAdapter;
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
        initView();
    }

    private void initView() {
        mPageAdapter=new PageAdapter();
        mViewList.add();

        binding.viewpagerTitleOrganization.initData(new String[]{getStringRes(R.string.basic_Info), getStringRes(R.string.logcat_Info), getStringRes(R.string.options_Info), getStringRes(R.string.intro_Info)}, binding.vpOrganization, 0);
        binding.vpOrganization.setAdapter(new PageAdapter());
        binding.vpOrganization.setOffscreenPageLimit(mPageAdapter.getCount());

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
                ((OrganizationTabFragment) mPageAdapter.getFragments().get(0)).getRecyclerView().scrollToPosition(0);
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

    private class PageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
