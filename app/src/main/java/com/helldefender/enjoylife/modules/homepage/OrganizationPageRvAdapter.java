package com.helldefender.enjoylife.modules.homepage;

import android.content.Context;

import com.helldefender.enjoylife.model.Event;
import com.helldefender.enjoylife.ui.adapter.base.BaseAdapter;
import com.helldefender.enjoylife.ui.adapter.base.BaseViewHolder;
import com.helldefender.enjoylife.ui.adapter.base.MultiViewType;

import java.util.List;

/**
 * Created by Helldefender on 2017/6/15.
 */

public class OrganizationPageRvAdapter extends BaseAdapter<Event> {

    public static final int TYPE_HEADER = 1;

    public static final int TYPE_CONTENT = 2;

    public OrganizationPageRvAdapter(Context context, MultiViewType<Event> multiViewType, List<Event> data) {
        super(context, multiViewType, data);
    }

    @Override
    public void onBind(BaseViewHolder holder, Event event, int position, int viewType) {

    }
}
