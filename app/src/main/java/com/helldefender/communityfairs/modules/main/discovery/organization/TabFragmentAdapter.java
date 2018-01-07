package com.helldefender.communityfairs.modules.main.discovery.organization;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Helldefender on 2018/1/7 for CommunityFairs.
 * Function:
 * Description:
 */

public class TabFragmentAdapter extends FragmentStatePagerAdapter {

    public Context mContext;

    private ArrayList<Fragment> fragments;

    private String[] titles;

    public TabFragmentAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        mContext = context;
        fragments = new ArrayList<>();

        initFragments();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    private void initFragments() {
        titles = new String[]{
                "主页", "活动"
        };


        for (int i = 0; i < titles.length; i++) {
            Fragment fragment = OrganizationTabFragment.newInstance();
            fragments.add(fragment);
        }
    }

    public ArrayList<Fragment> getFragments() {
        return fragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
