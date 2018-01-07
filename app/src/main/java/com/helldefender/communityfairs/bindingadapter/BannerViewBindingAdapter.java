package com.helldefender.communityfairs.bindingadapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v4.view.ViewPager;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import com.helldefender.communityfairs.modules.main.homepage.BannerItemViewModel;
import com.helldefender.communityfairs.widget.BannerView;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Helldefender on 2017/12/10 for CommunityFairs.
 * Function:
 * Description:
 */

public class BannerViewBindingAdapter {

    @BindingAdapter("images")
    public static void setList(BannerView bannerView, List<BannerItemViewModel> images) {
        ViewPager viewPager = bannerView.getViewPager();

        ViewPagerScroller viewPagerScroller = new ViewPagerScroller(bannerView.getContext());
        viewPagerScroller.setScrollDuration(1200);
        viewPagerScroller.setViewPagerScroller(viewPager);

        bannerView.setList(images);

        //bannerView.startLoop();
    }

    public static class ViewPagerScroller extends Scroller {

        private int mScrollDuration;

        public ViewPagerScroller(Context context) {
            super(context);
        }

        public ViewPagerScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public ViewPagerScroller(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, mScrollDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mScrollDuration);
        }

        public void setScrollDuration(int duration) {
            this.mScrollDuration = duration;
        }

        public void setViewPagerScroller(ViewPager viewPager) {
            try {
                Field mScroller = ViewPager.class.getDeclaredField("mScroller");
                mScroller.setAccessible(true);
                mScroller.set(viewPager, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
