package com.helldefender.communityfairs.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.viewpagerindicator.PageIndicator;

/**
 * Created by Helldefender on 2018/1/2 for CommunityFairs.
 * Function:
 * Description:
 */

public class CircleImageIndicator extends View implements PageIndicator{

    public CircleImageIndicator(Context context) {
        super(context);
    }

    public CircleImageIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setViewPager(ViewPager view) {

    }

    @Override
    public void setViewPager(ViewPager view, int initialPosition) {

    }

    @Override
    public void setCurrentItem(int item) {

    }

    @Override
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {

    }

    @Override
    public void notifyDataSetChanged() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
