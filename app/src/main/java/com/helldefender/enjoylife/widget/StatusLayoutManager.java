package com.helldefender.enjoylife.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.helldefender.enjoylife.listener.OnRetryListener;
import com.helldefender.enjoylife.listener.OnShowHideViewListener;

/**
 * Created by Helldefender on 2017/4/8.
 */

public class StatusLayoutManager {

    final Context context;

    final ViewStub netWorkErrorVs;

    final int netWorkErrorRetryViewId;

    final ViewStub emptyDataVs;

    final int emptyDataRetryViewId;

    final ViewStub errorVs;

    final int errorRetryViewId;

    final int loadingLayoutResId;

    final int contentLayoutResId;

    final int retryViewId;

    final int emptyDataIconImageId;

    final int emptyDataTextTipId;

    final RootFrameLayout rootFrameLayout;

    final OnShowHideViewListener onShowHideViewListener;

    final OnRetryListener onRetryListener;

    public StatusLayoutManager(Builder builder) {
        this.context = builder.context;
        this.loadingLayoutResId = builder.loadingLayoutResId;
        this.netWorkErrorVs = builder.netWorkErrorVs;
        this.netWorkErrorRetryViewId = builder.netWorkErrorRetryViewId;
        this.emptyDataVs = builder.emptyDataVs;
        this.emptyDataRetryViewId = builder.emptyDataRetryViewId;
        this.errorVs = builder.errorVs;
        this.errorRetryViewId = builder.errorRetryViewId;
        this.contentLayoutResId = builder.contentLayoutResId;
        this.onShowHideViewListener = builder.onShowHideViewListener;
        this.retryViewId = builder.retryViewId;
        this.onRetryListener = builder.onRetryListener;
        this.emptyDataIconImageId = builder.emptyDataIconImageId;
        this.emptyDataTextTipId = builder.emptyDataTextTipId;

        rootFrameLayout = new RootFrameLayout(this.context);
        rootFrameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        rootFrameLayout.setStatusLayoutManager(this);
    }


    public void showLoading() {
        rootFrameLayout.showLoading();
    }

    public void showContent() {
        rootFrameLayout.showContent();
    }

    public void showEmptyData(int iconImage, String textTip) {
        rootFrameLayout.showEmptyData(iconImage, textTip);
    }

    public void showEmptyData() {
        showEmptyData(0, "");
    }

    public void showNetWorkError() {
        rootFrameLayout.showNetWorkError();
    }

    public void showError() {
        rootFrameLayout.showError();
    }

    public View getRootLayout() {
        return rootFrameLayout;
    }


    public static final class Builder {

        private Context context;

        private int loadingLayoutResId;

        private int contentLayoutResId;

        private ViewStub netWorkErrorVs;

        private int netWorkErrorRetryViewId;

        private ViewStub emptyDataVs;

        private int emptyDataRetryViewId;

        private ViewStub errorVs;

        private int errorRetryViewId;

        private int retryViewId;

        private int emptyDataIconImageId;

        private int emptyDataTextTipId;

        private OnShowHideViewListener onShowHideViewListener;

        private OnRetryListener onRetryListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder loadingView(@LayoutRes int loadingLayoutResId) {
            this.loadingLayoutResId = loadingLayoutResId;
            return this;
        }

        public Builder netWorkErrorView(@LayoutRes int newWorkErrorId) {
            netWorkErrorVs = new ViewStub(context);
            netWorkErrorVs.setLayoutResource(newWorkErrorId);
            return this;
        }

        public Builder emptyDataView(@LayoutRes int noDataViewId) {
            emptyDataVs = new ViewStub(context);
            emptyDataVs.setLayoutResource(noDataViewId);
            return this;
        }

        public Builder errorView(@LayoutRes int errorViewId) {
            errorVs = new ViewStub(context);
            errorVs.setLayoutResource(errorViewId);
            return this;
        }

        public Builder contentView(@LayoutRes int contentLayoutResId) {
            this.contentLayoutResId = contentLayoutResId;
            return this;
        }

        public Builder netWorkErrorRetryViewId(int netWorkErrorRetryViewId) {
            this.netWorkErrorRetryViewId = netWorkErrorRetryViewId;
            return this;
        }

        public Builder emptyDataRetryViewId(int emptyDataRetryViewId) {
            this.emptyDataRetryViewId = emptyDataRetryViewId;
            return this;
        }

        public Builder errorRetryViewId(int errorRetryViewId) {
            this.errorRetryViewId = errorRetryViewId;
            return this;
        }

        public Builder retryViewId(int retryViewId) {
            this.retryViewId = retryViewId;
            return this;
        }

        public Builder emptyDataIconImageId(int emptyDataIconImageId) {
            this.emptyDataIconImageId = emptyDataIconImageId;
            return this;
        }

        public Builder emptyDataTextTipId(int emptyDataTextTipId) {
            this.emptyDataTextTipId = emptyDataTextTipId;
            return this;
        }

        public Builder onShowHideViewListener(OnShowHideViewListener onShowHideViewListener) {
            this.onShowHideViewListener = onShowHideViewListener;
            return this;
        }

        public Builder onRetryListener(OnRetryListener onRetryListener) {
            this.onRetryListener = onRetryListener;
            return this;
        }

        public StatusLayoutManager build() {
            return new StatusLayoutManager(this);
        }
    }

    public static Builder newBuilder(Context context) {
        return new Builder(context);
    }

}
