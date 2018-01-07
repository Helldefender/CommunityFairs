package com.helldefender.communityfairs.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TabWidget;
import android.widget.TextView;

import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.utils.DisplayUtil;

/**
 * Created by Helldefender on 2017/5/15.
 */

public class BadgeView extends TextView {

    private boolean mHideOnNull = true;

    public BadgeView(Context context) {
        this(context, null);
    }

    public BadgeView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public BadgeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();
    }

    private void init() {
        if (!(getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            FrameLayout.LayoutParams layoutParams =
                    new FrameLayout.LayoutParams(
                            android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                            android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                            Gravity.RIGHT | Gravity.TOP);
            setLayoutParams(layoutParams);
        }

        setTextColor(Color.WHITE);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
        setPadding(DisplayUtil.dp2px(5), DisplayUtil.dp2px(1), DisplayUtil.dp2px(5), DisplayUtil.dp2px(1));

        setBackground(9, getResources().getColor(R.color.colorAccentBlue));

        setGravity(Gravity.CENTER);

        setHideOnNull(true);
        setBadgeCount(0);
    }

    public void setBackground(int dpRadius, int badgeColor) {
        int radius = DisplayUtil.dp2px(dpRadius);
        float[] radiusArray = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};

        RoundRectShape roundRect = new RoundRectShape(radiusArray, null, null);
        ShapeDrawable bgDrawable = new ShapeDrawable(roundRect);
        bgDrawable.getPaint().setColor(badgeColor);
        setBackground(bgDrawable);
    }

    public boolean isHideOnNull() {
        return mHideOnNull;
    }

    public void setHideOnNull(boolean hideOnNull) {
        mHideOnNull = hideOnNull;
        setText(getText());
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (isHideOnNull() && (text == null || text.toString().equalsIgnoreCase("0"))) {
            setVisibility(View.GONE);
        } else {
            setVisibility(View.VISIBLE);
        }
        super.setText(text, type);
    }

    public void setBadgeCount(int count) {
        setText(String.valueOf(count));
    }

    public void setBadgeGravity(int gravity) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
        params.gravity = gravity;
        setLayoutParams(params);
    }

    public void setBadgeMargin(int leftDipMargin, int topDipMargin, int rightDipMargin, int bottomDipMargin) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getLayoutParams();
        params.leftMargin = DisplayUtil.dp2px(leftDipMargin);
        params.topMargin = DisplayUtil.dp2px(topDipMargin);
        params.rightMargin = DisplayUtil.dp2px(rightDipMargin);
        params.bottomMargin = DisplayUtil.dp2px(bottomDipMargin);
        setLayoutParams(params);
    }

    public void setTargetView(TabWidget target, int tabIndex) {
        View tabView = target.getChildTabViewAt(tabIndex);
        setTargetView(tabView);
    }

    public void setTargetView(View target) {
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }

        if (target == null) {
            return;
        }

        if (target.getParent() instanceof FrameLayout) {
            ((FrameLayout) target.getParent()).addView(this);

        } else if (target.getParent() instanceof ViewGroup) {
            ViewGroup parentContainer = (ViewGroup) target.getParent();
            int groupIndex = parentContainer.indexOfChild(target);
            parentContainer.removeView(target);

            FrameLayout badgeContainer = new FrameLayout(getContext());
            ViewGroup.LayoutParams parentLayoutParams = target.getLayoutParams();

            badgeContainer.setLayoutParams(parentLayoutParams);
            target.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            parentContainer.addView(badgeContainer, groupIndex, parentLayoutParams);
            badgeContainer.addView(target);

            badgeContainer.addView(this);
        } else if (target.getParent() == null) {
            Log.e(getClass().getSimpleName(), "ParentView is needed");
        }

    }
}
