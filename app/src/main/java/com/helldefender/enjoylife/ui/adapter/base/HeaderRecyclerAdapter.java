package com.helldefender.enjoylife.ui.adapter.base;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Helldefender on 2017/2/5.
 */

public class  HeaderRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BASE_HEADERVIEW_ITEM = 10000;

    private static final int BASE_FOOTVIEW_ITEM = 10000;

    private SparseArrayCompat<View> headerViews = new SparseArrayCompat<View>();

    private SparseArrayCompat<View> footViews = new SparseArrayCompat<View>();

    private RecyclerView.Adapter mInnerAdapter;

    public HeaderRecyclerAdapter(RecyclerView.Adapter mInnerAdapter) {
        this.mInnerAdapter = mInnerAdapter;
    }

    private boolean isHeaderViewPosition(int position) {
        return position < getHeadersCount();
    }

    private boolean isFootViewPosition(int position) {
        return position >= (mInnerAdapter.getItemCount() + getHeadersCount());
    }

    private int getHeadersCount() {
        return headerViews.size();
    }

    private int getFootCount() {
        return footViews.size();
    }

    public void addHeaderView(View view) {
        headerViews.put(headerViews.size() + BASE_HEADERVIEW_ITEM, view);
    }

    public void addFootView(View view) {
        footViews.put(footViews.size() + BASE_FOOTVIEW_ITEM, view);
    }

    private int getRealItemCount() {
        return mInnerAdapter.getItemCount();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerViews.get(viewType) != null) {
            BaseViewHolder viewHolder = BaseViewHolder.createViewHolder(parent.getContext(), headerViews.get(viewType));
            return viewHolder;
        } else if (footViews.get(viewType) != null) {
            BaseViewHolder viewHolder = BaseViewHolder.createViewHolder(parent.getContext(), footViews.get(viewType));
            return viewHolder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeaderViewPosition(position))
            return;
        if (isFootViewPosition(position))
            return;
        mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getRealItemCount() + getFootCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPosition(position)) {
            return headerViews.keyAt(position);
        } else if (isFootViewPosition(position)) {
            return footViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }
        return mInnerAdapter.getItemViewType(position - getHeadersCount());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int viewType = getItemViewType(position);
                    if (headerViews.get(viewType) != null) {
                        return ((GridLayoutManager) layoutManager).getSpanCount();
                    }
                    if (spanSizeLookup != null) {
                        return spanSizeLookup.getSpanSize(position);
                    }
                    return 1;

                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }
}


