package com.helldefender.enjoylife.inject.component;

import android.app.Activity;
import android.content.Context;

import com.helldefender.enjoylife.inject.module.FragmentModule;
import com.helldefender.enjoylife.inject.qualifier.ActivityContext;
import com.helldefender.enjoylife.inject.qualifier.ApplicationContext;
import com.helldefender.enjoylife.inject.scope.PerFragment;
import com.helldefender.enjoylife.modules.main.discovery.DiscoveryAppBarFragment;
import com.helldefender.enjoylife.modules.main.discovery.DiscoveryFragment;
import com.helldefender.enjoylife.modules.main.homepage.HomePageFragment;
import com.helldefender.enjoylife.modules.main.messsage.MessageFragment;

import dagger.Component;

/**
 * Created by Helldefender on 2017/4/7.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    @ActivityContext
    Context getFragmentContext();

    @ApplicationContext
    Context getApplicationContext();

    Activity getActivity();

    void inject(HomePageFragment homePageFragment);

    void inject(DiscoveryFragment discoveryFragment);

    void inject(DiscoveryAppBarFragment discoveryAppBarFragment);

    void inject(MessageFragment messageFragment);

}

