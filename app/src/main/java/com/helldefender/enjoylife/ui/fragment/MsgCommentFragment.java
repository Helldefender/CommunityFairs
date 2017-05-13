package com.helldefender.enjoylife.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.ui.fragment.base.BaseFragment;

/**
 * Created by Helldefender on 2017/2/24.
 */

public class MsgCommentFragment extends BaseFragment {

    private static final String KEY_MSG_BUNDLE = "message";

    private RecyclerView mRecyclerView;

//    @Override
//    protected void handleView(View view, Bundle savedInstanceState) {
//
//        if (getArguments() != null) {
//            setRecyclerView(getArguments().getString(KEY_MSG_BUNDLE), view);
//        } else {
//            setRecyclerView(getArguments().getString(KEY_MSG_BUNDLE), view);
//        }
//
//        List<MessageBean> data = new ArrayList<MessageBean>();
//        data.add(new MessageBean());
//        data.add(new MessageBean());
//        data.add(new MessageBean());
//        data.add(new MessageBean());
//        data.add(new MessageBean());
//        data.add(new MessageBean());
//        data.add(new MessageBean());
//
//        MessageRvAdapter messageRvAdapter = new MessageRvAdapter(getHoldingActivity(), R.layout.item_message_recyclerview, data);
//
//        mRecyclerView.setAdapter(messageRvAdapter);
//    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_comment_area;
    }

    @Override
    protected int getEmptyLayoutId() {
        return 0;
    }

    private void setRecyclerView(String type, View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.comment_area_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getHoldingActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);


    }
}
