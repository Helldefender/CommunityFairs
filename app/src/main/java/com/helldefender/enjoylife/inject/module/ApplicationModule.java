package com.helldefender.enjoylife.inject.module;

import android.content.Context;

import com.helldefender.enjoylife.app.App;
import com.helldefender.enjoylife.inject.qualifier.ApplicationContext;
import com.helldefender.enjoylife.inject.scope.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Helldefender on 2017/4/7.
 */
@Module
public class ApplicationModule {

    private App application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    @Provides
    @PerApp
    @ApplicationContext
    public Context provideApplicationContext() {
        return application.getApplicationContext();
    }

}
