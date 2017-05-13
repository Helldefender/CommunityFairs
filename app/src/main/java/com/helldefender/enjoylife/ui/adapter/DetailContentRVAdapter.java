package com.helldefender.enjoylife.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.ui.adapter.base.BaseAdapter;
import com.helldefender.enjoylife.ui.adapter.base.BaseViewHolder;
import com.helldefender.enjoylife.ui.adapter.base.MultiViewType;
import com.helldefender.enjoylife.ui.adapter.base.PosterRVAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helldefender on 2017/5/8.
 */

public class DetailContentRVAdapter extends BaseAdapter<String> {

    public static final int DETAIL_TYPE_CONTENT = 0;

    public static final int DETAIL_TYPE_COMMENT = 1;

    public DetailContentRVAdapter(Context context, MultiViewType<String> multiViewType, List<String> data) {
        super(context, multiViewType, data);
    }

    @Override
    public void onBind(BaseViewHolder holder, String s, int position, int viewType) {
        if (viewType == DETAIL_TYPE_CONTENT) {
            bindContent(holder);
        } else {
            bindComment(holder);
        }
    }

    private void bindContent(BaseViewHolder baseViewHolder) {
        RecyclerView hListRecyclerView = baseViewHolder.getView(R.id.rv_detail_item_poster);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        hListRecyclerView.setLayoutManager(linearLayoutManager);
        List<String> data = new ArrayList<String>();
        data.add("测试");
        data.add("测试");
        data.add("测试");
        data.add("测试");
        data.add("测试");
        data.add("测试");
        data.add("测试");
        data.add("测试");
        hListRecyclerView.setAdapter(new PosterRVAdapter(context, R.layout.item_detail_rv_poster, data));
    }

    private void bindComment(BaseViewHolder baseViewHolder) {

    }
}
