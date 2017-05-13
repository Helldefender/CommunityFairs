package com.helldefender.enjoylife.view.base;

/**
 * Created by Helldefender on 2017/4/7.
 */

public interface BaseView{
    void showLoadingLayout();

    void showEmptyLayout();

    void showErrorLayout(int message);
}