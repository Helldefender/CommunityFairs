package com.helldefender.enjoylife.modules;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Helldefender on 2017/6/16.
 */

public class FormActivity extends BaseActivity {
    @BindView(R.id.tv_form_title)
    TextView tvFormTitle;
    @BindView(R.id.tv_form_name)
    TextView tvFormName;
    @BindView(R.id.tv_form_num)
    TextView tvFormNum;
    @BindView(R.id.tv_form_type)
    TextView tvFormType;
    @BindView(R.id.tv_form_sex)
    TextView tvFormSex;
    @BindView(R.id.ll_sign_up_sex)
    LinearLayout llSignUpSex;
    @BindView(R.id.tv_share_unit)
    TextView tvShareUnit;
    @BindView(R.id.tv_form_contact)
    TextView tvFormContact;
    @BindView(R.id.tv_form_max_num)
    TextView tvFormMaxNum;
    @BindView(R.id.ll_form_1)
    LinearLayout llForm1;
    @BindView(R.id.ll_form_2)
    LinearLayout llForm2;
    @BindView(R.id.tv_form_add)
    TextView tvFormAdd;
    @BindView(R.id.btn_form)
    Button btnForm;
    @BindView(R.id.tv_form_1)
    TextView tvForm1;


    private ArrayList<String> mSexOptionsList = new ArrayList<>();

