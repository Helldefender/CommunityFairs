package com.helldefender.enjoylife.presenter.impl;

import com.helldefender.enjoylife.data.impl.HttpServiceImpl;
import com.helldefender.enjoylife.presenter.base.BasePresenterImpl;
import com.helldefender.enjoylife.utils.imageloader.ImageLoaderUtil;
import com.helldefender.enjoylife.view.DisCoveryAppBarView;

import javax.inject.Inject;

/**
 * Created by Helldefender on 2017/5/21.
 */

public class DiscoveryAppBarImpl extends BasePresenterImpl<DisCoveryAppBarView, String> {

    private HttpServiceImpl httpService;

    private ImageLoaderUtil imageLoaderUtil;

    @Inject
    public DiscoveryAppBarImpl() {

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
