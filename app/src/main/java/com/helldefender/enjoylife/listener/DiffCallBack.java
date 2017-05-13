package com.helldefender.enjoylife.listener;

import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by Helldefender on 2017/4/27.
 */

public class DiffCallBack extends DiffUtil.Callback {

    private List<String> oldList;

    private List<String> newList;

    public DiffCallBack(List<String> oldList, List<String> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList == null ? 0 : oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList == null ? 0 : newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        String oldStr = oldList.get(oldItemPosition);
        String newStr = newList.get(newItemPosition);
        if (!oldStr.equals(newStr)) {
            return false;
        }
        return true;
    }
}
