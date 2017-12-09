package com.helldefender.communityfairs.widget.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Helldefender on 2017/5/26.
 */

public class MessageItemDecoration extends RecyclerView.ItemDecoration {
//
//    private final Drawable mDivider;
//
//    private final int mSize;
//
//    private final int mOrientation;
//
//    public MessageItemDecoration(Resources resources, @ColorRes int color,
//                                 @DimenRes int size, int orientation) {
//        mDivider = new ColorDrawable(resources.getColor(color));
//        mSize = resources.getDimensionPixelSize(size);
//        mOrientation = orientation;
//    }
//
//    @Override
//    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        int left;
//        int right;
//        int top;
//        int bottom;
//        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
//            top = parent.getPaddingTop();
//            bottom = parent.getHeight() - parent.getPaddingBottom();
//            final int childCount = parent.getChildCount();
//            for (int i = 0; i < childCount - 1; i++) {
//                final View child = parent.getChildAt(i);
//                final RecyclerView.LayoutParams params =
//                        (RecyclerView.LayoutParams) child.getLayoutParams();
//                left = child.getRight() + params.rightMargin;
//                right = left + mSize;
//                mDivider.setBounds(left, top, right, bottom);
//                mDivider.draw(c);
//            }
//        } else {
//            left = parent.getPaddingLeft();
//            right = parent.getWidth() - parent.getPaddingRight();
//            final int childCount = parent.getChildCount();
//            for (int i = 0; i < childCount - 1; i++) {
//                final View child = parent.getChildAt(i);
//                final RecyclerView.LayoutParams params =
//                        (RecyclerView.LayoutParams) child.getLayoutParams();
//                top = child.getBottom() + params.bottomMargin;
//                bottom = top + mSize;
//                mDivider.setBounds(left, top, right, bottom);
//                mDivider.draw(c);
//            }
//        }
//    }
//
//    @Override
//    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
//                               RecyclerView.State state) {
//        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
//            outRect.set(0, 0, mSize, 0);
//        } else {
//            outRect.set(0, 0, 0, mSize);
//        }
//    }


    public MessageItemDecoration() {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom=20;
    }
}
