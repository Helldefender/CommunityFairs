package com.helldefender.communityfairs.app;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.bindingadapter.ItemViewWrapper;
import com.helldefender.communityfairs.bindingadapter.MultiViewType;
import com.helldefender.communityfairs.widget.recyclerview.RefreshHeaderLayout;

import java.util.List;

/**
 * Created by Helldefender on 2017/2/25.
 */

public class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int REFRESH_HEADER_VIEW = Integer.MIN_VALUE;

    private static final int HEADER_VIEWS = 10000;

    private static final int FOOTER_VIEWS = 20000;

    private static final int LOAD_MORE_FOOTER_VIEW = Integer.MAX_VALUE;

    private SparseArrayCompat<View> headerViews = new SparseArrayCompat<View>();

    private SparseArrayCompat<View> footerViews = new SparseArrayCompat<View>();

    private RefreshHeaderLayout refreshHeaderLayout;

    private FrameLayout loadMoreFrameLayout;

    protected Context context;

    private LayoutInflater mInflater;

    private boolean refreshEnable;

    private boolean loadMoreEnable;

    private List<T> viewModel;

    private ItemViewWrapper<T> itemViewWrapper;

    private RecyclerView.AdapterDataObserver adapterDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            BaseAdapter.this.notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            BaseAdapter.this.notifyItemRangeChanged(positionStart + getRefreshHeaderViewCount() + getHeaderViewsCount(), itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            BaseAdapter.this.notifyItemRangeChanged(positionStart + getRefreshHeaderViewCount() + getHeaderViewsCount(), itemCount, payload);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            BaseAdapter.this.notifyItemRangeInserted(positionStart + getRefreshHeaderViewCount() + getHeaderViewsCount(), itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            BaseAdapter.this.notifyItemRangeRemoved(positionStart + getRefreshHeaderViewCount() + getHeaderViewsCount(), itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            BaseAdapter.this.notifyDataSetChanged();
        }
    };

    public BaseAdapter(ItemViewWrapper<T> itemViewWrapper, List<T> viewModel) {
        this.itemViewWrapper = itemViewWrapper;
        this.viewModel = viewModel;

        registerAdapterDataObserver(adapterDataObserver);
    }

    public BaseAdapter(ItemViewWrapper<T> itemViewWrapper, List<T> viewModel, RefreshHeaderLayout refreshHeaderLayout, FrameLayout loadMoreFrameLayout) {
        this.itemViewWrapper = itemViewWrapper;
        this.viewModel = viewModel;
        this.refreshHeaderLayout = refreshHeaderLayout;
        this.loadMoreFrameLayout = loadMoreFrameLayout;

        refreshEnable = true;
        loadMoreEnable = true;

        registerAdapterDataObserver(adapterDataObserver);
    }
//
//    public BaseAdapter(Context context, List<T> data, RefreshHeaderLayout refreshHeaderLayout, FrameLayout loadMoreFrameLayout) {
//        this.context = context;
//        mInflater = LayoutInflater.from(context);
//        this.data = data;
//        refreshEnable = true;
//        loadMoreEnable = true;
//        this.refreshHeaderLayout = refreshHeaderLayout;
//        this.loadMoreFrameLayout = loadMoreFrameLayout;
//    }
//
//    public BaseAdapter(Context context, MultiViewType<T> multiViewType, List<T> data, RefreshHeaderLayout refreshHeaderLayout, FrameLayout loadMoreFrameLayout) {
//        this.context = context;
//        mInflater = LayoutInflater.from(context);
//        mMultiViewType = multiViewType;
//        this.data = data;
//        refreshEnable = true;
//        loadMoreEnable = true;
//        this.refreshHeaderLayout = refreshHeaderLayout;
//        this.loadMoreFrameLayout = loadMoreFrameLayout;
//    }

    @Override
    public BaseViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }

        if (viewType == REFRESH_HEADER_VIEW) {
            return new BaseViewHolder(context, refreshHeaderLayout);
        } else if (headerViews.get(viewType) != null) {
            return BaseViewHolder.createViewHolder(context, headerViews.get(viewType));
        } else if (footerViews.get(viewType) != null) {
            return BaseViewHolder.createViewHolder(context, footerViews.get(viewType));
        } else if (viewType == LOAD_MORE_FOOTER_VIEW) {
            return new BaseViewHolder(context, loadMoreFrameLayout);
        } else {
            ViewDataBinding viewDataBinding = DataBindingUtil.inflate(mInflater, itemViewWrapper.layoutRes(), parent, false);
            return BaseViewHolder.createViewHolder(viewDataBinding);
        }
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {
//
//        if (isFullSpanType(viewType)) {
//            return;
//        }

        if ((position >= 0 && position < getRefreshHeaderViewCount() + getHeaderViewsCount()) || position >= getRefreshHeaderViewCount() + getHeaderViewsCount() + getRealItemCount())
            return;

        int Position = position - getHeaderViewsCount() - getRefreshHeaderViewCount();

        T viewModel = this.viewModel.get(Position);
        onBindBinding(holder.viewDataBinding, this.itemViewWrapper.bindingVariable(), viewModel);
    }

    private void onBindBinding(ViewDataBinding viewDataBinding, int bindingVariable, T viewModel) {
        if (bindingVariable != 0) {
            boolean result = viewDataBinding.setVariable(bindingVariable, viewModel);
            if (!result) {
                throw new IllegalStateException("向你抛出一个异常-->setVariable失败");
            }
            viewDataBinding.executePendingBindings();
        }
    }

    public void setViewModel(List<T> viewModel) {
        this.viewModel = viewModel;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return getRefreshHeaderViewCount() + getHeaderViewsCount() + getRealItemCount() + getFooterViewsCount() + getLoadMoreFooterViewCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && refreshEnable) {
            return REFRESH_HEADER_VIEW;
        } else if (isHeaderViewPosition(position)) {
            return headerViews.keyAt(position - getRefreshHeaderViewCount());
        } else if (isFootViewPosition(position)) {
            return footerViews.keyAt(position - getHeaderViewsCount() - getRealItemCount() - getRefreshHeaderViewCount());
        } else if (loadMoreEnable && position == getHeaderViewsCount() + getRealItemCount() + getFooterViewsCount() + getRefreshHeaderViewCount()) {
            return LOAD_MORE_FOOTER_VIEW;
        } else {
            itemViewWrapper.getLayoutRes(position - getRefreshHeaderViewCount() - getHeaderViewsCount(), viewModel.get(position - getHeaderViewsCount() - getRefreshHeaderViewCount()));
            return itemViewWrapper.layoutRes();
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        this.recyclerView = recyclerView;
//
//        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//        if (layoutManager instanceof GridLayoutManager) {
//            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
//            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
//            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {
//                    int viewType = getItemViewType(position);
//                    if (isFullSpanType(viewType)) {
//                        return gridLayoutManager.getSpanCount();
//                    }
////                    else {
////                        if (spanSizeLookup != null) {
////                            return spanSizeLookup.getSpanSize(position);
////                        }
////                    }
//                    if (mMultiViewType != null) {
//                        int multiViewType = mMultiViewType.getItemViewType(position - getRefreshHeaderViewCount() - getHeaderViewsCount());
//                        return mMultiViewType.getViewTypeSpanCount(multiViewType);
//                    }
//                    return 1;
//                }
//            });
//        }
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int position = holder.getAdapterPosition();
        int viewType = getItemViewType(position);
        if (isFullSpanType(viewType)) {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                //StaggeredGridLayoutManager.LayoutParams stagGridLayoutParams = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
                //stagGridLayoutParams.setFullSpan(true);
                ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
            }
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        unregisterAdapterDataObserver(adapterDataObserver);
    }

    private boolean isFullSpanType(int viewType) {
        return viewType == REFRESH_HEADER_VIEW || viewType == LOAD_MORE_FOOTER_VIEW || headerViews.get(viewType) != null || footerViews.get(viewType) != null;
    }

    private void setRefreshHeaderLayout() {
        if (refreshHeaderLayout == null && refreshEnable) {
            refreshHeaderLayout = new RefreshHeaderLayout(context);
            refreshHeaderLayout.setLayoutParams(new RefreshHeaderLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
        }
    }

    private void setLoadMoreFrameLayout() {
        if (loadMoreFrameLayout == null && loadMoreEnable) {
            loadMoreFrameLayout = new FrameLayout(context);
            loadMoreFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    private boolean isHeaderViewPosition(int position) {
        if (refreshEnable) {
            return position > 0 && position <= getHeaderViewsCount();
        }
        return position < getHeaderViewsCount();
    }

    private boolean isFootViewPosition(int position) {
        if (loadMoreEnable) {
            return position >= (getRealItemCount() + getHeaderViewsCount() + getRefreshHeaderViewCount()) && position < (getRefreshHeaderViewCount() + getHeaderViewsCount() + getRealItemCount() + getFooterViewsCount());
        }
        return position >= getHeaderViewsCount() + getRealItemCount() + getRefreshHeaderViewCount();
    }

    public int getRefreshHeaderViewCount() {
        return refreshEnable ? 1 : 0;
    }

    public int getHeaderViewsCount() {
        return headerViews.size();
    }

    protected int getRealItemCount() {
        return viewModel == null ? 0 : viewModel.size();
    }

    protected int getFooterViewsCount() {
        return footerViews.size();
    }

    public int getLoadMoreFooterViewCount() {
        return loadMoreEnable ? 1 : 0;
    }

    public void addHeaderView(View view) {
        headerViews.put(headerViews.size() + HEADER_VIEWS, view);
    }

    public void addFootView(View view) {
        footerViews.put(footerViews.size() + FOOTER_VIEWS, view);
    }

    public void setRefreshEnable(boolean refreshEnable) {
        this.refreshEnable = refreshEnable;
    }

    public void setLoadMoreEnable(boolean loadMoreEnable) {
        this.loadMoreEnable = loadMoreEnable;
    }
}
