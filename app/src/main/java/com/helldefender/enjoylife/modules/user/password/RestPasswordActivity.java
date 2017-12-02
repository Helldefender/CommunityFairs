package com.helldefender.enjoylife.modules.user.password;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.model.entity.Key;
import com.helldefender.enjoylife.app.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Helldefender on 2017/6/9.
 */

public class RestPasswordActivity extends BaseActivity implements PasswordContract.View {
    @BindView(R.id.et_password_reset_phone)
    EditText etPasswordResetPhone;
    @BindView(R.id.btn_password_reset_check)
    Button btnPasswordResetCheck;
    @BindView(R.id.et_password_reset_check)
    EditText etPasswordResetCheck;
    @BindView(R.id.et_password_reset_new)
    EditText etPasswordResetNew;
    @BindView(R.id.img_password_reset)
    ImageView imgPasswordReset;
    @BindView(R.id.btn_password_reset)
    Button btnPasswordReset;
    @BindView(R.id.tv_password_reset_email)
    TextView tvPasswordResetEmail;
    @BindView(R.id.tv_password_reset_title)
    TextView tvPasswordResetTitle;

    private boolean isEnable = true;

    private boolean isEmail = false;

    private boolean isCheckBtnClick = false;

    private boolean isRegisterBtnEnable = false;

    private ResetPasswordPresenter mResetPasswordPresenter;

    private Key mKey;

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (isCheckBtnClick && etPasswordResetPhone.getText().length() > 0 && etPasswordResetCheck.getText().length() > 0 && etPasswordResetNew.getText().length() > 0) {
                isRegisterBtnEnable = true;
            } else {
                isRegisterBtnEnable = false;
            }
            updateRegisterBtn(isRegisterBtnEnable);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void updateRegisterBtn(boolean isRegisterBtnEnable) {
        btnPasswordReset.setEnabled(isRegisterBtnEnable);
    }

    @Override
    public void onAskCodeSuccess(Key key) {
        mKey = key;
        showToast("验证码已成功发送，注意查收");
    }

    @Override
    public void onResetSuccess() {
        finish();
    }

    @Override
    public void onVerificationCodeWrong() {
        showToast("验证码错误");
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void stopProgress() {

    }

    @Override
    public void setPresenter(PasswordContract.Presenter presenter) {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_password_reset;
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
            case R.id.tv_password_reset_title:
                finish();
                break;
            case R.id.btn_password_reset_check:
                isCheckBtnClick = true;
                if (isEmail) {
                    mResetPasswordPresenter.askEmailCode(etPasswordResetPhone.getText().toString());
                } else {
                    mResetPasswordPresenter.askSmsCode(etPasswordResetPhone.getText().toString());
                }
                break;
            case R.id.btn_password_reset:
                mResetPasswordPresenter.resetPassword(etPasswordResetNew.getText().toString(), etPasswordResetCheck.getText().toString(), mKey.getKey());
                break;
            case R.id.img_password_reset:
                if (isEnable) {
                    imgPasswordReset.setImageResource(R.drawable.register_password_unable);
                    etPasswordResetNew.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isEnable = false;
                } else {
                    imgPasswordReset.setImageResource(R.drawable.register_password_enable);
                    etPasswordResetNew.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    isEnable = true;
                }
                break;
            case R.id.tv_password_reset_email:
                isCheckBtnClick = false;
                isRegisterBtnEnable = false;

                if (isEmail) {
                    tvPasswordResetEmail.setText("点此用邮箱找回");
                    etPasswordResetPhone.setHint("请输入手机号");
                    etPasswordResetPhone.setInputType(InputType.TYPE_CLASS_NUMBER);
                    isEmail = false;
                } else {
                    tvPasswordResetEmail.setText("点此用手机号找回");
                    etPasswordResetPhone.setHint("请输入邮箱");
                    etPasswordResetPhone.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    isEmail = true;
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        mResetPasswordPresenter = new ResetPasswordPresenter(this);

        tvPasswordResetTitle.setOnClickListener(this);
        btnPasswordResetCheck.setOnClickListener(this);
        imgPasswordReset.setOnClickListener(this);
        btnPasswordReset.setOnClickListener(this);
        tvPasswordResetEmail.setOnClickListener(this);

        etPasswordResetPhone.addTextChangedListener(mTextWatcher);
        etPasswordResetCheck.addTextChangedListener(mTextWatcher);
        etPasswordResetNew.addTextChangedListener(mTextWatcher);
    }
}
