package com.helldefender.communityfairs.modules.user.register;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.app.BaseActivity;
import com.helldefender.communityfairs.app.ViewModelFactory;
import com.helldefender.communityfairs.databinding.ActivityRegisterBinding;
import com.helldefender.communityfairs.modules.main.MainActivity;
import com.helldefender.communityfairs.utils.SoftHideKeyBoardUtil;

/**
 * 功能：
 * 描述：
 * Created by Helldefender on 2018/1/13.
 */

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected int getVariableId() {
        return BR.viewModel;
    }

    @Override
    protected RegisterViewModel getViewModel() {
        return ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(RegisterViewModel.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SoftHideKeyBoardUtil.assistActivity(this);

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.etRegisterPhone.getText().length() == 0) {
                    showToast("手机号不得为空");
                    return;
                }
                if (binding.etRegisterCheck.getText().length() == 0) {
                    showToast("验证码不得为空");
                    return;
                }
                if (binding.etRegisterPassword.getText().length() == 0) {
                    showToast("密码不得为空");
                    return;
                }
                if (binding.etRegisterPasswordConfirm.getText().length() == 0) {
                    showToast("密码不得为空");
                    return;
                }

                if (!binding.etRegisterPassword.getText().toString().equals(binding.etRegisterPasswordConfirm.getText().toString())) {
                    showToast("两次密码输入不一致");
                    return;
                }

                if (binding.etRegisterPhone.getText().length() != 11) {
                    showToast("手机号错误");
                    return;
                }

                if (binding.etRegisterPassword.getText().length() > 5 && binding.etRegisterPasswordConfirm.getText().length() > 5 &&
                        binding.etRegisterPassword.getText().toString().equals(binding.etRegisterPasswordConfirm.getText().toString()))
                    startActivity(MainActivity.class);
            }
        });

        binding.btnRegisterCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.etRegisterPhone.getText().length() != 11)
                    showToast("请输入正确的手机号");
            }
        });
    }
}
