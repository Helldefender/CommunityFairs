package com.helldefender.enjoylife.presenter.base;

import android.support.annotation.NonNull;

import com.helldefender.enjoylife.listener.OnRequestCallBackListener;
import com.helldefender.enjoylife.utils.SubscriptionUtil;
import com.helldefender.enjoylife.view.base.BaseView;

import rx.Subscription;

/**
 * Created by Helldefender on 2017/4/7.
 */

public class BasePresenterImpl<T extends BaseView, E> implements BasePresenter, OnRequestCallBackListener<E> {

    protected T mView;

    protected Subscription subscription;

    @Override
    public void onCreate() {
        onLoading();
    }

    @Override
    public void attachView(@NonNull BaseView view) {
        mView = (T) view;
    }

    @Override
    public void onDestroy() {
        SubscriptionUtil.cancelSubscription(subscription);
    }

    @Override
    public void onLoading() {
        mView.showLoadingLayout();
    }

    @Override
    public void onSuccess(E data) {
    }

    @Override
    public void onEmpty() {
        mView.showEmptyLayout();
    }

    @Override
    public void onError(int message) {
        mView.showErrorLayout(message);
    }
}
