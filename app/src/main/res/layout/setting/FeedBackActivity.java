package com.acemurder.u_lab.modules.setting;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.acemurder.u_lab.R;
import com.acemurder.u_lab.base.BaseActivity;
import com.acemurder.u_lab.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Helldefender on 2017/6/8.
 */

public class FeedBackActivity extends BaseActivity {

    @BindView(R.id.tv_feedback_title)
    TextView mTitleTv;

    @BindView(R.id.et_feedback_content)
    EditText mContentEt;

    @BindView(R.id.et_feedback_phone)
    EditText mPhoneEt;

    @BindView(R.id.btn_feedback)
    Button mFeedbackBtn;

    private boolean isFeedBackBtnEnable = false;

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (mContentEt.getText().length() > 0 && mPhoneEt.getText().length() > 10) {
                isFeedBackBtnEnable = true;
            } else {
                isFeedBackBtnEnable = false;
            }

            updateRegisterBtn(isFeedBackBtnEnable);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void updateRegisterBtn(boolean isFeedBackBtnEnable) {
        mFeedbackBtn.setEnabled(isFeedBackBtnEnable);
    }

    @Override
    protected int getFragmentLayoutId() {
        return 0;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected BaseFragment getFragment() {
        return null;
    }

    @Override
    protected void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.tv_feedback_title:
                finish();
            case R.id.btn_feedback:
                //finish();
        }
    }

    @Override
    protected void setWidgetListener() {
        super.setWidgetListener();
        mTitleTv.setOnClickListener(this);
        mFeedbackBtn.setOnClickListener(this);
        mContentEt.addTextChangedListener(mTextWatcher);
        mPhoneEt.addTextChangedListener(mTextWatcher);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
