package com.helldefender.enjoylife.listener;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by Helldefender on 2017/4/27.
 */

public abstract class DiffCallbacks<T> extends DiffUtil.Callback {
    //RecyclerView刷新
    //RecycleView的Adapter具有四个刷新的方法

    private List<T> oldList;

    private List<T> newList;

    public DiffCallbacks(List<T> oldList, List<T> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    public List<T> getOldList() {
        return oldList;
    }

    public void setOldList(List<T> oldList) {
        this.oldList = oldList;
    }

    public List<T> getNewList() {
        return newList;
    }

    public void setNewList(List<T> newList) {
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

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }

    //用来判断两个对象是否是相同的Item
    //这是不是重写了父类的方法
    public abstract boolean areItemsTheSame(int oldItemPosition, int newItemPosition);

    //用来检查两个item是否含有相同的数据
    public abstract boolean areContentsTheSame(int oldItemPosition, int newItemPosition);
}

