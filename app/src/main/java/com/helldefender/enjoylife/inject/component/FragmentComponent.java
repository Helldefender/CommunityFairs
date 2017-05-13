package com.helldefender.enjoylife.inject.component;

import android.app.Activity;
import android.content.Context;

import com.helldefender.enjoylife.inject.module.FragmentModule;
import com.helldefender.enjoylife.inject.qualifier.ActivityContext;
import com.helldefender.enjoylife.inject.qualifier.ApplicationContext;
import com.helldefender.enjoylife.inject.scope.PerFragment;
import com.helldefender.enjoylife.ui.fragment.DiscoveryFragment;
import com.helldefender.enjoylife.ui.fragment.HomePageFragment;
import com.helldefender.enjoylife.ui.fragment.MessageFragment;

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

    void inject(MessageFragment messageFragment);
}

