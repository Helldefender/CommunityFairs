package com.helldefender.enjoylife.presenter.impl;

import com.helldefender.enjoylife.presenter.base.BasePresenterImpl;
import com.helldefender.enjoylife.view.DetailContentView;

import javax.inject.Inject;

/**
 * Created by Helldefender on 2017/5/7.
 */

public class DetailContentPresenterImpl extends BasePresenterImpl<DetailContentView, String> {

    @Inject
    public DetailContentPresenterImpl() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getData();
    }

    @Override
    public void onSuccess(String data) {
        super.onSuccess(data);
        //mView.initViewPager(data);
    }

    public void getData() {
        //dataManager.getData(this);
    }
}
