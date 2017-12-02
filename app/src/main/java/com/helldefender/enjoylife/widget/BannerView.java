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
import com.helldefender.enjoylife.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helldefender on 2017/4/13.
 */

public class BannerView extends FrameLayout {

    //private static final int IMAGE_COUNT = 4;

    //private static final int INDICATOR_COUNT = 4;

    private static final boolean isAutoPlay = true;

    private Context mContext;

    private List<ImageView> imageViewList;

    private List<String> titleList;

    private List<View> indicatorList;

    private List<String> imageUrlList;

    private OnClickListener onClickListener;

    private ViewPager viewPager;

    private TextView titleText;

    private BannerHandler handler = new BannerHandler(new WeakReference<BannerView>(this));

    public BannerView(@NonNull Context context) {
        this(context, null);
        this.mContext = context;
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        this.mContext = context;
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (!isInEditMode()) {
            this.mContext = context;
            initData();

            if (isAutoPlay) {
                startPlay();
            }
        }
    }

    private void initData() {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.view_banner, this, true);

        imageViewList = new ArrayList<ImageView>();
        indicatorList = new ArrayList<View>();
    }

    public void initUI() {
        if (imageUrlList == null || imageUrlList.size() == 0) {
            return;
        }

        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_banner, this, true);

        LinearLayout indicatorLayout = (LinearLayout) view.findViewById(R.id.ll_banner_indicator);
        indicatorLayout.removeAllViews();

        titleText = (TextView) view.findViewById(R.id.tv_banner_title);
        titleText.setText(titleList.get(0));

        for (int i = 0; i < imageUrlList.size(); i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViewList.add(imageView);

            ImageView indicatorView = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = 8;
            params.rightMargin = 8;
            indicatorLayout.addView(indicatorView, params);
            indicatorList.add(indicatorView);
        }

        for (int i = 0; i < indicatorList.size(); i++) {
            if (i == 0) {
                indicatorList.get(0).setBackgroundResource(R.drawable.carousel_strip);
            } else {
                indicatorList.get(i).setBackgroundResource(R.drawable.carousel_circle);
            }
        }

        if (imageViewList.size() > 0 && onClickListener != null) {
            imageViewList.get(imageViewList.size() - 1).setOnClickListener(onClickListener);
        }

        viewPager = (ViewPager) view.findViewById(R.id.vp_banner_image);
        viewPager.setFocusable(true);
        viewPager.setAdapter(new ViewPagerAdapter());

        viewPager.setCurrentItem(Integer.MAX_VALUE / 2);

        viewPager.setOnPageChangeListener(new ViewPageChangeListener());
    }

    public void startPlay() {
        handler.sendEmptyMessageDelayed(BannerHandler.MSG_KEEP_SILENT, BannerHandler.MSG_DELAY);
    }

    public void stopPlay() {
        handler.sendEmptyMessageDelayed(BannerHandler.MSG_KEEP_SILENT, BannerHandler.MSG_DELAY);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setImageUrls(List<String> imageUrlsList, List<String> titleList) {
        this.imageUrlList = imageUrlsList;
        this.titleList = titleList;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopPlay();
        destoryBitmaps();
    }

    private static class BannerHandler extends Handler {

        protected static final int MSG_UPDATE_IMAGE = 1;

        protected static final int MSG_KEEP_SILENT = 2;

        protected static final int MSG_BREAK_SILENT = 3;

        protected static final int MSG_PAGE_CHANGED = 4;

        protected static final long MSG_DELAY = 3000;

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
            Glide.with(mContext).load("http://pic16.nipic.com/20110921/7247268_215811562102_2.jpg").into(imageView);
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
            Log.d("DAI", "ONPAGESELDCTED");
            handler.sendMessage(Message.obtain(handler, BannerHandler.MSG_PAGE_CHANGED, position, 0));

            int total = indicatorList.size();
            position %= total;

            for (int i = 0; i < total; i++) {
                if (i == position) {
                    indicatorList.get(i).setBackgroundResource(R.drawable.carousel_strip);
                } else {
                    indicatorList.get(i).setBackgroundResource(R.drawable.carousel_circle);
                }
            }

            //titleText.setText(titleList.get(position));
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case ViewPager.SCROLL_STATE_DRAGGING:
                    Log.d("DAI", "scroll state dragging");
                    handler.sendEmptyMessage(BannerHandler.MSG_KEEP_SILENT);
                    break;
                case ViewPager.SCROLL_STATE_IDLE:
                    Log.d("DAI", "SCROLL STATE IDLE");
                    handler.sendEmptyMessageDelayed(BannerHandler.MSG_UPDATE_IMAGE, BannerHandler.MSG_DELAY);
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
        if (imageViewList == null) {
            return;
        }

        for (int i = 0; i < imageViewList.size(); i++) {
            ImageView imageView = imageViewList.get(i);
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                //解除drawable对view的引用
                drawable.setCallback(null);
            }
        }
    }
}
