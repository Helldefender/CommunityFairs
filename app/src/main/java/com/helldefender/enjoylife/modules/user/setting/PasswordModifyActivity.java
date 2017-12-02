package com.helldefender.enjoylife.modules.user.setting;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.model.entity.Key;
import com.helldefender.enjoylife.modules.user.password.PasswordContract;
import com.helldefender.enjoylife.modules.user.password.ResetPasswordPresenter;
import com.helldefender.enjoylife.app.BaseActivity;
import com.helldefender.enjoylife.utils.PreferenceUtil;
import com.helldefender.enjoylife.widget.dialog.DialogManager;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Helldefender on 2017/6/12.
 */

public class PasswordModifyActivity extends BaseActivity implements PasswordContract.View {
    
    @BindView(R.id.tv_password_modify_account)
    TextView mAccountTv;

    @BindView(R.id.btn_password_modify_check)
    Button mCheckBtn;

    @BindView(R.id.et_password_modify_check)
    EditText mCheckEt;

    @BindView(R.id.et_password_modify_new)
    EditText mNewPasswordEt;

    @BindView(R.id.img_password_modify)
    ImageView imgPasswordReset;

    @BindView(R.id.btn_password_modify)
    Button modifyBtn;

//    @BindView(R.id.tv_password_modify_email)
//    TextView tvPasswordResetEmail;

    @BindView(R.id.tv_password_modify_title)
    TextView tvPasswordResetTitle;

    private boolean isEnable = true;

    private boolean isPhone;

    //private boolean isEmail = false;

    private boolean isCheckBtnClick = false;

    private boolean isRegisterBtnEnable = false;

    private PasswordContract.Presenter mPasswordResetPresenter;

    private Key mKey;

    private Disposable mDisposable;

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (isCheckBtnClick && mAccountTv.getText().length() > 0 && mCheckEt.getText().length() > 0 && mNewPasswordEt.getText().length() > 5) {
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
        modifyBtn.setEnabled(isRegisterBtnEnable);
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
        DialogManager.showProgressDialog(this, null, getString(R.string.reset_password), true, new DialogInterface.OnCancelListener() {
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
    public void setPresenter(PasswordContract.Presenter presenter) {
        mPasswordResetPresenter = presenter;
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_password_modify;
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
            case R.id.tv_password_modify_title:
                finish();
                break;
            case R.id.btn_password_modify_check:
                Observable.interval(0, 1, TimeUnit.SECONDS)
                        .take(61) //设置总共发送的次数
                        .map(new Function<Long, Long>() {
                            @Override
                            public Long apply(@NonNull Long aLong) throws Exception {
                                return 60 - aLong;
                            }
                        })
                        .subscribeOn(Schedulers.computation())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull Disposable disposable) throws Exception {
                                //执行任务前的准备工作
                                if (isPhone) {
                                    mPasswordResetPresenter.askSmsCode(mAccountTv.getText().toString());
                                } else {
                                    mPasswordResetPresenter.askEmailCode(mAccountTv.getText().toString());
                                }

                                isCheckBtnClick = true;
                                mCheckBtn.setEnabled(false);
                            }
                        }).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Long>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                mDisposable = d;
                            }

                            @Override
                            public void onNext(@NonNull Long aLong) {
                                mCheckBtn.setText(aLong + " s");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                mCheckBtn.setEnabled(true);
                                mCheckBtn.setText(getResources().getString(R.string.register_get_check));
                            }
                        });
                break;
            case R.id.btn_password_modify:
                mPasswordResetPresenter.resetPassword(mNewPasswordEt.getText().toString(), mCheckEt.getText().toString(), mKey.getKey());
                break;
            case R.id.img_password_modify:
                if (isEnable) {
                    imgPasswordReset.setImageResource(R.drawable.register_password_unable);
                    //mNewPasswordEt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mNewPasswordEt.setTransformationMethod(new PasswordTransformationMethod());
                    isEnable = false;
                } else {
                    imgPasswordReset.setImageResource(R.drawable.register_password_enable);
                    //mNewPasswordEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mNewPasswordEt.setTransformationMethod(null);
                    isEnable = true;
                }
                break;
//            case R.id.tv_password_modify_email:
//                isCheckBtnClick = false;
//                isRegisterBtnEnable = false;
//
//                if (isEmail) {
//                    tvPasswordResetEmail.setText("点此用邮箱重置");
//                    mAccountTv.setHint("请输入手机号");
//                    mAccountTv.setInputType(InputType.TYPE_CLASS_NUMBER);
//                    isEmail = false;
//                } else {
//                    tvPasswordResetEmail.setText("点此用手机号修改");
//                    mAccountTv.setHint("请输入邮箱");
//                    mAccountTv.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
//                    isEmail = true;
//                }
//                break;
        }
    }

    @Override
    protected void setWidgetListener() {
        tvPasswordResetTitle.setOnClickListener(this);
        mCheckBtn.setOnClickListener(this);
        imgPasswordReset.setOnClickListener(this);
        modifyBtn.setOnClickListener(this);
        //tvPasswordResetEmail.setOnClickListener(this);

        mAccountTv.addTextChangedListener(mTextWatcher);
        mCheckEt.addTextChangedListener(mTextWatcher);
        mNewPasswordEt.addTextChangedListener(mTextWatcher);

        mNewPasswordEt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        handleStatusBar();

        new ResetPasswordPresenter(this);


        isPhone = PreferenceUtil.getUserAccountType();
        mAccountTv.setText(PreferenceUtil.getUserAccount());
        //从本地读取用户账号信息（手机号/邮箱根据指定信息发送验证码），设置tv,
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
}
