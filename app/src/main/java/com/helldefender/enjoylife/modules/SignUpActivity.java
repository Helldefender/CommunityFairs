package com.helldefender.enjoylife.modules;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Helldefender on 2017/6/15.
 */

public class SignUpActivity extends BaseActivity {
    @BindView(R.id.tv_sign_up_title)
    TextView tvSignUpTitle;
    @BindView(R.id.et_sign_up_name)
    EditText etSignUpName;
    @BindView(R.id.et_sign_up_num)
    EditText etSignUpNum;
    @BindView(R.id.ll_share_num)
    LinearLayout llShareNum;
    @BindView(R.id.et_sign_up_type)
    TextView etSignUpType;
    @BindView(R.id.ll_share_num_male)
    LinearLayout llShareNumMale;
    @BindView(R.id.et_sign_up_sex)
    TextView etSignUpSex;
    @BindView(R.id.tv_share_unit)
    TextView tvShareUnit;
    @BindView(R.id.et_sign_up_contact)
    EditText etSignUpContact;
    @BindView(R.id.et_sign_up_remark)
    EditText etSignUpRemark;
    @BindView(R.id.tv_sign_up_add)
    TextView tvSignUpAdd;
    @BindView(R.id.btn_sign_up)
    Button btnSignUp;
    @BindView(R.id.ll_search_borrow)
    LinearLayout llSearchBorrow;
    @BindView(R.id.et_sign_up_name_1)
    EditText etSignUpName1;
    @BindView(R.id.et_sign_up_num_1)
    EditText etSignUpNum1;
    @BindView(R.id.ll_share_num_1)
    LinearLayout llShareNum1;
    @BindView(R.id.et_sign_up_type_1)
    TextView etSignUpType1;
    @BindView(R.id.ll_share_num_male_1)
    LinearLayout llShareNumMale1;
    @BindView(R.id.et_sign_up_sex_1)
    TextView etSignUpSex1;
    @BindView(R.id.tv_share_unit_1)
    TextView tvShareUnit1;
    @BindView(R.id.ll_sign_up)
    LinearLayout llSignUp;
    @BindView(R.id.ll_sign_up_sex)
    LinearLayout llSignUpSex;
    @BindView(R.id.et_sign_up_contact_1)
    EditText etSignUpContact1;

    private ArrayList<String> mSexOptionsList = new ArrayList<>();

    private ArrayList<String> mIdentityOptionsList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign_up;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleStatusBar();

        mSexOptionsList.add("男");
        mSexOptionsList.add("女");

        mIdentityOptionsList.add("通信学院");
        mIdentityOptionsList.add("计算机学院");
        mIdentityOptionsList.add("自动化学院");
        mIdentityOptionsList.add("光电学院");
        mIdentityOptionsList.add("经管学院");
        mIdentityOptionsList.add("传媒学院");
        mIdentityOptionsList.add("法学院");

        tvSignUpAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUpActivity.this, "最多支持两人报名", Toast.LENGTH_SHORT).show();
                llSignUp.setVisibility(View.VISIBLE);
            }
        });

        tvSignUpTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        llSignUpSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final OptionsPickerView sexOptions = new OptionsPickerView.Builder(SignUpActivity.this, new OptionsPickerView.OnOptionsSelectListener() {

                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        String textSelect = mSexOptionsList.get(options1);
                        etSignUpSex.setText(textSelect);
                    }
                })
                        .setTitleText("选择性别")
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

        etSignUpType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final OptionsPickerView sexOptions = new OptionsPickerView.Builder(SignUpActivity.this, new OptionsPickerView.OnOptionsSelectListener() {

                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        String textSelect = mIdentityOptionsList.get(options1);
                        etSignUpType.setText(textSelect);
                    }
                })
                        .setTitleText("选择学院")
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
