package com.helldefender.enjoylife.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.helldefender.enjoylife.R;
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
        return 0;
    }

    @Override
    public int getEmptyLayoutId() {
        return 0;
    }

    @Override
    public void initInject() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        handleStatusBar();

        showSplashView();
    }

    private boolean canAutoLogin() {
        String account = PreferenceUtil.getUserAccount();
        String token = PreferenceUtil.getUserToken();
        return !TextUtils.isEmpty(account) && !TextUtils.isEmpty(token);
    }

    private void showSplashView() {
        getWindow().setBackgroundDrawableResource(R.mipmap.splash_bg);
        customSplash = true;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (canAutoLogin()) {
                    MainActivity.start(SplashActivity.this,null);
                    finish();
                } else {
                    LoginActivity.start(SplashActivity.this, null);
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
