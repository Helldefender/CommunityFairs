package com.helldefender.enjoylife.ui.adapter;

/**
 * Created by Helldefender on 2017/2/5.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.helldefender.enjoylife.widget.TabViewPager;

import java.util.List;

public class TabViewPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {

    private List<Fragment> fragmentList;

    private FragmentManager fragmentManager;

    private TabViewPager viewPager;

    private int currentPageIndex = 0;

    private OnExtraPageChangeListener onExtraPageChangeListener;

    public TabViewPagerAdapter(FragmentManager fragmentManager, TabViewPager viewPager, List<Fragment> fragments) {
        fragmentList = fragments;
        this.fragmentManager = fragmentManager;
        this.viewPager = viewPager;
        this.viewPager.setAdapter(this);
        this.viewPager.setOnPageChangeListener(this);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(fragmentList.get(position).getView());
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment =  fragmentList.get(position);
        if (!fragment.isAdded()) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(fragment, fragment.getClass().getSimpleName());
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();
        }

        if (fragment.getView().getParent() == null) {
            container.addView(fragment.getView());
        }

        return fragment.getView();
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public OnExtraPageChangeListener getOnExtraPageChangeListener() {
        return onExtraPageChangeListener;
    }

    public void setOnExtraPageChangeListener(OnExtraPageChangeListener onExtraPageChangeListener) {
        this.onExtraPageChangeListener = onExtraPageChangeListener;
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {
        if (null != onExtraPageChangeListener) {
            onExtraPageChangeListener.onExtraPageScrolled(i, v, i2);
        }
    }

    @Override
    public void onPageSelected(int i) {
        fragmentList.get(currentPageIndex).onPause();
        if (fragmentList.get(i).isAdded()) {
            fragmentList.get(i).onResume();
        }
        currentPageIndex = i;

        if (null != onExtraPageChangeListener) {
            onExtraPageChangeListener.onExtraPageSelected(i);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        if (null != onExtraPageChangeListener) {
            onExtraPageChangeListener.onExtraPageScrollStateChanged(i);
        }
    }

    public static class OnExtraPageChangeListener {
        public void onExtraPageScrolled(int i, float v, int i2) {
        }

        public void onExtraPageSelected(int i) {
        }

        public void onExtraPageScrollStateChanged(int i) {
        }
    }
}
