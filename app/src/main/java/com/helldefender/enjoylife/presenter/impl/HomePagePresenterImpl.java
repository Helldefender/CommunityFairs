package com.helldefender.enjoylife.presenter.impl;

import com.helldefender.enjoylife.presenter.NewsLatestPresenter;
import com.helldefender.enjoylife.presenter.base.BasePresenterImpl;
import com.helldefender.enjoylife.utils.imageloader.ImageLoaderUtil;
import com.helldefender.enjoylife.view.HomePageView;

import javax.inject.Inject;

/**
 * Created by Helldefender on 2017/4/7.
 */

public class HomePagePresenterImpl extends BasePresenterImpl<HomePageView, String> implements NewsLatestPresenter {

    //private HttpServiceImpl httpService;

    private ImageLoaderUtil imageLoaderUtil;

//    @Inject
//    public HomePagePresenterImpl(HttpServiceImpl httpService) {
//        //this.dataManager = new DataManager(new HttpServiceImpl());
//        //this.dataManager = dataManager;
//        this.httpService = httpService;
//    }

    @Inject
    public HomePagePresenterImpl() {
        //this.dataManager = new DataManager(new HttpServiceImpl());
        //this.dataManager = dataManager;
    }

    public HomePagePresenterImpl test() {
        //根据需求加载相应的数据（参数为对应的实现类）
        //this.dataManager = new DataManager(new HttpServiceImpl());
        return this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getHomePageDate();
    }

    @Override
    public void onSuccess(String data) {
        super.onSuccess(data);

    }

    @Override
    public void getLatestData() {
        //getHomePageDate();
        //实现下拉刷新
    }

    public void getHomePageDate() {
        //subscription = httpService.getFromService(this);
    }
}
