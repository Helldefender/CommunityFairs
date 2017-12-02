package com.helldefender.enjoylife.bindingadapter;

import android.app.VoiceInteractor;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Helldefender on 2017/11/30 for CommunityFairs.
 * Function:
 * Description:
 */

public class RecyclerViewBindingAdapter {
    @BindingAdapter(value = {"onLoadMoreCommand"})
    public static void onLoadMoreCommand(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int state;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }
}
