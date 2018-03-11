package com.helldefender.communityfairs.modules.main.homepage;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.app.BaseFragment;
import com.helldefender.communityfairs.app.ViewModelFactory;
import com.helldefender.communityfairs.databinding.FragmentHomepageBinding;
import com.helldefender.communityfairs.listener.OnLoadMoreListener;
import com.helldefender.communityfairs.listener.OnRefreshListener;
import com.helldefender.communityfairs.modules.community.publish.PublishActivity;
import com.helldefender.communityfairs.widget.recyclerview.LoadMoreFooterView;
import com.helldefender.communityfairs.widget.recyclerview.RefreshRecyclerView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Helldefender on 2017/2/5.
 */

public class HomePageFragment extends BaseFragment<FragmentHomepageBinding, HomePageViewModel> implements OnLoadMoreListener, OnRefreshListener {

    RefreshRecyclerView recyclerView;

    private LoadMoreFooterView loadMoreFooterView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected int getVariableId() {
        return BR.viewModel;
    }

    @Override
    protected HomePageViewModel getViewModel() {
        return ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(HomePageViewModel.class);
    }

    @Override
    protected void initViews(FragmentHomepageBinding binding, Bundle savedInstanceState) {
        recyclerView = binding.rvHomepage;
        loadMoreFooterView = (LoadMoreFooterView) recyclerView.getLoadMoreFooterView();

        recyclerView.setOnRefreshListener(this);
        recyclerView.setOnLoadMoreListener(this);

//        recyclerView.post(new Runnable() {
//            @Override
//            public void run() {
//                recyclerView.setRefreshing(true);
//            }
//        });

        binding.fabRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHoldingActivity().startActivity(PublishActivity.class);
            }
        });
    }

    @Override
    public void onRefresh() {
        loadBanner();
        loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
        refresh();
    }

    @Override
    public void onLoadMore() {
        if (loadMoreFooterView.canLoadMore() && recyclerView.getAdapter() != null && recyclerView.getAdapter().getItemCount() > 0) {
            loadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
            loadMore();
        }
    }

    private void loadBanner() {
//        NetworkAPI.requestBanners(new NetworkAPI.Callback<List<Image>>() {
//            @Override
//            public void onSuccess(List<Image> images) {
//                if (!ListUtils.isEmpty(images)) {
//                    bannerView.setList(images);
//                }
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                e.printStackTrace();
//            }
//        });
    }

    private void refresh() {
        Observable.interval(3, 3, TimeUnit.SECONDS)
                .take(1) //设置总共发送的次数
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return 60 - aLong;
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        recyclerView.setRefreshing(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        mPage = 1;
//        NetworkAPI.requestImages(mPage, new NetworkAPI.Callback<List<Image>>() {
//            @Override
//            public void onSuccess(List<Image> images) {
//                iRecyclerView.setRefreshing(false);
//                if (ListUtils.isEmpty(images)) {
//                    mAdapter.clear();
//                } else {
//                    mPage = 2;
//                    mAdapter.setList(images);
//                }
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                e.printStackTrace();
//                iRecyclerView.setRefreshing(false);
//                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void loadMore() {
        Observable.interval(3, 3, TimeUnit.SECONDS)
                .take(1) //设置总共发送的次数
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return 60 - aLong;
                    }
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        loadMoreFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        NetworkAPI.requestImages(mPage, new NetworkAPI.Callback<List<Image>>() {
//            @Override
//            public void onSuccess(final List<Image> images) {
//                if (ListUtils.isEmpty(images)) {
//                    loadMoreFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
//                } else {
//
////                    mPage++;
////                    loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
////                    mAdapter.append(images);
//                    /**
//                     * FIXME here we post delay to see more animation, you don't need to do this.
//                     */
//                    loadMoreFooterView.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            mPage++;
//                            loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
//                            mAdapter.append(images);
//                        }
//                    }, 2000);
//                }
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                e.printStackTrace();
//                loadMoreFooterView.setStatus(LoadMoreFooterView.Status.ERROR);
//                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
