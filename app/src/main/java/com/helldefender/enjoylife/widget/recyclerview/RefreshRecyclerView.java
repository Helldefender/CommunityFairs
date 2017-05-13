package com.helldefender.enjoylife.widget.recyclerview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.listener.OnLoadMoreListener;
import com.helldefender.enjoylife.listener.OnRefreshListener;
import com.helldefender.enjoylife.listener.RefreshStatusListener;

/**
 * Created by Helldefender on 2017/4/19.
 */

public class RefreshRecyclerView extends RecyclerView {

    private static final int STATUS_DEFAULT = 0;

    private static final int STATUS_SWIPING_TO_REFRESH = 1;

    private static final int STATUS_RELEASE_TO_REFRESH = 2;

    private static final int STATUS_REFRESHING = 3;

    private int status;

    private boolean mIsAutoRefreshing;

    private boolean mRefreshEnabled;

    private boolean mLoadMoreEnabled;

    private int mRefreshFinalMoveOffset;

    private OnRefreshListener onRefreshListener;

    private OnLoadMoreListener onLoadMoreListener;

    private RefreshHeaderLayout refreshHeaderLayout;

    private FrameLayout loadMoreFooterLayout;

    private View refreshHeaderView;

    private View loadMoreFooterView;

    private int mActivePointerId = -1;

    private int lastTouchY = 0;

    private ValueAnimator mScrollAnimator;

