package com.helldefender.enjoylife.utils;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

/**
 * Created by Helldefender on 2017/6/4.
 */

public class TabIndicatorUtil {

    public static void setTabIndicatorWidth(Context context, TabLayout mTabLayout, int leftMarginValue, int rightMarginValue) {
        Class<?> tabLayout = mTabLayout.getClass();
        Field tabStripField = null;
        try {
            tabStripField = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        tabStripField.setAccessible(true);

        LinearLayout tabLinearLayout = null;
        try {
            tabLinearLayout = (LinearLayout) tabStripField.get(mTabLayout);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = DisplayUtil.dip2px(context, leftMarginValue);
        int right = DisplayUtil.dip2px(context, rightMarginValue);

        for (int i = 0; i < tabLinearLayout.getChildCount(); i++) {
            View child = tabLinearLayout.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
}
