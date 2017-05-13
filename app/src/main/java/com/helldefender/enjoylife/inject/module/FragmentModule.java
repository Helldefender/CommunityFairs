package com.helldefender.enjoylife.inject.module;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.helldefender.enjoylife.inject.qualifier.ActivityContext;
import com.helldefender.enjoylife.inject.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Helldefender on 2017/4/7.
 */
@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @PerFragment
    @ActivityContext
    public Context provideActivityContext() {
        return fragment.getActivity();
    }

    @Provides
    @PerFragment
    public Activity provideActivity() {
        return fragment.getActivity();
    }

    @Provides
    @PerFragment
    public Fragment provideFragment() {
        return fragment;
    }
}
