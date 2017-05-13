package com.helldefender.enjoylife.inject.component;

import android.app.Activity;
import android.content.Context;

import com.helldefender.enjoylife.inject.module.ActivityModule;
import com.helldefender.enjoylife.inject.qualifier.ActivityContext;
import com.helldefender.enjoylife.inject.qualifier.ApplicationContext;
import com.helldefender.enjoylife.inject.scope.PerActivity;
import com.helldefender.enjoylife.ui.activity.DetailContentActivity;

import dagger.Component;

/**
 * Created by Helldefender on 2017/4/7.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    @ActivityContext
    Context getActivityContext();

    @ApplicationContext
    Context getApplicationContext();

    Activity getActivity();

    void inject(DetailContentActivity detailContentActivity);

}
