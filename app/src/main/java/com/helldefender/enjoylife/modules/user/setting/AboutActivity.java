package com.helldefender.enjoylife.modules.user.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.app.BaseActivity;

import butterknife.BindView;

/**
 * Created by Helldefender on 2017/6/7.
 */

public class AboutActivity extends BaseActivity {

    @BindView(R.id.tv_drawer_setting_about_title)
    TextView mTitleTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
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
            case R.id.tv_drawer_setting_about_title:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handleStatusBar();

        mTitleTv.setOnClickListener(this);
    }
}