    private ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            final int height = (Integer) animation.getAnimatedValue();
            setRefreshHeaderContainerHeight(height);
            switch (status) {
                case STATUS_SWIPING_TO_REFRESH: {
                    refreshStatusListener.onMove(false, true, height);
                }
                break;

                case STATUS_RELEASE_TO_REFRESH: {
                    refreshStatusListener.onMove(false, true, height);
                }
                break;

                case STATUS_REFRESHING: {
                    refreshStatusListener.onMove(true, true, height);
                }
                break;
            }
        }
    };

    private Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            switch (status) {
                case STATUS_SWIPING_TO_REFRESH: {
                    if (mIsAutoRefreshing) {
                        refreshHeaderLayout.getLayoutParams().height = refreshHeaderView.getMeasuredHeight();
                        refreshHeaderLayout.requestLayout();
                        setStatus(STATUS_REFRESHING);
                        if (onRefreshListener != null) {
                            onRefreshListener.onRefresh();
                            refreshStatusListener.onRefresh();
                        }
                    } else {
                        refreshHeaderLayout.getLayoutParams().height = 0;
                        refreshHeaderLayout.requestLayout();
                        setStatus(STATUS_DEFAULT);
                    }
                }
                break;

                case STATUS_RELEASE_TO_REFRESH: {
                    refreshHeaderLayout.getLayoutParams().height = refreshHeaderView.getMeasuredHeight();
                    refreshHeaderLayout.requestLayout();
                    setStatus(STATUS_REFRESHING);
                    if (onRefreshListener != null) {
                        onRefreshListener.onRefresh();
                        refreshStatusListener.onRefresh();
                    }
                }
                break;

                case STATUS_REFRESHING: {
                    mIsAutoRefreshing = false;
                    refreshHeaderLayout.getLayoutParams().height = 0;
                    refreshHeaderLayout.requestLayout();
                    setStatus(STATUS_DEFAULT);
                    refreshStatusListener.onReset();
                }
                break;
            }
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    private RefreshStatusListener refreshStatusListener = new RefreshStatusListener() {
        @Override
        public void onStart(int headerHeight) {
            if (refreshHeaderView != null && refreshHeaderView instanceof RefreshStatusListener) {
                RefreshStatusListener refreshStatusListener = (RefreshStatusListener) refreshHeaderView;
                refreshStatusListener.onStart(headerHeight);
            }
        }

        @Override
        public void onMove(boolean finished, boolean automatic, int moved) {
            if (refreshHeaderView != null && refreshHeaderView instanceof RefreshStatusListener) {
                RefreshStatusListener refreshStatusListener = (RefreshStatusListener) refreshHeaderView;
                refreshStatusListener.onMove(finished, automatic, moved);
            }
        }

        @Override
        public void onRefresh() {
            if (refreshHeaderView != null && refreshHeaderView instanceof RefreshStatusListener) {
                RefreshStatusListener refreshStatusListener = (RefreshStatusListener) refreshHeaderView;
                refreshStatusListener.onRefresh();
            }
        }

        @Override
        public void onRelease() {
            if (refreshHeaderView != null && refreshHeaderView instanceof RefreshStatusListener) {
                RefreshStatusListener refreshStatusListener = (RefreshStatusListener) refreshHeaderView;
                refreshStatusListener.onRelease();
            }
        }

        @Override
        public void onComplete() {
            if (refreshHeaderView != null && refreshHeaderView instanceof RefreshStatusListener) {
                RefreshStatusListener refreshStatusListener = (RefreshStatusListener) refreshHeaderView;
                refreshStatusListener.onComplete();
            }
        }

        @Override
        public void onReset() {
            if (refreshHeaderView != null && refreshHeaderView instanceof RefreshStatusListener) {
                RefreshStatusListener trigger = (RefreshStatusListener) refreshHeaderView;
                trigger.onReset();
            }
        }
    };

    private OnLoadMoreScrollListener mOnLoadMoreScrollListener = new OnLoadMoreScrollListener() {
        @Override
        public void onLoadMore(RecyclerView recyclerView) {
            if (onLoadMoreListener != null && status == STATUS_DEFAULT) {
                onLoadMoreListener.onLoadMore();
            }
        }
    };

    public RefreshRecyclerView(Context context) {
        this(context, null);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RefreshRecyclerView, defStyle, 0);
        int refreshHeaderLayoutRes = -1;
        int loadMoreFooterLayoutRes = -1;
        int refreshFinalMoveOffset = -1;
        boolean refreshEnabled;
        boolean loadMoreEnabled;

        try {
            refreshEnabled = a.getBoolean(R.styleable.RefreshRecyclerView_refreshEnabled, false);
            loadMoreEnabled = a.getBoolean(R.styleable.RefreshRecyclerView_loadMoreEnabled, false);
            refreshHeaderLayoutRes = a.getResourceId(R.styleable.RefreshRecyclerView_refreshHeaderLayout, -1);
            loadMoreFooterLayoutRes = a.getResourceId(R.styleable.RefreshRecyclerView_loadMoreFooterLayout, -1);
            refreshFinalMoveOffset = a.getDimensionPixelOffset(R.styleable.RefreshRecyclerView_refreshFinalMoveOffset, -1);
        } finally {
            a.recycle();
        }

        setRefreshEnabled(refreshEnabled);

        setLoadMoreEnabled(loadMoreEnabled);

        if (refreshHeaderLayoutRes != -1) {
            setRefreshHeaderView(refreshHeaderLayoutRes);
        }
        if (loadMoreFooterLayoutRes != -1) {
            setLoadMoreFooterView(loadMoreFooterLayoutRes);
        }
        if (refreshFinalMoveOffset != -1) {
            setRefreshFinalMoveOffset(refreshFinalMoveOffset);
        }

        setStatus(STATUS_DEFAULT);
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setRefreshEnabled(boolean enabled) {
        this.mRefreshEnabled = enabled;
    }

    public void setLoadMoreEnabled(boolean enabled) {
        this.mLoadMoreEnabled = enabled;
        if (mLoadMoreEnabled) {
            removeOnScrollListener(mOnLoadMoreScrollListener);
            addOnScrollListener(mOnLoadMoreScrollListener);
        } else {
            removeOnScrollListener(mOnLoadMoreScrollListener);
        }
    }

    public void setRefreshFinalMoveOffset(int refreshFinalMoveOffset) {
        this.mRefreshFinalMoveOffset = refreshFinalMoveOffset;
    }

    public void setRefreshHeaderView(@LayoutRes int refreshHeaderLayoutRes) {
        ensureRefreshHeaderContainer();
        final View refreshHeader = LayoutInflater.from(getContext()).inflate(refreshHeaderLayoutRes, refreshHeaderLayout, false);
        if (refreshHeader != null) {
            setRefreshHeaderView(refreshHeader);
        }
    }

    public void setRefreshHeaderView(View refreshHeader) {
        if (!(refreshHeader instanceof RefreshStatusListener)) {
            throw new ClassCastException("顶部View需继承自RefreshStatusListener");
        }

        if (refreshHeaderView != null) {
            removeRefreshHeaderView();
        }
        if (refreshHeaderView != refreshHeader) {
            this.refreshHeaderView = refreshHeader;
            ensureRefreshHeaderContainer();
            refreshHeaderLayout.addView(refreshHeader);
        }
    }

    public void setLoadMoreFooterView(@LayoutRes int loadMoreFooterLayoutRes) {
        ensureLoadMoreFooterContainer();
        final View loadMoreFooter = LayoutInflater.from(getContext()).inflate(loadMoreFooterLayoutRes, loadMoreFooterLayout, false);
        if (loadMoreFooter != null) {
            setLoadMoreFooterView(loadMoreFooter);
        }
    }

    public void setLoadMoreFooterView(View loadMoreFooter) {
        if (loadMoreFooterView != null) {
            removeLoadMoreFooterView();
        }
        if (loadMoreFooterView != loadMoreFooter) {
            this.loadMoreFooterView = loadMoreFooter;
            ensureLoadMoreFooterContainer();
            loadMoreFooterLayout.addView(loadMoreFooter);
        }
    }

    private void ensureRefreshHeaderContainer() {
        if (refreshHeaderLayout == null) {
            refreshHeaderLayout = new RefreshHeaderLayout(getContext());
            refreshHeaderLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
        }
    }

    private void ensureLoadMoreFooterContainer() {
        if (loadMoreFooterLayout == null) {
            loadMoreFooterLayout = new FrameLayout(getContext());
            loadMoreFooterLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    private void removeRefreshHeaderView() {
        if (refreshHeaderLayout != null) {
            refreshHeaderLayout.removeView(refreshHeaderView);
        }
    }

    private void removeLoadMoreFooterView() {
        if (loadMoreFooterLayout != null) {
            loadMoreFooterLayout.removeView(loadMoreFooterView);
        }
    }

    public View getRefreshHeaderView() {
        return refreshHeaderView;
    }

    public View getLoadMoreFooterView() {
        return loadMoreFooterView;
    }

    public RefreshHeaderLayout getRefreshHeaderLayout() {
        return refreshHeaderLayout;
    }

    public FrameLayout getLoadMoreFooterLayout() {
        return loadMoreFooterLayout;
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if (refreshHeaderView != null) {
            if (refreshHeaderView.getMeasuredHeight() > mRefreshFinalMoveOffset) {
                mRefreshFinalMoveOffset = 0;
            }
        }
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent e) {
//        final int action = MotionEventCompat.getActionMasked(e);
//        final int actionIndex = MotionEventCompat.getActionIndex(e);
//        switch (action) {
//            case MotionEvent.ACTION_DOWN: {
//                mActivePointerId = MotionEventCompat.getPointerId(e, 0);
//                lastTouchY = (int) (MotionEventCompat.getY(e, actionIndex) + 0.5f);
//            }
//            break;
//
//            case MotionEvent.ACTION_POINTER_DOWN: {
//                mActivePointerId = MotionEventCompat.getPointerId(e, actionIndex);
//                lastTouchY = (int) (MotionEventCompat.getY(e, actionIndex) + 0.5f);
//            }
//            break;
//
//            case MotionEventCompat.ACTION_POINTER_UP: {
//                //onPointerUp(e);
//            }
//            break;
//        }
//
//        return super.onInterceptTouchEvent(e);
//    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN: {
//                final int index = MotionEventCompat.getActionIndex(e);
//                mActivePointerId = MotionEventCompat.getPointerId(e, 0);
//                lastTouchY = getMotionEventY(e, index);
                lastTouchY = (int) e.getY();
            }
            break;
            case MotionEvent.ACTION_MOVE: {
//                final int index = MotionEventCompat.findPointerIndex(e, mActivePointerId);
//                final int y = getMotionEventY(e, index);
//                final int dy = y - lastTouchY;
//                lastTouchY = y;

                int y = (int) e.getY();
                int dy = y - lastTouchY;
                lastTouchY = y;

                boolean refreshCondition = mRefreshEnabled && refreshHeaderView != null && isFingerDragging() && canTriggerRefresh();

                if (refreshCondition) {
                    int refreshHeaderViewHeight = refreshHeaderView.getMeasuredHeight();
                    int refreshHeaderContainerHeight = refreshHeaderLayout.getMeasuredHeight();

                    if (dy > 0 && status == STATUS_DEFAULT) {
                        setStatus(STATUS_SWIPING_TO_REFRESH);
                        refreshStatusListener.onStart(refreshHeaderViewHeight);
                    } else if (dy < 0) {
                        if (status == STATUS_SWIPING_TO_REFRESH && refreshHeaderContainerHeight <= 0) {
                            setStatus(STATUS_DEFAULT);
                        }
                        if (status == STATUS_DEFAULT) {
                            break;
                        }
                    }
                    if (status == STATUS_SWIPING_TO_REFRESH || status == STATUS_RELEASE_TO_REFRESH) {
                        if (refreshHeaderContainerHeight >= refreshHeaderViewHeight) {
                            setStatus(STATUS_RELEASE_TO_REFRESH);
                        } else {
                            setStatus(STATUS_SWIPING_TO_REFRESH);
                        }
                        move(dy);
                        //fingerMove(dy);
                        return true;
                    }
                }

            }
            break;
            case MotionEvent.ACTION_UP:
                onFingerUpStartAnimating();
                break;
        }
        return super.onTouchEvent(e);
    }


    private int getMotionEventY(MotionEvent e, int pointerIndex) {
        return (int) (MotionEventCompat.getY(e, pointerIndex) + 0.5f);
    }

    private boolean isFingerDragging() {
        return getScrollState() == SCROLL_STATE_DRAGGING;
    }

    public boolean canTriggerRefresh() {
        final Adapter adapter = getAdapter();
        if (adapter == null || adapter.getItemCount() <= 0) {
            return true;
        }
        View firstChild = getChildAt(0);
        int position = getChildLayoutPosition(firstChild);
        if (position == 0) {
            if (firstChild.getTop() == refreshHeaderLayout.getTop()) {
                return true;
            }
        }
        return false;
    }

    private void setStatus(int status) {
        this.status = status;
    }

    private void onFingerUpStartAnimating() {
        if (status == STATUS_RELEASE_TO_REFRESH) {
            startScrollReleaseStatusToRefreshingStatus();
        } else if (status == STATUS_SWIPING_TO_REFRESH) {
            startScrollSwipingToRefreshStatusToDefaultStatus();
        }
    }

    private void fingerMove(int dy) {
        int ratioDy = (int) (dy * 0.5f + 0.5f);
        int offset = refreshHeaderLayout.getMeasuredHeight();
        int finalDragOffset = mRefreshFinalMoveOffset;

        int nextOffset = offset + ratioDy;
        if (finalDragOffset > 0) {
            if (nextOffset > finalDragOffset) {
                ratioDy = finalDragOffset - offset;
            }
        }

        if (nextOffset < 0) {
            ratioDy = -offset;
        }
        move(ratioDy);
    }

    private void move(int dy) {
        if (dy != 0) {
            int height = refreshHeaderLayout.getMeasuredHeight() + dy;
            setRefreshHeaderContainerHeight(height);
            refreshStatusListener.onMove(false, false, height);
        }
    }

    private void setRefreshHeaderContainerHeight(int height) {
        refreshHeaderLayout.getLayoutParams().height = height;
        refreshHeaderLayout.requestLayout();
    }

    private void startScrollDefaultStatusToRefreshingStatus() {
        refreshStatusListener.onStart(refreshHeaderView.getMeasuredHeight());

        int targetHeight = refreshHeaderView.getMeasuredHeight();
        int currentHeight = refreshHeaderLayout.getMeasuredHeight();
        startScrollAnimation(400, new AccelerateInterpolator(), currentHeight, targetHeight);
    }

    private void startScrollSwipingToRefreshStatusToDefaultStatus() {
        final int targetHeight = 0;
        final int currentHeight = refreshHeaderLayout.getMeasuredHeight();
        startScrollAnimation(300, new DecelerateInterpolator(), currentHeight, targetHeight);
    }

    private void startScrollReleaseStatusToRefreshingStatus() {
        refreshStatusListener.onRelease();

        final int targetHeight = refreshHeaderView.getMeasuredHeight();
        final int currentHeight = refreshHeaderLayout.getMeasuredHeight();
        startScrollAnimation(300, new DecelerateInterpolator(), currentHeight, targetHeight);
    }

    private void startScrollRefreshingStatusToDefaultStatus() {
        refreshStatusListener.onComplete();

        final int targetHeight = 0;
        final int currentHeight = refreshHeaderLayout.getMeasuredHeight();
        startScrollAnimation(400, new DecelerateInterpolator(), currentHeight, targetHeight);
    }

    private void startScrollAnimation(final int time, final Interpolator interpolator, int value, int toValue) {
        if (mScrollAnimator == null) {
            mScrollAnimator = new ValueAnimator();
        }

        mScrollAnimator.removeAllUpdateListeners();
        mScrollAnimator.removeAllListeners();
        mScrollAnimator.cancel();

        mScrollAnimator.setIntValues(value, toValue);
        mScrollAnimator.setDuration(time);
        mScrollAnimator.setInterpolator(interpolator);
        mScrollAnimator.addUpdateListener(mAnimatorUpdateListener);
        mScrollAnimator.addListener(animatorListener);
        mScrollAnimator.start();
    }

    public void setRefreshing(boolean refreshing) {
        if (status == STATUS_DEFAULT && refreshing) {
            this.mIsAutoRefreshing = true;
            setStatus(STATUS_SWIPING_TO_REFRESH);
            startScrollDefaultStatusToRefreshingStatus();
        } else if (status == STATUS_REFRESHING && !refreshing) {
            this.mIsAutoRefreshing = false;
            startScrollRefreshingStatusToDefaultStatus();
        } else {
            this.mIsAutoRefreshing = false;
        }
    }
}
