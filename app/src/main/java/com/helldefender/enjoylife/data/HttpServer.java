package com.helldefender.enjoylife.data;

import com.helldefender.enjoylife.listener.OnRequestCallBackListener;

import rx.Subscription;

/**
 * Created by Helldefender on 2017/4/7.
 */

public interface HttpServer<T> {
    Subscription getFromService(OnRequestCallBackListener onRequestCallBackListener);

    void getFromCache(OnRequestCallBackListener onRequestCallBackListener);
}
