package com.helldefender.communityfairs.bindingadapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.helldefender.communityfairs.app.BaseAdapter;
import com.helldefender.communityfairs.bindingadapter.command.ReplyCommand;
import com.helldefender.communityfairs.widget.recyclerview.RefreshRecyclerView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Helldefender on 2017/11/30 for CommunityFairs.
 * Function:
 * Description:
 */

public class RecyclerViewBindingAdapter {

    @BindingAdapter(value = {"itemViewWrapper", "viewModel", "adapter", "refreshEnable", "loadMoreEnable"}, requireAll = false)
    public static <T> void setAdapter(RefreshRecyclerView recyclerView, ItemViewWrapper<T> itemViewWrapper, List<T> viewModel, BaseAdapter adapter, boolean refreshEnable, boolean loadMoreEnable) {
        if (itemViewWrapper == null) {
            throw new IllegalArgumentException("向你抛出了一个异常-->ItemViewWrapper为空");
        }

        BaseAdapter mAdapter = (BaseAdapter) recyclerView.getAdapter();
        if (adapter == null) {
            if (mAdapter == null) {
                adapter = BaseAdapterFactory.DEFAULT.create(recyclerView, itemViewWrapper, viewModel, refreshEnable, loadMoreEnable);
            } else {
                adapter = mAdapter;
            }
        }

        if (mAdapter != adapter) {
            recyclerView.setAdapter(adapter);
        }
    }

    @BindingAdapter("layoutManager")
    public static void setLayoutManger(RecyclerView recyclerView, LayoutManager.LayoutManagerFactory layoutManagerFactory) {
        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }

    @BindingAdapter("onLoadMoreCommand")
    public static void onLoadMoreCommand(RecyclerView recyclerView, ReplyCommand<Integer> replyCommand) {

        RecyclerView.OnScrollListener onScrollListener = new OnScrollListener(replyCommand);
        recyclerView.addOnScrollListener(onScrollListener);
    }

    public static class OnScrollListener extends RecyclerView.OnScrollListener {
        private PublishSubject<Integer> methodInvoke = PublishSubject.create();

        private ReplyCommand<Integer> onLoadMoreCommand;

        public OnScrollListener(final ReplyCommand<Integer> onLoadMoreCommand) {
            this.onLoadMoreCommand = onLoadMoreCommand;
            methodInvoke.throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Integer>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Integer integer) {
                            onLoadMoreCommand.execute(integer);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int visibleItemCount = linearLayoutManager.getChildCount();
            int totalItemCount = linearLayoutManager.getItemCount();
            int pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();
            if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                if (onLoadMoreCommand != null) {
                    methodInvoke.onNext(recyclerView.getAdapter().getItemCount());
                }
            }
        }
    }

    public interface BaseAdapterFactory {
        BaseAdapterFactory DEFAULT = new BaseAdapterFactory() {
            @Override
            public <T> BaseAdapter<T> create(RefreshRecyclerView recyclerView, ItemViewWrapper<T> itemViewWrapper, List<T> viewModel, boolean refreshEnable, boolean loadMoreEnable) {
                if (refreshEnable && loadMoreEnable) {
                    return new BaseAdapter<>(itemViewWrapper, viewModel, recyclerView.getRefreshHeaderLayout(), recyclerView.getLoadMoreFooterLayout());
                }
                return new BaseAdapter<>(itemViewWrapper, viewModel);
            }
        };

        <T> BaseAdapter<T> create(RefreshRecyclerView recyclerView, ItemViewWrapper<T> itemViewWrapper, List<T> viewModel, boolean refreshEnable, boolean loadMoreEnable);
    }

}
