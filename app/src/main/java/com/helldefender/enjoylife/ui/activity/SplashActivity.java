package com.helldefender.enjoylife.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.modules.login.LoginActivity;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;
import com.helldefender.enjoylife.utils.PreferenceUtil;

/**
 * Created by Helldefender on 2017/2/21.
 */

public class SplashActivity extends BaseActivity {

    private static boolean firstEnter = true;

    private boolean customSplash = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initInject() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void widgetClick(View view) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_welcome);

        handleStatusBar();

        showSplashView();
    }

    private boolean canAutoLogin() {
        String account = PreferenceUtil.getUserAccount();
        String token = PreferenceUtil.getUserToken();
        return !TextUtils.isEmpty(account) && !TextUtils.isEmpty(token);
    }

    private void showSplashView() {
        getWindow().setBackgroundDrawableResource(R.drawable.splash);
        customSplash = true;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (canAutoLogin()) {
                    MainActivity.start(SplashActivity.this,null);
                    finish();
                } else {
                    //LoginActivity.start(SplashActivity.this, null);
                    startActivity(LoginActivity.class);
                    finish();
                }
            }
        };
        if (customSplash) {
            new Handler().postDelayed(runnable, 1000);
        } else {
            runnable.run();
        }
    }
}
