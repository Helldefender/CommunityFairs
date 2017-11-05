package com.acemurder.u_lab.modules.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.acemurder.u_lab.R;
import com.acemurder.u_lab.base.BaseActivity;
import com.acemurder.u_lab.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Helldefender on 2017/6/7.
 */

public class AboutActivity extends BaseActivity {

    @BindView(R.id.tv_drawer_setting_about_title)
    TextView mTitleTv;

    @Override
    protected int getFragmentLayoutId() {
        return 0;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected BaseFragment getFragment() {
        return null;
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

        mTitleTv.setOnClickListener(this);
    }
}
