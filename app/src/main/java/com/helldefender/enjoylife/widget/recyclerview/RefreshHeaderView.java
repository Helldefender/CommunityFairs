package com.helldefender.enjoylife.widget.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.listener.RefreshStatusListener;

/**
 * Created by Helldefender on 2017/4/19.
 */

public class RefreshHeaderView extends RelativeLayout implements RefreshStatusListener{
    private ImageView ivArrow;

    private ImageView ivSuccess;

    private TextView tvRefresh;

    private ProgressBar progressBar;

    private Animation rotateUp;

    private Animation rotateDown;

    private boolean rotated = false;

    private int mHeight;

    public RefreshHeaderView(Context context) {
        this(context, null);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.view_refresh_header, this);
        tvRefresh = (TextView) findViewById(R.id.tvRefresh);
        ivArrow = (ImageView) findViewById(R.id.ivArrow);
        ivSuccess = (ImageView) findViewById(R.id.ivSuccess);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        rotateUp = AnimationUtils.loadAnimation(context, R.anim.rotate_up);
        rotateDown = AnimationUtils.loadAnimation(context, R.anim.rotate_down);
    }

    @Override
    public void onStart( int headerHeight) {
        this.mHeight = headerHeight;
        //是否采用模糊模式（是否明确显示进度） 为假时，明确显示进度
        progressBar.setIndeterminate(false);
    }

    @Override
    public void onMove(boolean isComplete, boolean automatic, int moved) {
        if (!isComplete) {
            ivArrow.setVisibility(VISIBLE);
            progressBar.setVisibility(GONE);
            ivSuccess.setVisibility(GONE);
            if (moved <= mHeight) {
                if (rotated) {
                    ivArrow.clearAnimation();
                    ivArrow.startAnimation(rotateDown);
                    rotated = false;
                }

                tvRefresh.setText("下拉刷新");
            } else {
                tvRefresh.setText("松开刷新");
                if (!rotated) {
                    ivArrow.clearAnimation();
                    ivArrow.startAnimation(rotateUp);
                    rotated = true;
                }
            }
        }
    }

    @Override
    public void onRefresh() {
        ivSuccess.setVisibility(GONE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
        tvRefresh.setText("正在加载");
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        rotated = false;
        ivSuccess.setVisibility(VISIBLE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
        tvRefresh.setText("加载完成");
    }

    @Override
    public void onReset() {
        rotated = false;
        ivSuccess.setVisibility(GONE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
    }
}
