package com.helldefender.communityfairs.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Helldefender on 2017/2/5.
 */

public class App extends Application {

    private static final String TAG = "Logger";

    private static App app;

    public static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
        applicationContext = this;
    }

    public static App getInstance() {
        return app;
    }

    public static Context getContext() {
        return applicationContext;
    }
}
