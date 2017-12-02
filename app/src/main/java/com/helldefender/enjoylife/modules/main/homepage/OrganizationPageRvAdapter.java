package com.helldefender.enjoylife.modules.main.homepage;

import android.content.Context;

import com.helldefender.enjoylife.model.entity.Event;
import com.helldefender.enjoylife.app.BaseAdapter;
import com.helldefender.enjoylife.app.BaseViewHolder;
import com.helldefender.enjoylife.app.MultiViewType;

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
