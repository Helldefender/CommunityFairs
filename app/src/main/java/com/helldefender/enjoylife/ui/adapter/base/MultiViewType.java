package com.helldefender.enjoylife.ui.adapter.base;

/**
 * Created by Helldefender on 2017/4/12.
 */

public interface MultiViewType<T> {
    int getViewTypeSpanCount(int viewType);

    int getItemViewType(int position);

    int getLayoutId(int viewType);
}
