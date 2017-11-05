package com.helldefender.enjoylife.modules.register;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.model.Key;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;
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
 * Created by Helldefender on 2017/6/7.
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.View {

    @BindView(R.id.tv_register_title)
    TextView mTitleTv;

    @BindView(R.id.et_register_phone)
    EditText etRegisterPhone;

    @BindView(R.id.btn_register_check)
    Button mRegisterBtnCheck;

    @BindView(R.id.et_register_check)
    EditText mVerifiCodeEt;

    @BindView(R.id.et_register_password)
    EditText mPasswordEt;

    @BindView(R.id.img_register)
    ImageView imgRegister;

    @BindView(R.id.btn_register)
    Button mRegisterBtn;

    @BindView(R.id.tv_register_email)
    TextView tvRegisterEmail;

    @BindView(R.id.et_register_password_confirm)
    EditText mPasswordConfirmEt;

    @BindView(R.id.img_register_confirm)
    ImageView mRegisterConfirmImg;

    private boolean isPasswordEnable = true;

    private boolean isEmail = false;

    private boolean isCheckBtnClick = false;

    private boolean isRegisterBtnEnable = false;

    private RegisterContract.Presenter mRegisterPresenter;

    private Key mKey;

    private Disposable mDisposable;

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (isCheckBtnClick && etRegisterPhone.getText().length() > 0 && mVerifiCodeEt.getText().length() > 0 && mPasswordEt.getText().length() > 5 && mPasswordEt.getText().toString().equals(mPasswordConfirmEt.getText().toString())) {
                isRegisterBtnEnable = true;
                mRegisterConfirmImg.setVisibility(View.VISIBLE);
            } else {
                mRegisterConfirmImg.setVisibility(View.GONE);
                isRegisterBtnEnable = false;
            }

            updateRegisterBtn(isRegisterBtnEnable);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void updateRegisterBtn(boolean isRegisterBtnEnable) {
        mRegisterBtn.setEnabled(isRegisterBtnEnable);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
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
            case R.id.tv_register_title:
                finish();
                break;
            case R.id.btn_register_check:
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
                                if (isEmail) {
                                    mRegisterPresenter.askEmailCode(etRegisterPhone.getText().toString());
                                } else {
                                    mRegisterPresenter.askSmsCode(etRegisterPhone.getText().toString());
                                }

                                isCheckBtnClick = true;
                                mRegisterBtnCheck.setEnabled(false);
                            }
                        }).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Long>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                mDisposable = d;
                            }

                            @Override
                            public void onNext(@NonNull Long aLong) {
                                mRegisterBtnCheck.setText(aLong + " s");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                mRegisterBtnCheck.setEnabled(true);
                                mRegisterBtnCheck.setText(getResources().getString(R.string.register_get_check));
                            }
                        });
                break;
            case R.id.btn_register:
                if (isEmail) {
                    mRegisterPresenter.register(etRegisterPhone.getText().toString()
                            , mPasswordEt.getText().toString()
                            , mPasswordEt.getText().toString()
                            , 1
                            , mVerifiCodeEt.getText().toString()
                            , mKey.getKey());
                } else {
                    mRegisterPresenter.register(etRegisterPhone.getText().toString()
                            , mPasswordEt.getText().toString()
                            , mPasswordEt.getText().toString()
                            , 0
                            , mVerifiCodeEt.getText().toString()
                            , mKey.getKey());
                }

                break;
            case R.id.img_register:
                if (isPasswordEnable) {
                    imgRegister.setImageResource(R.drawable.register_password_unable);
                    //mPasswordEt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mPasswordEt.setTransformationMethod(new PasswordTransformationMethod());
                    isPasswordEnable = false;
                } else {
                    imgRegister.setImageResource(R.drawable.register_password_enable);
                    mPasswordEt.setTransformationMethod(null);
                    //mPasswordEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    isPasswordEnable = true;
                }
                break;
            case R.id.tv_register_email:
                if (isEmail) {
                    tvRegisterEmail.setText("点此用邮箱注册");
                    etRegisterPhone.setHint("请输入有效手机号");
                    etRegisterPhone.setInputType(InputType.TYPE_CLASS_NUMBER);
                    isEmail = false;
                } else {
                    tvRegisterEmail.setText("点此用手机号注册");
                    etRegisterPhone.setHint("请输入有效邮箱");
                    etRegisterPhone.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
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

        handleStatusBar();

        new RegisterPresenter(this);

        mTitleTv.setOnClickListener(this);
        mRegisterBtnCheck.setOnClickListener(this);
        mRegisterBtn.setOnClickListener(this);
        imgRegister.setOnClickListener(this);
        tvRegisterEmail.setOnClickListener(this);

        etRegisterPhone.addTextChangedListener(mTextWatcher);
        mVerifiCodeEt.addTextChangedListener(mTextWatcher);
        mPasswordEt.addTextChangedListener(mTextWatcher);
        mPasswordConfirmEt.addTextChangedListener(mTextWatcher);

        mPasswordEt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
    }

    @Override
    public void onVerificationCodeWrong() {
        showToast("验证码错误");
    }

    @Override
    public void onAskSuccess(@NonNull Key key) {
        mKey = key;
        showToast("验证码已成功发送，注意查收");
    }

    @Override
    public void onUserAlreadyExist() {
        showToast("用户已存在");
    }

    @Override
    public void onRegisterSuccess() {
        startActivity(EditInfoActivity.class);
        finish();
    }

    @Override
    public void showProgress() {
        DialogManager.showProgressDialog(this, null, getString(R.string.registering), true, new DialogInterface.OnCancelListener() {
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
    public void setPresenter(RegisterContract.Presenter presenter) {
        mRegisterPresenter = presenter;

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
}
