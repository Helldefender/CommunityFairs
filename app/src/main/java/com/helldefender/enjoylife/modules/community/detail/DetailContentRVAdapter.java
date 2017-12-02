package com.helldefender.enjoylife.modules.community.detail;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.app.BaseAdapter;
import com.helldefender.enjoylife.app.BaseViewHolder;
import com.helldefender.enjoylife.app.MultiViewType;
import com.helldefender.enjoylife.modules.community.publish.PosterRVAdapter;

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
        RecyclerView hListRecyclerView = baseViewHolder.getView(R.id.rv_item_display_part);
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
        hListRecyclerView.setAdapter(new PosterRVAdapter(context, R.layout.item_detail_rv_display_part, data));
    }

    private void bindComment(BaseViewHolder baseViewHolder) {

    }
}
