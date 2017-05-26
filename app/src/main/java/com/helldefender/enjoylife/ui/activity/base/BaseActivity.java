package com.helldefender.enjoylife.ui.activity.base;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.StatusLayoutManager;
import com.helldefender.enjoylife.app.MyApplication;
import com.helldefender.enjoylife.inject.component.ActivityComponent;
import com.helldefender.enjoylife.inject.component.DaggerActivityComponent;
import com.helldefender.enjoylife.inject.module.ActivityModule;
import com.helldefender.enjoylife.listener.PermissionListener;
import com.helldefender.enjoylife.presenter.base.BasePresenter;
import com.helldefender.enjoylife.ui.fragment.base.BaseFragment;
import com.helldefender.enjoylife.utils.ToolbarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Helldefender on 2017/2/5.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    protected T mPresenter;

    protected ActivityComponent mActivityComponent;

    private static PermissionListener mListener;

    protected Toolbar toolbar;

    protected StatusLayoutManager statusLayoutManager;

    protected LinearLayout llBaseActivity;

    public abstract int getLayoutId();

    public abstract int getEmptyLayoutId();

    public abstract void initInject();

    public abstract void initPresenter();

//    public static void start(Context context,Class<?> cs) {
//        start(context,cs ,null);
//    }
//
//    public static void start(Context context, Class<?> cs,Intent extras) {
//        Intent intent = new Intent();
//        intent.setClass(context, cs);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        if (extras != null) {
//            intent.putExtras(extras);
//        }
//        context.startActivity(intent);
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //setContentView(R.layout.activity_base);

//        llBaseActivity = (LinearLayout) findViewById(R.id.ll_base_activity);
//        statusLayoutManager = new StatusLayoutManager.Builder(this)
//                .contentView(getLayoutId())
//                .loadingView(R.layout.layout_status_loading)
//                .emptyDataView(getEmptyLayoutId())
//                .errorView(R.layout.layout_status_error)
//                .netWorkErrorView(R.layout.layout_status_net_error)
//                .retryViewId(R.layout.layout_status_retry)
////                .emptyDataRetryViewId()
////                .errorRetryViewId()
////                .netWorkErrorRetryViewId()
//                .emptyDataIconImageId(R.id.tv_status_empty)
//                .emptyDataTextTipId(R.id.img_status_empty)
//                .onRetryListener(new OnRetryListener() {
//                    @Override
//                    public void onRetry() {
//                        statusLayoutManager.showLoading();
//                        //请求加载数据
//                    }
//                })
//                //view显示隐藏监听
//                .onShowHideViewListener(new OnShowHideViewListener() {
//                    @Override
//                    public void onShowView(View view, int id) {
//                        //该view显示
//                    }
//
//                    @Override
//                    public void onHideView(View view, int id) {
//                        //该view隐藏
//                    }
//                })
//                .build();
//
//        llBaseActivity.addView(statusLayoutManager.getRootLayout(), 0);
//
//        statusLayoutManager.showContent();

        initActivityComponent();
        initInject();

        ButterKnife.bind(this);

        initPresenter();

        if (mPresenter != null) {
            mPresenter.onCreate();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((MyApplication) getApplication()).getApplicationComponent())
                .baseFragmentComponent(((MyApplication) getApplication()).getBaseFragmentComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    protected void handleStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View view = getWindow().getDecorView();
            int toolbarUtil = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            view.setSystemUiVisibility(toolbarUtil);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams layoutparams = getWindow().getAttributes();
            layoutparams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | layoutparams.flags);
        }
    }

    protected void setUpStatusBar(int colorResourceId) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(colorResourceId));
    }

    public void setToolBar(int toolBarId, ToolbarUtil toolbarUtil) {
        toolbar = (Toolbar) findViewById(toolBarId);

        if (toolbarUtil.titleId != 0) {
            toolbar.setTitle(toolbarUtil.titleId);
        }

        if (!TextUtils.isEmpty(toolbarUtil.titleString)) {
            toolbar.setTitle(toolbarUtil.titleString);
        }

        if (toolbarUtil.logoId != 0) {
            toolbar.setLogo(toolbarUtil.logoId);
        }

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && toolbarUtil.isShowNavigationIcon) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (toolbarUtil.isNeedNavigate) {
            toolbar.setNavigationIcon(toolbarUtil.navigateId);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    public void setToolBar(int toolbarId, int titleId, int logoId) {
        toolbar = (Toolbar) findViewById(toolbarId);
        toolbar.setTitle(titleId);
        toolbar.setLogo(logoId);
        setSupportActionBar(toolbar);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public int getToolBarHeight() {
        if (toolbar != null) {
            return toolbar.getHeight();
        }
        return 0;
    }

    public void addFragment(BaseFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    public void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    protected int getFragmentContentId() {
        return R.id.coordinatorLayout_main_fragmentContainer;
    }

    public static void requestRuntimePermission(BaseActivity baseActivity, String[] permissions, PermissionListener listener) {
        //Activity topActivity = ActivityCollector.getTopActivity();
        if (baseActivity == null) {
            return;
        }
        mListener = listener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(baseActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(baseActivity, permissionList.toArray(new String[permissionList.size()]), 1);
        } else {
            mListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {
                        mListener.onGranted();
                    } else {
                        mListener.onDenied(deniedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onNavigateUpClicked();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onNavigateUpClicked() {
        onBackPressed();
    }
//    protected <T extends View> T findView(int resId) {
//        return (T) (getView().findViewById(resId));
//    }

//    protected abstract void initInjector();
//
//    protected abstract void initViews();
}
