package com.helldefender.communityfairs.widget.viewpagetitle;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import com.helldefender.communityfairs.utils.DeviceUtil;
import com.helldefender.communityfairs.utils.TextViewUtil;

import java.util.ArrayList;

import static android.support.v4.view.ViewPager.SCROLL_STATE_SETTLING;

/**
 * 功能：
 * 描述：
 * Created by Helldefender on 2018/4/21.
 */

public class OnPageChangeListener implements ViewPager.OnPageChangeListener {
    private static final String TAG = "test_tag";
    private final Context context;
    private int fixLeftDis;
    private ArrayList<TextView> textViews;
    private ViewPagerTitle viewPagerTitle;
    private DynamicLine dynamicLine;

    private ViewPager pager;
    private int pagerCount;
    private int screenWidth;
    private int lineWidth;
    private int everyLength;
    private int lastPosition;
    private int dis;
    private int[] location = new int[2];


    public OnPageChangeListener(Context context, ViewPager viewPager, DynamicLine dynamicLine, ViewPagerTitle viewPagerTitle, int allLength, int margin, int fixLeftDis) {
        this.viewPagerTitle = viewPagerTitle;
        this.pager = viewPager;
        this.dynamicLine = dynamicLine;
        this.context = context;
        textViews = viewPagerTitle.getTextView();
        pagerCount = textViews.size();
        screenWidth = DeviceUtil.getScreenWidth(context);

        lineWidth = (int) TextViewUtil.getTextViewLength(textViews.get(0));

        everyLength = allLength / pagerCount;
        dis = margin;
        this.fixLeftDis = fixLeftDis;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (lastPosition > position) {
            Log.d(TAG, "lastPosition < position positionOffset = " + positionOffset + "][position=" + position + "][ locationtm 0 = ");
            dynamicLine.updateView((position + positionOffset) * everyLength + dis + fixLeftDis, (lastPosition + 1) * everyLength - dis);
        } else {
            Log.d(TAG, "lastPosition >= position positionOffset = " + positionOffset + "][position=" + position);
            if (positionOffset > 0.5f) {
                positionOffset = 0.5f;
            }
            dynamicLine.updateView(lastPosition * everyLength + dis + fixLeftDis, (position + positionOffset * 2) * everyLength + dis + lineWidth);
        }
    }

    @Override
    public void onPageSelected(int position) {
        Log.d(TAG, lastPosition + "[[[[");
        viewPagerTitle.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        boolean scrollRight;
        if (state == SCROLL_STATE_SETTLING) {
            scrollRight = lastPosition < pager.getCurrentItem();
            lastPosition = pager.getCurrentItem();
            if (lastPosition + 1 < textViews.size() && lastPosition - 1 >= 0) {
                textViews.get(scrollRight ? lastPosition + 1 : lastPosition - 1).getLocationOnScreen(location);
                if (location[0] > screenWidth) {
                    viewPagerTitle.smoothScrollBy(screenWidth / 2, 0);
                } else if (location[0] < 0) {
                    viewPagerTitle.smoothScrollBy(-screenWidth / 2, 0);
                }
            }
        }
    }
}