    private ArrayList<String> mIdentityOptionsList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_form;
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void widgetClick(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        handleStatusBar();

        mSexOptionsList.add("必填");
        mSexOptionsList.add("选填");

        mIdentityOptionsList.add("1人");
        mIdentityOptionsList.add("2人");
        mIdentityOptionsList.add("3人");
        mIdentityOptionsList.add("4人");
        mIdentityOptionsList.add("5人");
        mIdentityOptionsList.add("6人");
        mIdentityOptionsList.add("7人");
        mIdentityOptionsList.add("8人");
        mIdentityOptionsList.add("9人");

        tvFormTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvFormAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llForm1.setVisibility(View.VISIBLE);
            }
        });


        tvFormName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final OptionsPickerView sexOptions = new OptionsPickerView.Builder(FormActivity.this, new OptionsPickerView.OnOptionsSelectListener() {

                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        String textSelect = mSexOptionsList.get(options1);
                        tvFormName.setText(textSelect);
                    }
                })
                        .setTitleText("选择是否必填")
                        .setContentTextSize(20)//设置滚轮文字大小
                        .setDividerColor(Color.parseColor("#ECECEC"))//设置分割线的颜色
                        .setBgColor(Color.parseColor("#FFFFFF"))
                        .setTitleBgColor(Color.parseColor("#F5F6F7"))
                        .setTitleColor(Color.parseColor("#666666"))
                        .setCancelColor(Color.parseColor("#999999"))
                        .setSubmitColor(Color.parseColor("#999999"))
                        .setTextColorCenter(Color.parseColor("#999999"))
                        .build();
                sexOptions.setPicker(mSexOptionsList);
                sexOptions.show();
            }
        });

        tvForm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final OptionsPickerView sexOptions = new OptionsPickerView.Builder(FormActivity.this, new OptionsPickerView.OnOptionsSelectListener() {

                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        String textSelect = mSexOptionsList.get(options1);
                        tvForm1.setText(textSelect);
                    }
                })
                        .setTitleText("选择是否必填")
                        .setContentTextSize(20)//设置滚轮文字大小
                        .setDividerColor(Color.parseColor("#ECECEC"))//设置分割线的颜色
                        .setBgColor(Color.parseColor("#FFFFFF"))
                        .setTitleBgColor(Color.parseColor("#F5F6F7"))
                        .setTitleColor(Color.parseColor("#666666"))
                        .setCancelColor(Color.parseColor("#999999"))
                        .setSubmitColor(Color.parseColor("#999999"))
                        .setTextColorCenter(Color.parseColor("#999999"))
                        .build();
                sexOptions.setPicker(mSexOptionsList);
                sexOptions.show();
            }
        });

        tvFormContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final OptionsPickerView sexOptions = new OptionsPickerView.Builder(FormActivity.this, new OptionsPickerView.OnOptionsSelectListener() {

                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        String textSelect = mSexOptionsList.get(options1);
                        tvFormContact.setText(textSelect);
                    }
                })
                        .setTitleText("选择是否必填")
                        .setContentTextSize(20)//设置滚轮文字大小
                        .setDividerColor(Color.parseColor("#ECECEC"))//设置分割线的颜色
                        .setBgColor(Color.parseColor("#FFFFFF"))
                        .setTitleBgColor(Color.parseColor("#F5F6F7"))
                        .setTitleColor(Color.parseColor("#666666"))
                        .setCancelColor(Color.parseColor("#999999"))
                        .setSubmitColor(Color.parseColor("#999999"))
                        .setTextColorCenter(Color.parseColor("#999999"))
                        .build();
                sexOptions.setPicker(mSexOptionsList);
                sexOptions.show();
            }
        });

        tvFormNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final OptionsPickerView sexOptions = new OptionsPickerView.Builder(FormActivity.this, new OptionsPickerView.OnOptionsSelectListener() {

                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        String textSelect = mSexOptionsList.get(options1);
                        tvFormNum.setText(textSelect);
                    }
                })
                        .setTitleText("选择是否必填")
                        .setContentTextSize(20)//设置滚轮文字大小
                        .setDividerColor(Color.parseColor("#ECECEC"))//设置分割线的颜色
                        .setBgColor(Color.parseColor("#FFFFFF"))
                        .setTitleBgColor(Color.parseColor("#F5F6F7"))
                        .setTitleColor(Color.parseColor("#666666"))
                        .setCancelColor(Color.parseColor("#999999"))
                        .setSubmitColor(Color.parseColor("#999999"))
                        .setTextColorCenter(Color.parseColor("#999999"))
                        .build();
                sexOptions.setPicker(mSexOptionsList);
                sexOptions.show();
            }
        });

        tvFormSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final OptionsPickerView sexOptions = new OptionsPickerView.Builder(FormActivity.this, new OptionsPickerView.OnOptionsSelectListener() {

                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        String textSelect = mSexOptionsList.get(options1);
                        tvFormSex.setText(textSelect);
                    }
                })
                        .setTitleText("选择是否必填")
                        .setContentTextSize(20)//设置滚轮文字大小
                        .setDividerColor(Color.parseColor("#ECECEC"))//设置分割线的颜色
                        .setBgColor(Color.parseColor("#FFFFFF"))
                        .setTitleBgColor(Color.parseColor("#F5F6F7"))
                        .setTitleColor(Color.parseColor("#666666"))
                        .setCancelColor(Color.parseColor("#999999"))
                        .setSubmitColor(Color.parseColor("#999999"))
                        .setTextColorCenter(Color.parseColor("#999999"))
                        .build();
                sexOptions.setPicker(mSexOptionsList);
                sexOptions.show();
            }
        });

        tvFormType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final OptionsPickerView sexOptions = new OptionsPickerView.Builder(FormActivity.this, new OptionsPickerView.OnOptionsSelectListener() {

                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        String textSelect = mSexOptionsList.get(options1);
                        tvFormType.setText(textSelect);
                    }
                })
                        .setTitleText("选择是否必填")
                        .setContentTextSize(20)//设置滚轮文字大小
                        .setDividerColor(Color.parseColor("#ECECEC"))//设置分割线的颜色
                        .setBgColor(Color.parseColor("#FFFFFF"))
                        .setTitleBgColor(Color.parseColor("#F5F6F7"))
                        .setTitleColor(Color.parseColor("#666666"))
                        .setCancelColor(Color.parseColor("#999999"))
                        .setSubmitColor(Color.parseColor("#999999"))
                        .setTextColorCenter(Color.parseColor("#999999"))
                        .build();
                sexOptions.setPicker(mSexOptionsList);
                sexOptions.show();
            }
        });

        tvFormMaxNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final OptionsPickerView sexOptions = new OptionsPickerView.Builder(FormActivity.this, new OptionsPickerView.OnOptionsSelectListener() {

                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        String textSelect = mIdentityOptionsList.get(options1);
                        tvFormMaxNum.setText(textSelect);
                    }
                })
                        .setTitleText("请选择最多报名人数")
                        .setContentTextSize(20)//设置滚轮文字大小
                        .setDividerColor(Color.parseColor("#ECECEC"))//设置分割线的颜色
                        .setBgColor(Color.parseColor("#FFFFFF"))
                        .setTitleBgColor(Color.parseColor("#F5F6F7"))
                        .setTitleColor(Color.parseColor("#666666"))
                        .setCancelColor(Color.parseColor("#999999"))
                        .setSubmitColor(Color.parseColor("#999999"))
                        .setTextColorCenter(Color.parseColor("#999999"))
                        .build();
                sexOptions.setPicker(mIdentityOptionsList);
                sexOptions.show();
            }
        });
    }
}
