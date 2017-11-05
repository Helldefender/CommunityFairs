package com.acemurder.u_lab.modules.setting;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acemurder.u_lab.R;
import com.acemurder.u_lab.base.BaseActivity;
import com.acemurder.u_lab.base.BaseFragment;
import com.bigkoo.pickerview.OptionsPickerView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Helldefender on 2017/6/4.
 */

public class SettingActivity extends BaseActivity {

    @BindView(R.id.tv_drawer_setting_notification)
    TextView mNotificationTv;

    @BindView(R.id.ll_drawer_setting_notification)
    LinearLayout mNotificationLayout;

    @BindView(R.id.tv_drawer_setting_language)
    TextView mLanguageTv;

    @BindView(R.id.ll_drawer_setting_language)
    LinearLayout mLanguageLayout;

    @BindView(R.id.tv_drawer_setting_account_management)
    TextView mAccountManagementTv;

    @BindView(R.id.ll_drawer_setting_account_management)
    LinearLayout mAccountManagementLayout;

    @BindView(R.id.tv_drawer_setting_help)
    TextView mHelpTv;

    @BindView(R.id.tv_drawer_setting_about)
    TextView mAboutTv;

    @BindView(R.id.tv_drawer_setting_title)
    TextView mTitleTv;

    private ArrayList<String> mLanguageOptionsList = new ArrayList<>();
    private ArrayList<String> mNotificationOptionsList = new ArrayList<>();

    @Override
    protected int getFragmentLayoutId() {
        return 0;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected BaseFragment getFragment() {
        return null;
    }

    @Override
    protected void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.tv_drawer_setting_title:
                finish();
                break;
            case R.id.ll_drawer_setting_notification:
                OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        String textSelect = mNotificationOptionsList.get(options1);

                        mNotificationTv.setText(textSelect);
                    }
                })
                        .setTitleText("消息推送")
                        .setContentTextSize(20)//设置滚轮文字大小
                        .setDividerColor(Color.parseColor("#ECECEC"))//设置分割线的颜色
                        .setBgColor(Color.parseColor("#FFFFFF"))
                        .setTitleBgColor(Color.parseColor("#F5F6F7"))
                        .setTitleColor(Color.parseColor("#666666"))
                        .setCancelColor(Color.parseColor("#999999"))
                        .setSubmitColor(Color.parseColor("#999999"))
                        .setTextColorCenter(Color.LTGRAY)
                        .build();
                pvOptions.setPicker(mNotificationOptionsList);
                pvOptions.show();

                break;
            case R.id.ll_drawer_setting_language:
                OptionsPickerView languageOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        String textSelect = mLanguageOptionsList.get(options1);

                        mLanguageTv.setText(textSelect);
                    }
                })
                        .setTitleText("语言选择")
                        .setContentTextSize(20)//设置滚轮文字大小
                        .setDividerColor(Color.parseColor("#ECECEC"))//设置分割线的颜色
                        .setBgColor(Color.parseColor("#FFFFFF"))
                        .setTitleBgColor(Color.parseColor("#F5F6F7"))
                        .setTitleColor(Color.parseColor("#666666"))
                        .setCancelColor(Color.parseColor("#999999"))
                        .setSubmitColor(Color.parseColor("#999999"))
                        .setTextColorCenter(Color.LTGRAY)
                        .build();
                languageOptions.setPicker(mLanguageOptionsList);
                languageOptions.show();
                break;
            case R.id.ll_drawer_setting_account_management:
                startActivity(AccountManagerActivity.class);
                break;
            case R.id.tv_drawer_setting_help:
                startActivity(com.acemurder.u_lab.modules.setting.HelpActivity.class);
                break;
            case R.id.tv_drawer_setting_about:
                startActivity(AboutActivity.class);
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNotificationOptionsList.add("开");
        mNotificationOptionsList.add("关");

        mLanguageOptionsList.add("中文");
        mLanguageOptionsList.add("英文");

        mTitleTv.setOnClickListener(this);
        mNotificationLayout.setOnClickListener(this);
        mLanguageLayout.setOnClickListener(this);
        mAccountManagementLayout.setOnClickListener(this);
        mHelpTv.setOnClickListener(this);
        mAboutTv.setOnClickListener(this);

    }
}
