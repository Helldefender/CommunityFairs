package com.helldefender.communityfairs.modules.main.discovery;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Helldefender on 2017/6/4.
 */

public class FragmentPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;

    private String[] mTabTitles;

    public FragmentPageAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] tabTitles) {
        super(fm);
        mFragmentList = fragmentList;
        mTabTitles = tabTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mTabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }
}
