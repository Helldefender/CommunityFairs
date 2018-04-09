package com.helldefender.communityfairs.modules.user.login;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.app.BaseActivity;
import com.helldefender.communityfairs.app.ViewModelFactory;
import com.helldefender.communityfairs.databinding.ActivityLoginBinding;
import com.helldefender.communityfairs.modules.main.MainActivity;
import com.helldefender.communityfairs.modules.user.register.RegisterActivity;
import com.helldefender.communityfairs.utils.SoftHideKeyBoardUtil;

/**
 * 功能：
 * 描述：
 * Created by Helldefender on 2018/1/13.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected int getVariableId() {
        return BR.viewModel;
    }

    @Override
    protected LoginViewModel getViewModel() {
        return ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(LoginViewModel.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SoftHideKeyBoardUtil.assistActivity(this);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.class);
                finish();
//                if (binding.etLoginAccount.getText().length() == 0) {
//                    showToast("用户名不得为空");
//                    return;
//                }
//                if (binding.etLoginPassword.getText().length() == 0) {
//                    showToast("密码不得为空");
//                    return;
//                }
//
//                if ((binding.etLoginAccount.getText().length() < 5) || (binding.etLoginAccount.getText().length() > 10)) {
//                    showToast("用户名长度为5-10个字符");
//                    return;
//                }
//                if ((binding.etLoginPassword.getText().length() < 6) || (binding.etLoginPassword.getText().length() > 15)) {
//                    showToast("密码长度为6-20个字符");
//                    return;
//                }
//                if (binding.etLoginAccount.getText().toString().equals("admin") && binding.etLoginPassword.getText().toString().equals("123456")) {
//                    startActivity(MainActivity.class);
//                } else if (!binding.etLoginAccount.getText().toString().equals("admin")) {
//                    showToast("用户名错误"+binding.etLoginAccount.getText());
//                } else if (!binding.etLoginPassword.getText().toString().equals("123456")) {
//                    showToast("密码错误");
//                }
            }
        });

        binding.tvLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RegisterActivity.class);
            }
        });
    }
}
