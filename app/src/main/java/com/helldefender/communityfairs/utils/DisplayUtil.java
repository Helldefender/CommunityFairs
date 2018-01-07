package com.helldefender.communityfairs.utils;

import android.content.Context;

import com.helldefender.communityfairs.app.App;

/**
 * Created by Helldefender on 2017/6/4.
 */

public class DisplayUtil {

    public static int px2dp(float pxValue) {
        final float scale = App.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2px(float dipValue) {
        final float scale = App.getContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    public static int px2sp(float pxValue) {
        final float fontScale = App.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(float spValue) {
        final float fontScale = App.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
