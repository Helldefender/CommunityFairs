package com.acemurder.u_lab.modules.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.acemurder.u_lab.R;
import com.acemurder.u_lab.base.BaseActivity;
import com.acemurder.u_lab.base.BaseFragment;
import com.acemurder.u_lab.modules.user.login.LoginActivity;
import com.acemurder.u_lab.util.PreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Helldefender on 2017/6/8.
 */

public class AccountManagerActivity extends BaseActivity {

    @BindView(R.id.tv_setting_account_title)
    TextView mTitleTv;

    @BindView(R.id.tv_setting_account_password)
    TextView mPasswordTv;

    @BindView(R.id.tv_setting_account_logout)
    TextView mLogoutTv;

    @Override
    protected int getFragmentLayoutId() {
        return 0;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_accout_manager;
    }

    @Override
    protected BaseFragment getFragment() {
        return null;
    }

    @Override
    protected void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.tv_setting_account_title:
                finish();
            case R.id.tv_setting_account_password:
                startActivity(com.acemurder.u_lab.modules.setting.PasswordModifyActivity.class);
                break;
            case R.id.tv_setting_account_logout:
                PreferenceUtil.clearEnterStatus();
                PreferenceUtil.clearUserAccount();
                PreferenceUtil.clearUserPassword();
                startActivity(LoginActivity.class);
                break;
        }
    }

    @Override
    protected void setWidgetListener() {
        mTitleTv.setOnClickListener(this);
        mLogoutTv.setOnClickListener(this);
        mPasswordTv.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        setWidgetListener();
    }
}
