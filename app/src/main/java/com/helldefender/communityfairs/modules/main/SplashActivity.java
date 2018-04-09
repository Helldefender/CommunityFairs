package com.helldefender.communityfairs.modules.main;

import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.app.BaseActivity;
import com.helldefender.communityfairs.modules.user.login.LoginActivity;
import com.helldefender.communityfairs.utils.PreferenceUtil;

/**
 * Created by Helldefender on 2017/2/21.
 */

public class SplashActivity extends BaseActivity {

    private boolean customSplash = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

    @Override
    protected ViewModel getViewModel() {
        return null;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

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
                startActivity(LoginActivity.class);
                finish();
//                if (canAutoLogin()) {
//                    startActivity(SplashActivity.class);
//                    //MainActivity.start(SplashActivity.this,null); // TODO: 2017/12/30
//                    finish();
//                } else {
//                    startActivity(MainActivity.class);
//                    //LoginActivity.start(SplashActivity.this, null);
//                    //startActivity(LoginActivity.class); // TODO: 2017/12/30
//                    finish();
//                }
            }
        };

        if (customSplash) {
            new Handler().postDelayed(runnable, 1000);
        } else {
            runnable.run();
        }
    }
}
