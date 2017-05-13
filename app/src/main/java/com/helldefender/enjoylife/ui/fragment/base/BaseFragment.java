package com.helldefender.enjoylife.ui.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.StatusLayoutManager;
import com.helldefender.enjoylife.app.MyApplication;
import com.helldefender.enjoylife.inject.component.DaggerFragmentComponent;
import com.helldefender.enjoylife.inject.component.FragmentComponent;
import com.helldefender.enjoylife.inject.module.FragmentModule;
import com.helldefender.enjoylife.listener.OnRetryListener;
import com.helldefender.enjoylife.listener.OnShowHideViewListener;
import com.helldefender.enjoylife.presenter.base.BasePresenter;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by Helldefender on 2017/2/5.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    protected BaseActivity baseActivity;

    protected T mPresenter;

    protected FragmentComponent mFragmentComponent;

    protected StatusLayoutManager statusLayoutManager;

    LinearLayout llBaseFragment;

    protected abstract void initInject();

    protected abstract void initPresenter();

    protected abstract void initViews(View view, Bundle savedInstanceState);

    protected abstract int getLayoutId();

    protected abstract int getEmptyLayoutId();

    protected BaseActivity getHoldingActivity() {
        return baseActivity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.baseActivity = (BaseActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(((MyApplication) getActivity().getApplication()).getApplicationComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
        initInject();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        llBaseFragment = (LinearLayout) view.findViewById(R.id.ll_base_fragment);

        statusLayoutManager = new StatusLayoutManager.Builder(getHoldingActivity())
                .contentView(getLayoutId())
                .loadingView(R.layout.layout_status_loading)
                .emptyDataView(getEmptyLayoutId())
                .errorView(R.layout.layout_status_error)
                .netWorkErrorView(R.layout.layout_status_net_error)
                .retryViewId(R.layout.layout_status_retry)
//                .emptyDataRetryViewId()
//                .errorRetryViewId()
//                .netWorkErrorRetryViewId()
                .emptyDataIconImageId(R.id.tv_status_empty)
                .emptyDataTextTipId(R.id.img_status_empty)
                .onRetryListener(new OnRetryListener() {
                    @Override
                    public void onRetry() {

                    }
                })
                .onShowHideViewListener(new OnShowHideViewListener() {
                    @Override
                    public void onShowView(View view, int id) {

                    }

                    @Override
                    public void onHideView(View view, int id) {

                    }
                })
                .build();

        llBaseFragment.addView(statusLayoutManager.getRootLayout(), 0);

        statusLayoutManager.showContent();

        ButterKnife.bind(this, view);

        initViews(view, savedInstanceState);

        initPresenter();

        if (mPresenter != null) {
            mPresenter.onCreate();
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected void addFragment(BaseFragment baseFragment) {
        if (baseFragment != null) {
            getHoldingActivity().addFragment(baseFragment);
        }
    }

    protected void removeFragment() {
        getHoldingActivity().removeFragment();
    }

    protected void setToolBar(int toolbarId, int titleId, int logoId) {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).setToolBar(toolbarId, titleId, logoId);
        }
    }

    protected void setTitle(int titleId) {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            getActivity().setTitle(titleId);
        }
    }
}
