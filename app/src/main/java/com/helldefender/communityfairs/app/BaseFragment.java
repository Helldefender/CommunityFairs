package com.helldefender.communityfairs.app;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Helldefender on 2017/2/5.
 */

public abstract class BaseFragment<V extends ViewDataBinding, VM extends ViewModel> extends Fragment {

    protected V binding;

    protected VM viewModel;

    protected BaseActivity baseActivity;

    protected abstract int getLayoutId();

    protected abstract int getVariableId();

    protected abstract VM getViewModel();

    protected abstract void initViews(V binding, Bundle savedInstanceState);

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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        binding.setVariable(getVariableId(), viewModel = getViewModel());
        initViews(binding, savedInstanceState);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
