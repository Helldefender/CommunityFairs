package com.helldefender.enjoylife.ui.adapter;

import android.content.Context;

import com.helldefender.enjoylife.ui.adapter.base.BaseAdapter;
import com.helldefender.enjoylife.ui.adapter.base.BaseViewHolder;
import com.helldefender.enjoylife.ui.adapter.base.MultiViewType;

import java.util.List;

/**
 * Created by Helldefender on 2017/5/11.
 */

public class DiscoveryRVAdapter extends BaseAdapter<String> {

    public static final int DISCOVERY_TYPE_ORGANIZATION__TITLE = 1;

    public static final int DISCOVERY_TYPE_ORGANIZATION = 2;

    public static final int DISCOVERY_TYPE_EVENT_TITLE = 3;

    public static final int DISCOVERT_TYPE_EVENT = 4;

    public DiscoveryRVAdapter(Context context, MultiViewType<String> multiViewType, List<String> data) {
        super(context, multiViewType, data);
    }

    @Override
    public void onBind(BaseViewHolder holder, String s, int position, int viewType) {

    }
}
