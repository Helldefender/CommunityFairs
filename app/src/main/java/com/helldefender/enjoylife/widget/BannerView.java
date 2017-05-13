package com.helldefender.enjoylife.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.helldefender.enjoylife.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helldefender on 2017/4/13.
 */

public class BannerView extends FrameLayout {

    private static final int IMAGE_COUNT = 4;

    private static final int INDICATOR_COUNT = 4;

    private static final boolean isAutoPlay = false;

    private Context context;

    private List<ImageView> imageViewList;

    private List<String> titleList;

    private List<View> indicatorList;

    private List<String> imageUrlList;

    private OnClickListener onClickListener;

    private ViewPager viewPager;

    private TextView titleText;

    private ImageHandler handler = new ImageHandler(new WeakReference<BannerView>(this));

    public BannerView(@NonNull Context context) {
        this(context, null);
        this.context = context;
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (!isInEditMode()) {
            this.context = context;
            initData();

            if (isAutoPlay) {
                startPlay();
            }
        }
    }

    private void initData() {
        imageViewList = new ArrayList<ImageView>();
        indicatorList = new ArrayList<View>();
    }

    public void initUI() {
        if (imageUrlList == null || imageUrlList.size() == 0) {
            return;
        }

        LayoutInflater.from(context).inflate(R.layout.view_banner, this, true);
        LinearLayout indicatorLayout = (LinearLayout) findViewById(R.id.ll_banner_indicator);
        indicatorLayout.removeAllViews();

        titleText = (TextView) findViewById(R.id.tv_banner_title);
        titleText.setText(titleList.get(0));

        for (int i = 0; i < imageUrlList.size(); i++) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViewList.add(imageView);

            ImageView indicatorView = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = 8;
            params.rightMargin = 8;
            indicatorLayout.addView(indicatorView, params);
            indicatorList.add(indicatorView);
        }

        for (int i = 0; i < indicatorList.size(); i++) {
            if (i == 0) {
                indicatorList.get(0).setBackgroundResource(R.mipmap.carousel_strip);
            } else {
                indicatorList.get(i).setBackgroundResource(R.mipmap.carousel_circle);
            }
        }

        if (imageViewList.size() > 0 && onClickListener != null) {
            imageViewList.get(imageViewList.size() - 1).setOnClickListener(onClickListener);
        }

        viewPager = (ViewPager) findViewById(R.id.vp_banner_image);
        viewPager.setFocusable(true);
        viewPager.setAdapter(new ViewPagerAdapter());

        viewPager.setCurrentItem(Integer.MAX_VALUE / 2);

        viewPager.setOnPageChangeListener(new ViewPageChangeListener());
    }

    public void startPlay() {
        handler.sendEmptyMessageDelayed(ImageHandler.MSG_KEEP_SILENT, ImageHandler.MSG_DELAY);
    }

    public void stopPlay() {
        handler.sendEmptyMessageDelayed(ImageHandler.MSG_KEEP_SILENT, ImageHandler.MSG_DELAY);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setImageUrls(List<String> imageUrlsList, List<String> titleList) {
        this.imageUrlList = imageUrlsList;
        this.titleList = titleList;
    }


    public void destory() {
        stopPlay();
        destoryBitmaps();
    }

    private static class ImageHandler extends Handler {

        protected static final int MSG_UPDATE_IMAGE = 1;

        protected static final int MSG_KEEP_SILENT = 2;

        protected static final int MSG_BREAK_SILENT = 3;

        protected static final int MSG_PAGE_CHANGED = 4;

        protected static final long MSG_DELAY = 3000;

        private WeakReference<BannerView> weakReference;

        private int currentItem = 0;

        public ImageHandler(WeakReference<BannerView> weakReference) {
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

    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(View container, int position, Object object) {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position %= imageViewList.size();
            //用户左滑，position出现负值，对负值进行处理，使其落在正确的区间内
            if (position < 0) {
                position = imageViewList.size() + position;
            }

            ImageView imageView = imageViewList.get(position);
            Glide.with(context).load("http://pic16.nipic.com/20110921/7247268_215811562102_2.jpg").into(imageView);
            //如果view在之前已经添加到了一个父组件，则必须先remove,封否则会抛出异常
            ViewParent viewParent = imageView.getParent();

            if (viewParent != null) {
                ViewGroup viewGroup = (ViewGroup) viewParent;
                viewGroup.removeView(imageView);
            }

            container.addView(imageView);
            return imageView;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
    }

    private class ViewPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            handler.sendMessage(Message.obtain(handler, ImageHandler.MSG_PAGE_CHANGED, position, 0));

            int total = indicatorList.size();
            position %= total;

            for (int i = 0; i < total; i++) {
                if (i == position) {
                    indicatorList.get(i).setBackgroundResource(R.mipmap.carousel_strip);
                } else {
                    indicatorList.get(i).setBackgroundResource(R.mipmap.carousel_circle);
                }
            }

            //titleText.setText(titleList.get(position));
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case ViewPager.SCROLL_STATE_DRAGGING:
                    handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
                    break;
                case ViewPager.SCROLL_STATE_IDLE:
                    handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 销毁ImageView资源，回收内存
     */
    private void destoryBitmaps() {

        for (int i = 0; i < IMAGE_COUNT; i++) {
            ImageView imageView = imageViewList.get(i);
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                //解除drawable对view的引用
                drawable.setCallback(null);
            }
        }
    }
}
