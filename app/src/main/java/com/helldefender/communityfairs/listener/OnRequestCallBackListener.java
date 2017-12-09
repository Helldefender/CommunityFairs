package com.helldefender.communityfairs.listener;

/**
 * Created by Helldefender on 2017/4/7.
 */

public interface OnRequestCallBackListener<T> {
    void onLoading();

    void onSuccess(T data);

    void onEmpty();

    void onError(int message);
}
