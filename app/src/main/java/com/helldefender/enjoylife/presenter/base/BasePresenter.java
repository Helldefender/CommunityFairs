package com.helldefender.enjoylife.presenter.base;

import android.support.annotation.NonNull;

import com.helldefender.enjoylife.view.base.BaseView;

/**
 * Created by Helldefender on 2017/4/7.
 */

public interface BasePresenter {
    void onCreate();

    void attachView(@NonNull BaseView view);

    void onDestroy();
}
