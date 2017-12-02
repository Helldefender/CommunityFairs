package com.helldefender.enjoylife.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/**
 * Created by Helldefender on 2017/7/22.
 */

public class SoftHideKeyBoardUtil {

    private View mChildOfContent;

    private int usableHeightPrevious;

    private FrameLayout.LayoutParams frameLayoutParams;

    private SoftHideKeyBoardUtil(Activity activity) {
        //找到DecorView
        FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        //获取到用户设置的View
        mChildOfContent = content.getChildAt(0);
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                possiblyResizeChildOfContent();
            }
        });

        frameLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();
    }

    private void possiblyResizeChildOfContent() {
        int usableHeightNow = computeUsableHeight();

        if (usableHeightNow != usableHeightPrevious) {
            int usableHeightSansKeyBoard = mChildOfContent.getRootView().getHeight();
            //Activity中xml布局的高度
            int heightDifference = usableHeightSansKeyBoard - usableHeightNow;
            if (heightDifference > 100 /*(usableHeightSansKeyBoard / 4)*/) {
                frameLayoutParams.height = usableHeightSansKeyBoard - heightDifference;
            } else {
                frameLayoutParams.height = usableHeightSansKeyBoard;
            }

            mChildOfContent.requestLayout();
            usableHeightPrevious = usableHeightNow;
        }
    }

    private int computeUsableHeight() {
        Rect rect = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(rect);
        //全屏时，直接返回rect.bottom,rect.top是状态栏的高度
//        return (rect.bottom - rect.top);
        return rect.bottom;
    }

    public static void assistActivity(Activity activity) {
        new SoftHideKeyBoardUtil(activity);
    }
}
