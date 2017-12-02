package com.helldefender.enjoylife.modules.community.publish;

import android.content.Context;

import com.helldefender.enjoylife.app.BaseAdapter;
import com.helldefender.enjoylife.app.BaseViewHolder;

import java.util.List;

/**
 * Created by Helldefender on 2017/5/8.
 */

public class PosterRVAdapter extends BaseAdapter<String> {

    public PosterRVAdapter(Context context, int layoutResId, List<String> data) {
        super(context, layoutResId, data);
    }

    @Override
    public void onBind(BaseViewHolder holder, String s, int position, int viewType) {

    }
}
