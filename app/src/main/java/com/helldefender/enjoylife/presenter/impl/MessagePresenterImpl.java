package com.helldefender.enjoylife.presenter.impl;

import com.helldefender.enjoylife.presenter.base.BasePresenterImpl;
import com.helldefender.enjoylife.view.MessageView;

import javax.inject.Inject;

/**
 * Created by Helldefender on 2017/5/2.
 */

public class MessagePresenterImpl extends BasePresenterImpl<MessageView,String> {

    @Inject
    public MessagePresenterImpl() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //
    }

    @Override
    public void onSuccess(String data) {
        super.onSuccess(data);
    }
}
