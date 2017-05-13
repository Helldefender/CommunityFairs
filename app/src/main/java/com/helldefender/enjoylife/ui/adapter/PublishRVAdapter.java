package com.helldefender.enjoylife.ui.adapter;

import android.content.Context;

import com.helldefender.enjoylife.ui.adapter.base.BaseAdapter;
import com.helldefender.enjoylife.ui.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Helldefender on 2017/5/11.
 */

public class PublishRVAdapter extends BaseAdapter<String> {
    public PublishRVAdapter(Context context, int layoutResId, List<String> data) {
        super(context, layoutResId, data);
    }

    @Override
    public void onBind(BaseViewHolder holder, String s, int position, int viewType) {

    }
}
