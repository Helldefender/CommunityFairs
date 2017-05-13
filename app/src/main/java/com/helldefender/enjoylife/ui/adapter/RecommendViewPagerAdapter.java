package com.helldefender.enjoylife.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by Helldefender on 2017/2/5.
 */

public class RecommendViewPagerAdapter extends PagerAdapter {
    private ArrayList<RelativeLayout> viewList;

    public RecommendViewPagerAdapter(ArrayList<RelativeLayout> viewList) {
        this.viewList = viewList;
    }

    public interface onItemClickListener {
        void onItemClick(View view, int position);
    }

    onItemClickListener onItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override

    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position%=viewList.size();
        //用户左滑，position出现负值，对负值进行处理，使其落在正确的区间内
        if (position < 0) {
            position = viewList.size() + position;
        }
        RelativeLayout view = viewList.get(position);
        //如果view在之前已经添加到了一个父组件，则必须先remove,封否则会抛出异常   他所谓的父组件是什么？ArrayList ?ViewGroup?LinearLayout RelativeLayout
        ViewParent viewParent=view.getParent();
        if (viewParent != null) {
            ViewGroup viewGroup = (ViewGroup) viewParent;
            viewGroup.removeView(view);
        }
        container.addView(view);
        final int finalPosition = position;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, finalPosition);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}