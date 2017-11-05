package com.helldefender.enjoylife.modules;

import android.content.Context;

import com.helldefender.enjoylife.model.Organization;
import com.helldefender.enjoylife.ui.adapter.base.BaseAdapter;
import com.helldefender.enjoylife.ui.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Helldefender on 2017/6/15.
 */

public class OrganizationAdapter extends BaseAdapter<Organization>{

    public OrganizationAdapter(Context context, int layoutResId, List<Organization> data) {
        super(context, layoutResId, data);
    }

    @Override
    public void onBind(BaseViewHolder holder, Organization organization, int position, int viewType) {

    }
}
