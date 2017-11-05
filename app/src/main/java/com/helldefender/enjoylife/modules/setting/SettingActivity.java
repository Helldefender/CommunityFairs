package com.helldefender.enjoylife.modules.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Helldefender on 2017/6/4.
 */

public class SettingActivity extends BaseActivity {


    @BindView(R.id.tv_drawer_setting_title)
    TextView tvDrawerSettingTitle;
    @BindView(R.id.tv_drawer_setting_account_management)
    TextView tvDrawerSettingAccountManagement;
    @BindView(R.id.ll_drawer_setting_account_management)
    LinearLayout llDrawerSettingAccountManagement;
    @BindView(R.id.ll_user_info)
    LinearLayout llUserInfo;
    @BindView(R.id.tv_drawer_setting_help)
    TextView tvDrawerSettingHelp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.tv_drawer_setting_title:
                finish();
                break;
            case R.id.tv_drawer_setting_account_management:
                startActivity(PasswordModifyActivity.class);
                break;
            case R.id.tv_drawer_setting_help:
                startActivity(FeedBackActivity.class);
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handleStatusBar();

        tvDrawerSettingTitle.setOnClickListener(this);
        tvDrawerSettingAccountManagement.setOnClickListener(this);
        tvDrawerSettingHelp.setOnClickListener(this);
    }
}
