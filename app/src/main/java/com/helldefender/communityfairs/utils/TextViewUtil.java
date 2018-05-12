package com.helldefender.communityfairs.utils;

import android.text.TextPaint;
import android.widget.TextView;

/**
 * 功能：
 * 描述：
 * Created by Helldefender on 2018/4/21.
 */

public class TextViewUtil {

    public static float getTextViewLength(TextView textView) {
        TextPaint paint = textView.getPaint();
        return paint.measureText(textView.getText().toString());
    }

    public static float getTextViewLength(TextView textView, float textSize) {
        TextPaint paint = textView.getPaint();
        paint.setTextSize(textSize);
        return paint.measureText(textView.getText().toString());
    }
}
