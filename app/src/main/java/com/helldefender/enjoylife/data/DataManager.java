package com.helldefender.enjoylife.data;

import com.helldefender.enjoylife.data.local.DataBaseHelper;
import com.helldefender.enjoylife.data.local.PreferencesHelper;
import com.helldefender.enjoylife.listener.OnRequestCallBackListener;

import javax.inject.Inject;

/**
 * Created by Helldefender on 2017/4/7.
 */
//perapp
public class DataManager {

    private HttpServer httpServer;

    private DataBaseHelper dataBaseHelper;

    private PreferencesHelper preferencesHelper;

    @Inject
    public DataManager(HttpServer httpServer, DataBaseHelper dataBaseHelper, PreferencesHelper preferencesHelper) {
        this.httpServer = httpServer;
        this.dataBaseHelper = dataBaseHelper;
        this.preferencesHelper = preferencesHelper;
    }

    public void getData(OnRequestCallBackListener onRequestCallBackListener) {
        httpServer.getFromService(onRequestCallBackListener);
    }

    public PreferencesHelper getPreference(OnRequestCallBackListener onRequestCallBackListener) {
        return preferencesHelper;
    }
}
