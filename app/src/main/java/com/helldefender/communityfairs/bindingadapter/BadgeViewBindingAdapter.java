package com.helldefender.communityfairs.bindingadapter;

import android.databinding.BindingAdapter;

import com.helldefender.communityfairs.widget.BadgeView;

/**
 * Created by Helldefender on 2018/1/6 for CommunityFairs.
 * Function:
 * Description:
 */

public class BadgeViewBindingAdapter {

    @BindingAdapter(value = {"count"}, requireAll = false)
    public static void setBadgeView(BadgeView badgeView, int count) {
        badgeView.setBadgeCount(count);
//        BadgeView badgeView = new BadgeView();
//        badgeView.setTargetView();
//        badgeView.setBadgeGravity();
//        badgeView.setBadgeCount();
    }

}
