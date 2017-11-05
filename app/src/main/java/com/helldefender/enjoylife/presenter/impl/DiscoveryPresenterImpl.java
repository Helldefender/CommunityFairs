package com.helldefender.enjoylife.presenter.impl;

import com.helldefender.enjoylife.presenter.base.BasePresenterImpl;
import com.helldefender.enjoylife.utils.imageloader.ImageLoaderUtil;
import com.helldefender.enjoylife.view.DiscoveryView;

import javax.inject.Inject;

/**
 * Created by Helldefender on 2017/4/16.
 */

public class DiscoveryPresenterImpl extends BasePresenterImpl<DiscoveryView,String> {
    //private DataManager dataManager;

    //private HttpServiceImpl httpService;

    private ImageLoaderUtil imageLoaderUtil;

//    @Inject
//    public DiscoveryPresenterImpl(HttpServiceImpl httpService) {
//        //this.dataManager = new DataManager(new HttpServiceImpl());
//        //this.dataManager = dataManager;
//        this.httpService = httpService;
//    }

    @Inject
    public DiscoveryPresenterImpl() {
    }

//    @Inject
//    public HomePagePresenterImpl(DataManager dataManager, ImageLoaderUtil imageLoaderUtil) {
//        //this.dataManager = new DataManager(new HttpServiceImpl());
//        this.dataManager = dataManager;
//    }

    public DiscoveryPresenterImpl test() {
        //根据需求加载相应的数据（参数为对应的实现类）
        //this.dataManager = new DataManager(new HttpServiceImpl());
        return this;
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
