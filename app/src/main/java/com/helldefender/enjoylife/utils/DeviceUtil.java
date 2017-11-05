package com.helldefender.enjoylife.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Helldefender on 2017/5/26.
 */

public class DeviceUtil {

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static Point getScreenSize(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2){
            return new Point(display.getWidth(), display.getHeight());
        }else{
            Point point = new Point();
            display.getSize(point);
            return point;
        }
    }
}
