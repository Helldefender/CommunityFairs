package com.helldefender.enjoylife.app;

import android.app.Application;
import android.content.Context;

import com.helldefender.enjoylife.inject.component.ApplicationComponent;
import com.helldefender.enjoylife.inject.component.BaseFragmentComponent;
import com.helldefender.enjoylife.inject.component.DaggerApplicationComponent;
import com.helldefender.enjoylife.inject.component.DaggerBaseFragmentComponent;
import com.helldefender.enjoylife.inject.module.ApplicationModule;
import com.helldefender.enjoylife.inject.module.BaseFragmentModule;
import com.jude.utils.JUtils;

/**
 * Created by Helldefender on 2017/2/5.
 */

public class MyApplication extends Application {

    private static final String TAG = "Logger";

    private static MyApplication myApplication;

    private ApplicationComponent applicationComponent;

    private BaseFragmentComponent baseFragmentComponent;

    public static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        JUtils.initialize(this);

        myApplication = this;
        applicationContext = this;

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        baseFragmentComponent = DaggerBaseFragmentComponent.builder().
                baseFragmentModule(new BaseFragmentModule())
                .build();
    }

    public static MyApplication getInstance() {
        return myApplication;
    }

    public static Context getContext() {
        return applicationContext;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public BaseFragmentComponent getBaseFragmentComponent() {
        return baseFragmentComponent;
    }

}
