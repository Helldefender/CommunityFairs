package com.helldefender.communityfairs.modules.community.detail;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.github.clans.fab.FloatingActionMenu;
import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.app.BaseActivity;
import com.helldefender.communityfairs.app.ViewModelFactory;
import com.helldefender.communityfairs.databinding.ActivityNewsDetailBinding;
import com.helldefender.communityfairs.modules.community.detail.apply.ApplyActivity;
import com.helldefender.communityfairs.modules.community.detail.comment.CommentActivity;
import com.helldefender.communityfairs.utils.ToolbarUtil;


/**
 * a
 * Created by Helldefender on 2017/2/7.
 */

public class NewsDetailActivity extends BaseActivity<ActivityNewsDetailBinding, NewsDetailViewModel> {

    private CollapsingToolbarLayoutState state;

    Boolean isKeyboardOpen = false;

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected int getVariableId() {
        return BR.viewModel;
    }

    @Override
    protected NewsDetailViewModel getViewModel() {
        return ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(NewsDetailViewModel.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //toolbarUtil.titleString = "标题";
        //toolbarUtil.isNeedNavigate = false;
        //mToolBar.setTitleTextColor(Color.RED);

        ToolbarUtil toolbarUtil = new ToolbarUtil();
        setToolBar(R.id.toolbar_news_detail, toolbarUtil);
        setAppBar();
        setFloatingActionMenu();
    }

    private void setAppBar() {
        binding.appBarLNewsDetail.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        binding.collapsingToolBarLNewsDetail.setTitle("展开状态");//设置title为EXPANDED
                        binding.collapsingToolBarLNewsDetail.setExpandedTitleColor(Color.parseColor("#FFFFFF"));

                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        binding.collapsingToolBarLNewsDetail.setTitle("折叠状态");//设置title不显示
                        //mTitleText.setVisibility(View.VISIBLE);//隐藏播放按钮
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                            //mTitleText.setVisibility(View.GONE);//由折叠变为中间状态时隐藏播放按钮
                        }
                        binding.collapsingToolBarLNewsDetail.setTitle("中间状态");//设置title为INTERNEDIATE
                        binding.collapsingToolBarLNewsDetail.setCollapsedTitleTextColor(Color.parseColor("#FFFFFF"));
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });
    }

    public void setFloatingActionMenu() {
        final FloatingActionMenu floatingActionMenu = binding.fabMenu;
        floatingActionMenu.setClosedOnTouchOutside(true);
        AnimatorSet set = new AnimatorSet();

        ObjectAnimator scaleOutX = ObjectAnimator.ofFloat(floatingActionMenu.getMenuIconView(), "scaleX", 1.0f, 0.2f);
        ObjectAnimator scaleOutY = ObjectAnimator.ofFloat(floatingActionMenu.getMenuIconView(), "scaleY", 1.0f, 0.2f);

        ObjectAnimator scaleInX = ObjectAnimator.ofFloat(floatingActionMenu.getMenuIconView(), "scaleX", 0.2f, 1.0f);
        ObjectAnimator scaleInY = ObjectAnimator.ofFloat(floatingActionMenu.getMenuIconView(), "scaleY", 0.2f, 1.0f);

        scaleOutX.setDuration(50);
        scaleOutY.setDuration(50);

        scaleInX.setDuration(150);
        scaleInY.setDuration(150);

        scaleInX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                floatingActionMenu.getMenuIconView().setImageResource(floatingActionMenu.isOpened()
                        ? R.drawable.ic_file_upload_white_24dp : R.drawable.ic_close_white_24dp);
            }
        });

        set.play(scaleOutX).with(scaleOutY);
        set.play(scaleInX).with(scaleInY).after(scaleOutX);
        set.setInterpolator(new OvershootInterpolator(2));

        floatingActionMenu.setIconToggleAnimatorSet(set);

        binding.fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionMenu.close(true);
            }
        });

        binding.fabComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CommentActivity.class);
                floatingActionMenu.close(true);
            }
        });

        binding.fabApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ApplyActivity.class);
                floatingActionMenu.close(true);
            }
        });
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Boolean shouldDispatchTouchEvent = false;
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            View v = getCurrentFocus();
//            if (isShouldHideInput(v, ev)) {
//                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                if (inputMethodManager != null && v != null) {
//                    if (isKeyboardOpen) {
//                        shouldDispatchTouchEvent = true;
//                    }
//                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                }
//            }
//            return shouldDispatchTouchEvent || super.dispatchTouchEvent(ev);
//        }
//        // 必不可少，否则所有的组件都不会有TouchEvent了
//        return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);
//    }
//
//    public boolean isShouldHideInput(View view, MotionEvent event) {
//        if (view != null && (view instanceof EditText)) {
//            int[] leftTop = {0, 0};
//            view.getLocationInWindow(leftTop);
//            //获取输入框当前的location位置
//            int left = leftTop[0];
//            int top = leftTop[1];
//            int bottom = top + view.getHeight();
//            int right = left + view.getWidth();
//            if (event.getX() > left && event.getX() < right
//                    && event.getY() > top && event.getY() < bottom) {
//                return false;
//            }
//        }
//
//        return true;
//    }
}
