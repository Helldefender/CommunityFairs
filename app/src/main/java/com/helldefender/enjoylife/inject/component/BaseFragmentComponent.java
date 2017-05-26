package com.helldefender.enjoylife.inject.component;

import com.helldefender.enjoylife.inject.module.BaseFragmentModule;
import com.helldefender.enjoylife.inject.qualifier.FragmentType;
import com.helldefender.enjoylife.ui.fragment.base.BaseFragment;

import dagger.Component;

/**
 * Created by Helldefender on 2017/5/23.
 */

@Component(modules = BaseFragmentModule.class)
public interface BaseFragmentComponent {
    @FragmentType("BlankFragment")
    BaseFragment getBlankFragment();

    @FragmentType("DiscoveryAppBarFragment")
    BaseFragment getDiscvoeryAppBarFragment();
}
