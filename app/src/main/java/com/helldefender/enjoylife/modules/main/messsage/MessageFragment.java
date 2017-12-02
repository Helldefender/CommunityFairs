package com.helldefender.enjoylife.modules.main.messsage;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.modules.impl.MessagePresenterImpl;
import com.helldefender.enjoylife.app.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Helldefender on 2017/2/5.
 */

public class MessageFragment extends BaseFragment implements MessageView {

    @Inject
    MessagePresenterImpl messagePresenter;

    @BindView(R.id.rv_message)
    RecyclerView messageRecyclerView;

    Unbinder unbinder;

    @Override
    protected void initInject() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initPresenter() {
        mPresenter = messagePresenter;
        mPresenter.attachView(this);
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        initPresenter();
        initMessageRecyclerView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected int getEmptyLayoutId() {
        return 0;
    }

    @Override
    public void showLoadingLayout() {

    }

    @Override
    public void showEmptyLayout() {

    }

    @Override
    public void showErrorLayout(int message) {

    }

    private void initMessageRecyclerView() {
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(getHoldingActivity()));
        List<String> data = new ArrayList<String>();
        data.add("测试");

        messageRecyclerView.setAdapter(new MessageRVAdapter(getHoldingActivity(), R.layout.item_message_rv, data));
        //messageRecyclerView.addItemDecoration(new MessageItemDecoration());
        messageRecyclerView.addItemDecoration(new DividerItemDecoration(getHoldingActivity(), DividerItemDecoration.VERTICAL));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
