package com.helldefender.communityfairs.bindingadapter;

/**
 * Created by Helldefender on 2017/4/12.
 */

public interface MultiViewType<T> {
    int getViewTypeSpanCount(int viewType);

    int getItemViewType(int position);

    int getLayoutRes(int viewType);

    void getLayoutRes(ItemView itemView, int position, T viewModel);
}
