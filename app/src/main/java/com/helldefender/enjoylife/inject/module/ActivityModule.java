package com.helldefender.enjoylife.inject.module;

import android.app.Activity;
import android.content.Context;

import com.helldefender.enjoylife.inject.qualifier.ActivityContext;
import com.helldefender.enjoylife.inject.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Helldefender on 2017/4/7.
 */
@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    @ActivityContext
    public Context provideActivityContext() {
        return activity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity() {
        return activity;
    }
}
