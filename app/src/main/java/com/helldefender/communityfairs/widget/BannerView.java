package com.helldefender.communityfairs.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.model.entity.Image;
import com.viewpagerindicator.CirclePageIndicator;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helldefender on 2017/4/13.
 */

public class BannerView extends FrameLayout {

    private ViewPager viewPager;

    private CirclePageIndicator circlePageIndicator;

    private BannerHandler handler = new BannerHandler(new WeakReference<>(this));

    public BannerView(@NonNull Context context) {
        this(context, null);
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.view_banner, this);

        viewPager = (ViewPager) findViewById(R.id.vp_banner);
        viewPager.setOnPageChangeListener(new BannerView.ViewPageChangeListener());
        circlePageIndicator = (CirclePageIndicator) findViewById(R.id.vpIndicator_banner);
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public CirclePageIndicator getCirclePageIndicator() {
        return circlePageIndicator;
    }

    public void startLoop() {
        handler.sendEmptyMessageDelayed(BannerHandler.MSG_BREAK_SILENT, BannerHandler.MSG_DELAY);
    }

    public void stopLoop() {
        handler.sendEmptyMessageDelayed(BannerHandler.MSG_KEEP_SILENT, BannerHandler.MSG_DELAY);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopLoop();
        //destroyBitmaps();
    }


//    private void destroyBitmaps() {
//        for (int i = 0; i < imageViewList.size(); i++) {
//            ImageView imageView = imageViewList.get(i);
//            Drawable drawable = imageView.getDrawable();
//            if (drawable != null) {
//                //解除drawable对view的引用
//                drawable.setCallback(null); //销毁ImageView资源，回收内存
//            }
//        }
//    }

    private static class BannerHandler extends Handler {

        protected static final int MSG_UPDATE_IMAGE = 1;

        protected static final int MSG_KEEP_SILENT = 2;

        protected static final int MSG_BREAK_SILENT = 3;

        protected static final int MSG_PAGE_CHANGED = 4;

        protected static final long MSG_DELAY = 6000;

        private WeakReference<BannerView> weakReference;

        private int currentItem = 0;

        public BannerHandler(WeakReference<BannerView> weakReference) {
            this.weakReference = weakReference;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BannerView bannerView = weakReference.get();

            if (bannerView == null) {
                return;
            }

            if (bannerView.handler.hasMessages(MSG_UPDATE_IMAGE)) {
                bannerView.handler.removeMessages(MSG_UPDATE_IMAGE);
            }

            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    bannerView.viewPager.setCurrentItem(currentItem);
                    bannerView.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    break;
                case MSG_BREAK_SILENT:
                    bannerView.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    currentItem = msg.arg1;
                    break;
                default:
                    break;
            }
        }
    }

    private class ViewPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            handler.sendMessage(Message.obtain(handler, BannerHandler.MSG_PAGE_CHANGED, position, 0));
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case ViewPager.SCROLL_STATE_DRAGGING:
                    handler.sendEmptyMessage(BannerHandler.MSG_KEEP_SILENT);
                    break;
                case ViewPager.SCROLL_STATE_IDLE:
                    handler.sendEmptyMessageDelayed(BannerHandler.MSG_UPDATE_IMAGE, BannerHandler.MSG_DELAY);
                    break;
                default:
                    break;
            }
        }
    }
}
