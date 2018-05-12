package com.helldefender.communityfairs.widget.translucent;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ScrollView;

import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.utils.DisplayUtil;


/**
 * Created by Helldefender on 2018/1/2 for CommunityFairs.
 * Function:
 * Description:
 */

public class TranslucentScrollView extends ScrollView {

    //伸缩视图
    private View zoomView;
    //伸缩视图初始高度
    private int zoomViewInitHeight = 0;
    // 记录首次按下位置
    private float mFirstPosition = 0;
    // 是否正在放大
    private Boolean mScaling = false;

    //渐变的视图
    private View transView;

    //渐变颜色
    //private int transColor = Color.WHITE;

    private Drawable transDrawable;
    //渐变开始位置
    private int transStartY = 50;
    //渐变结束位置
    private int transEndY = 300;

    //渐变开始默认位置，Y轴，50dp
    private final int DFT_TRANSSTARTY = 50;
    //渐变结束默认位置，Y轴，300dp
    private final int DFT_TRANSENDY = 300;

    private TranslucentScrollView.TranslucentChangedListener translucentChangedListener;

    public interface TranslucentChangedListener {
        void onTranslucentChanged(int transAlpha);
    }

    public TranslucentScrollView(Context context) {
        super(context);
    }

    public TranslucentScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TranslucentScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTranslucentChangedListener(TranslucentScrollView.TranslucentChangedListener translucentChangedListener) {
        this.translucentChangedListener = translucentChangedListener;
    }

    public void setPullZoomView(View zoomView) {
        this.zoomView = zoomView;
        zoomViewInitHeight = zoomView.getLayoutParams().height;
        if (zoomViewInitHeight == LayoutParams.MATCH_PARENT || zoomViewInitHeight == WindowManager.LayoutParams.WRAP_CONTENT) {
            zoomView.post(new Runnable() {
                @Override
                public void run() {
                    zoomViewInitHeight = TranslucentScrollView.this.zoomView.getHeight();
                }
            });
        }
    }

    public void setTransView(View transView) {
        //setTransView(transView, getResources().getColor(R.color.colorAccentBlue), DisplayUtil.dp2px(getContext(), DFT_TRANSSTARTY), DisplayUtil.dp2px(getContext(), DFT_TRANSENDY));
        setTransView(transView, getResources().getDrawable(R.drawable.bg_primary_color_gradient), DisplayUtil.dp2px( DFT_TRANSSTARTY), DisplayUtil.dp2px(DFT_TRANSENDY));
    }


    //public void setTransView(View transView, @ColorInt int transColor, int transStartY, int transEndY) {
    public void setTransView(View transView, Drawable drawable, int transStartY, int transEndY) {
        this.transView = transView;
        //初始视图-透明
        //this.transView.setBackgroundColor(ColorUtils.setAlphaComponent(transColor, 0));
        drawable.setAlpha(0);
        this.transView.setBackground(drawable);
        this.transStartY = transStartY;
        this.transEndY = transEndY;
        //this.transColor = transColor;
        this.transDrawable = drawable;
        if (transStartY > transEndY) {
            throw new IllegalArgumentException("transStartY 不得大于 transEndY .. ");
        }
    }

    private int getTransAlpha() {
        float scrollY = getScrollY();
        if (transStartY != 0) {
            if (scrollY <= transStartY) {
                return 0;
            } else if (scrollY >= transEndY) {
                return 255;
            } else {
                return (int) ((scrollY - transStartY) / (transEndY - transStartY) * 255 * 1.12);
            }
        } else {
            if (scrollY >= transEndY) {
                return 255;
            }
            return (int) ((transEndY - scrollY) / transEndY * 255);
        }
    }

    private void resetZoomView() {
        final ViewGroup.LayoutParams lp = zoomView.getLayoutParams();
        final float h = zoomView.getLayoutParams().height;// ZoomView当前高度

        // 设置动画
        ValueAnimator anim = ObjectAnimator.ofFloat(0.0F, 1.0F).setDuration(200);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                lp.height = (int) (h - (h - zoomViewInitHeight) * cVal);
                zoomView.setLayoutParams(lp);
            }
        });
        anim.start();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        int transAlpha = getTransAlpha();

        if (transView != null) {
            //transView.setBackgroundColor(ColorUtils.setAlphaComponent(transColor, transAlpha));
            transDrawable.setAlpha(transAlpha);
            transView.setBackground(transDrawable);
        }
        if (translucentChangedListener != null) {
            translucentChangedListener.onTranslucentChanged(transAlpha);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (zoomView != null) {
            ViewGroup.LayoutParams params = zoomView.getLayoutParams();
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    //手指离开后恢复图片
                    mScaling = false;
                    resetZoomView();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!mScaling) {
                        if (getScrollY() == 0) {
                            mFirstPosition = event.getY();
                        } else {
                            break;
                        }
                    }

                    int distance = (int) ((event.getY() - mFirstPosition) * 0.6);
                    if (distance < 0) {
                        break;
                    }
                    mScaling = true;
                    params.height = zoomViewInitHeight + distance;
                    zoomView.setLayoutParams(params);
                    return true;
            }
        }

        return super.onTouchEvent(event);
    }

}
