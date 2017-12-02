package com.helldefender.enjoylife.inject.module;

import com.helldefender.enjoylife.inject.qualifier.FragmentType;
import com.helldefender.enjoylife.modules.main.discovery.BlankFragment;
import com.helldefender.enjoylife.modules.main.discovery.DiscoveryAppBarFragment;
import com.helldefender.enjoylife.app.BaseFragment;

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
