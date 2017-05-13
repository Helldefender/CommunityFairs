package com.helldefender.enjoylife.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.delete.server.entity.CommentBean;

import java.util.List;

/**
 * Created by Helldefender on 2017/2/24.
 */

public class CommentRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CommentBean> data;
    private Context mContext;

    public CommentRvAdapter(List<CommentBean> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class CommentItemHolder extends RecyclerView.ViewHolder {

        public CommentItemHolder(View itemView) {
            super(itemView);
        }
    }
}
