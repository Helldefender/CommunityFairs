package com.helldefender.enjoylife.modules;

import android.content.Context;

import com.helldefender.enjoylife.model.Event;
import com.helldefender.enjoylife.ui.adapter.base.BaseAdapter;
import com.helldefender.enjoylife.ui.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Helldefender on 2017/6/15.
 */

public class EventAdapter extends BaseAdapter<Event> {

    public EventAdapter(Context context, int layoutResId, List<Event> data) {
        super(context, layoutResId, data);
    }

    @Override
    public void onBind(BaseViewHolder holder, Event event, int position, int viewType) {

    }
}
