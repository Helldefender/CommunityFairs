package com.helldefender.communityfairs.widget;

import android.text.Spannable;
import android.text.SpannableString;

/**
 * Created by Helldefender on 2018/4/24 for Mirroe.
 * Function:
 * Description:
 */
public class SimpleSpinnerTextFormatter{

    public Spannable format(String text) {
        return new SpannableString(text);
    }
}
