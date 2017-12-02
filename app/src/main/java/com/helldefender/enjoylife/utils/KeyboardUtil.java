package com.helldefender.enjoylife.utils;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Helldefender on 2017/11/18 for CommunityFairs.
 * Function:
 * Description:
 */

public class KeyboardUtil {
    private KeyboardUtil() {
        /* 作为一个工具类，应该有工具类的自觉，不应该被实例化嘛 */
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    /**
     * 显示键盘
     * @param v 作为焦点的View
     */
    public static void showInput(View v) {
        v.requestFocus(); // 让这个view得到焦点先
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 隐藏键盘
     * @param v 作为焦点的View
     */
    public static void hideInput(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    /**
     * 在触碰非EditText情况下自动隐藏键盘
     * @param v 触摸的view，用来判断是否触摸的是EditText
     * @param event 触摸事件
     */
    public static void autoHideInput(View v, MotionEvent event) {
        if (isShouldHideInput(v, event)) hideInput(v);
    }

    /*
     *  判断触碰的位置是否在EditText上，是否应该隐藏键盘
     *  @param v 触摸的view，用来判断是否触摸的是EditText
     *  @param event 触摸事件
     *  @return 是否应该隐藏键盘
     */
    private static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            /* 得到editText的位置 */
            int[] leftTop = {0, 0};
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            /* 超过EditText范围就返回true，表示你摸了除了EditText的别人！键盘不理你了，应该自动隐藏 */
            return !(event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    /**
     * 回车不换行
     * @param editText
     */
    protected void setViewEditorActionEvent(EditText editText) {
        if (editText != null) {
            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (event != null) {
                        return event.getKeyCode() == KeyEvent.KEYCODE_ENTER;
                    }
                    return false;
                }
            });
        }
    }

//    /**
//     * 关闭键盘
//     */
//    protected void closeSoftKeyboard() {
//        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        if (inputMethodManager.isActive() && getCurrentFocus() != null) {
//            if (getCurrentFocus().getWindowToken() != null) {
//                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//            }
//        }
//    }
}
