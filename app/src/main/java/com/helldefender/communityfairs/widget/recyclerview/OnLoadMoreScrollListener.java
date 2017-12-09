package com.helldefender.communityfairs.widget.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Helldefender on 2017/4/19.
 */
public abstract class OnLoadMoreScrollListener extends RecyclerView.OnScrollListener {

    //通过重写onScrollListener来监听RecyclerView是否滑动到底部
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        //滚动时一直回调，直到停止滚动时才停止回调。点击时也回调一次。
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        //正在滚动时回调，
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int visibleItemCount = layoutManager.getChildCount();

        boolean triggerCondition = visibleItemCount > 0
                && newState == RecyclerView.SCROLL_STATE_IDLE
                && canTriggerLoadMore(recyclerView);

        if (triggerCondition) {
            onLoadMore(recyclerView);
        }
    }

    public boolean canTriggerLoadMore(RecyclerView recyclerView) {
        View lastChild = recyclerView.getChildAt(recyclerView.getChildCount() - 1);
        int position = recyclerView.getChildLayoutPosition(lastChild);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int totalItemCount = layoutManager.getItemCount();
        return totalItemCount - 1 == position;
    }

    public abstract void onLoadMore(RecyclerView recyclerView);
}
