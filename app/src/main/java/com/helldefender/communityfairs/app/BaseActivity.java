package com.helldefender.communityfairs.app;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.helldefender.communityfairs.utils.ToolbarUtil;

/**
 * Created by Helldefender on 2017/2/5.
 */

public abstract class BaseActivity<V extends ViewDataBinding, VM extends ViewModel> extends AppCompatActivity {

    protected V binding;

    protected VM viewModel;

    protected Toolbar toolbar;

    protected abstract int getLayoutId();

    protected abstract int getVariableId();

    protected abstract VM getViewModel();

    //protected abstract void widgetClick(View view);

    protected void setWidgetListener() {
    }


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

        if (getViewModel() != null) {
            binding = DataBindingUtil.setContentView(this, getLayoutId());
            binding.setVariable(getVariableId(), viewModel = getViewModel());
        }

        handleStatusBar();


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


    public void addFragment(BaseFragment fragment) {
//        if (fragment != null) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
//                    .addToBackStack(fragment.getClass().getSimpleName())
//                    .commitAllowingStateLoss();
//        }
    }

    public void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    /**
     * Activity间跳转
     *
     * @param cls
     */
    public void startActivity(Class<?> cls) {
        startActivity(new Intent(BaseActivity.this, cls));
    }

    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void startActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivityForResult(intent, requestCode);
    }

    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
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


    protected String getStringRes(int resId) {
        return getResources().getString(resId);
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
