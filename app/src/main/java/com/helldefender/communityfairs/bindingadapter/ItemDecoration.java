package com.helldefender.communityfairs.bindingadapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.app.App;
import com.helldefender.communityfairs.widget.recyclerview.AnoDividerItemDecoration;
import com.helldefender.communityfairs.widget.recyclerview.DividerItemDecoration;

/**
 * Created by Helldefender on 2018/1/6 for CommunityFairs.
 * Function:
 * Description:
 */

public class ItemDecoration {

    public interface ItemDecorationFactory {
        RecyclerView.ItemDecoration create(RecyclerView recyclerView);
    }

    public static ItemDecoration.ItemDecorationFactory getHorizontalItemDecoration() {
        return new ItemDecorationFactory() {
            @Override
            public RecyclerView.ItemDecoration create(RecyclerView recyclerView) {
                return new AnoDividerItemDecoration(App.getContext(), R.color.colorDivider, R.dimen.divider_height, LinearLayoutManager.VERTICAL);
                //return new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL);
            }
        };
    }

    public static ItemDecoration.ItemDecorationFactory getVerticalItemDecoration() {
        return new ItemDecorationFactory() {
            @Override
            public RecyclerView.ItemDecoration create(RecyclerView recyclerView) {
                return new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
            }
        };
    }
}
