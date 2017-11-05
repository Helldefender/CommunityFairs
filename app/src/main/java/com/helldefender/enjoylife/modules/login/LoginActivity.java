package com.helldefender.enjoylife.modules.login;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.modules.password.PasswordResetActivity;
import com.helldefender.enjoylife.modules.register.RegisterActivity;
import com.helldefender.enjoylife.ui.activity.MainActivity;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;
import com.helldefender.enjoylife.utils.PreferenceUtil;
import com.helldefender.enjoylife.widget.dialog.DialogManager;

import butterknife.BindView;

/**
 * Created by Helldefender on 2017/6/7.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.et_login_account)
    EditText etLoginAccount;

    @BindView(R.id.et_login_password)
    EditText etLoginPassword;

    @BindView(R.id.btn_login)
    Button LoginBtn;

    @BindView(R.id.tv_login_forget_password)
    TextView tvLoginForgetPassword;

    @BindView(R.id.tv_login_register)
    TextView tvLoginRegister;

    private LoginContract.Presenter mLoginPresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initInject() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (etLoginAccount.getText().length() == 0 || etLoginPassword.getText().length() == 0) {
                    showToast("请填写用户名和密码");
                }
                startActivity(MainActivity.class);
                break;
            case R.id.tv_login_forget_password:
                startActivity(PasswordResetActivity.class);
                break;
            case R.id.tv_login_register:
                startActivity(RegisterActivity.class);
                break;
        }
    }

    @Override
    protected void setWidgetListener() {
        LoginBtn.setOnClickListener(this);
        tvLoginForgetPassword.setOnClickListener(this);
        tvLoginRegister.setOnClickListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handleStatusBar();

        if (PreferenceUtil.getEnterStatus() != null) {
            startActivity(MainActivity.class);
            finish();
        }

        new LoginPresenter(this);

//        //模仿用户登录
//        if (!(PreferenceUtil.getEnterStatus() == null)) {
//
//            mLoginPresenter.login(PreferenceUtil.getUserAccount(), PreferenceUtil.getUserPassword());
//        }

        setWidgetListener();
    }

    @Override
    public void onLoginSuccess() {
        if (PreferenceUtil.getEnterStatus() == null) {
            PreferenceUtil.saveEnterStatus("login");
            PreferenceUtil.saveUserAccount(etLoginAccount.getText().toString());
            PreferenceUtil.saveUserPassword(etLoginPassword.getText().toString());
        }

        startActivity(MainActivity.class);
    }

    @Override
    public void onPasswordWrong() {
        showToast("密码错误");
    }

    @Override
    public void showProgress() {
        DialogManager.showProgressDialog(this, null, getString(R.string.logging), true, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                DialogManager.dismissProgressDialog();
            }
        }).setCanceledOnTouchOutside(false);
    }

    @Override
    public void stopProgress() {
        DialogManager.dismissProgressDialog();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mLoginPresenter = presenter;
    }

    @Override
    public void onError(Throwable t) {

    }
}
