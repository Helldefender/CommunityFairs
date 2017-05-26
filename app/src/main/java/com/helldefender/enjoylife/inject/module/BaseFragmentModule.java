package com.helldefender.enjoylife.inject.module;

import com.helldefender.enjoylife.inject.qualifier.FragmentType;
import com.helldefender.enjoylife.ui.fragment.BlankFragment;
import com.helldefender.enjoylife.ui.fragment.DiscoveryAppBarFragment;
import com.helldefender.enjoylife.ui.fragment.base.BaseFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Helldefender on 2017/5/21.
 */
@Module
public class BaseFragmentModule {

    @Provides
    //@PerApp
    @FragmentType("BlankFragment")
    BaseFragment getBlankFragment() {
        return new BlankFragment();
    }

    @Provides
    //@PerApp
    @FragmentType("DiscoveryAppBarFragment")
    BaseFragment getDiscoveryAppBarFragment() {
        return new DiscoveryAppBarFragment();
    }
}
