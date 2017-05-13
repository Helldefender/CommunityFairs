package com.helldefender.enjoylife.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.app.MyApplication;
import com.helldefender.enjoylife.delete.server.HttpMethods;
import com.helldefender.enjoylife.delete.server.ProgressSubscriber;
import com.helldefender.enjoylife.delete.server.SubscriberOnNextListener;
import com.helldefender.enjoylife.delete.server.entity.UserBean;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;
import com.helldefender.enjoylife.utils.NetworkUtil;
import com.helldefender.enjoylife.utils.PreferenceUtil;
import com.helldefender.enjoylife.widget.ClearEditText;
import com.helldefender.enjoylife.widget.DialogManager;
import com.jude.utils.JUtils;


/**
 * Created by Helldefender on 2017/2/21.
 */

public class LoginActivity extends BaseActivity {

    private boolean isLogin = false;

    private static boolean firstEnter = true;

    private boolean registerMode = false;

    private boolean registerPanelInited = false;

    private ImageView logoImage;

    private TextView logBtn;

    private TextView switchModeBtn;

    private TextView tipText;

    private TextView account;

    private TextView username;

    private TextView password;

    private TextView passwordConfirm;

    private ClearEditText loginAccountEdit;

    private ClearEditText loginPasswordEdit;

    private ClearEditText registerAccountEdit;

    private ClearEditText registerNickNameEdit;

    private ClearEditText registerPasswordEdit;

    private ClearEditText registerPasswordComfirmEdit;

    private View loginLayout;

    private View registerLayout;

    private SubscriberOnNextListener loginListener;

    private SubscriberOnNextListener registerListener;

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!registerMode) {
                boolean isEnable = loginAccountEdit.getText().length() > 0
                        && loginPasswordEdit.getText().length() > 0;
                updateLoginBtn(LoginActivity.this, logBtn, isEnable);

                boolean userNameEnable = loginAccountEdit.getText().length() > 0;
                boolean passwordEnable = loginPasswordEdit.getText().length() > 0;

                loginAccountEdit.setIconResource(userNameEnable ? R.mipmap.username_enable : R.mipmap.username_disable);
                loginPasswordEdit.setIconResource(passwordEnable ? R.mipmap.password_enable : R.mipmap.password_disable);
            } else {
                boolean isEnable = registerAccountEdit.getText().length() > 0
                        && registerPasswordEdit.getText().length() > 0
                        && registerPasswordComfirmEdit.getText().length() > 0;
                updateLoginBtn(LoginActivity.this, logBtn, isEnable);
            }
        }
    };

    public static void start(Context context) {
        start(context, null);
    }

    public static void start(Context context, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public int getEmptyLayoutId() {
        return 0;
    }

    @Override
    public void initInject() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpStatusBar(android.R.color.white);

        setLogBtn();

        setupLoginPanel();

        setupRegisterPanel();

        initLoginListener();

        initRegisterListener();
    }

    private void setLogBtn() {
        logBtn = (TextView) findViewById(R.id.login_textview);

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (registerMode) {
                    register();
                } else {
                    login();
                }
            }
        });
    }

    private void setupLoginPanel() {
        logoImage = (ImageView) findViewById(R.id.logo_image);
        tipText = (TextView) findViewById(R.id.register_login_question);
        loginAccountEdit = (ClearEditText) findViewById(R.id.edit_login_account);
        loginPasswordEdit = (ClearEditText) findViewById(R.id.edit_login_password);

        loginAccountEdit.setIconResource(R.mipmap.username_disable);
        loginPasswordEdit.setIconResource(R.mipmap.password_disable);

        loginAccountEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(30)});
        loginPasswordEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
        loginAccountEdit.addTextChangedListener(textWatcher);
        loginPasswordEdit.addTextChangedListener(textWatcher);

        String account = PreferenceUtil.getUserAccount();
        loginAccountEdit.setText(account);

        loginPasswordEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    //隐藏键盘当edit失去焦点时候
                    InputMethodManager inputMethodManager = (InputMethodManager) MyApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(loginPasswordEdit.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                }
            }
        });
    }

    private void setupRegisterPanel() {
        loginLayout = findViewById(R.id.login_layout);
        registerLayout = findViewById(R.id.register_layout);
        switchModeBtn = (TextView) findViewById(R.id.register_login_tip_text);

        switchModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchMode();
            }
        });
    }


    private void updateLoginBtn(Context context, TextView logBtn, boolean isEnable) {
        logBtn.setText(registerMode ? R.string.register : R.string.login);
        logBtn.setBackgroundResource(R.drawable.login_btn_selector);
        logBtn.setEnabled(isEnable);
        logBtn.setTextColor(isEnable ? getResources().getColor(R.color.login_btn_enable) : getResources().getColor(R.color.login_btn_disable));
    }

    private void initLoginListener() {
        loginListener = new SubscriberOnNextListener<UserBean>() {
            @Override
            public void onNext(UserBean userBean) {
                if (userBean.getResultCode() == 1) {
                    saveLoginInfo(userBean.getList().getName(), userBean.getList().getPassword());
                    onLoginDone();

//                    if (userBean.getList().getFirst() == 0)
//                        ThemeChooseActivity.start(LoginActivity.this, null);
//                    else
//                        MainActivity.start(LoginActivity.this, null);

                } else {
                    onLoginDone();
                    Toast.makeText(LoginActivity.this, userBean.getResultMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private void initRegisterListener() {
        registerListener = new SubscriberOnNextListener<UserBean>() {

            @Override
            public void onNext(UserBean userBean) {
                Toast.makeText(LoginActivity.this, R.string.register_success, Toast.LENGTH_SHORT).show();
                switchMode();  // 切换回登录
                loginAccountEdit.setText(userBean.getList().getName());
                loginPasswordEdit.setText(userBean.getList().getPassword());

                registerAccountEdit.setText("");
                registerNickNameEdit.setText("");
                registerPasswordEdit.setText("");

                DialogManager.dismissProgressDialog();
            }
        };

    }

    private void login() {
        DialogManager.showProgressDialog(this, null, getString(R.string.logining), true, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                onLoginDone();
            }
        }).setCanceledOnTouchOutside(false);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                DialogManager.dismissProgressDialog();
            }
        };

        new Handler().postDelayed(runnable, 1000);

        final String account = loginAccountEdit.getEditableText().toString().toLowerCase();
        final String token = loginPasswordEdit.getEditableText().toString();

        //HttpMethods.getInstance().loginCertification(account, token, new ProgressSubscriber(loginListener, this));
        onLoginDone();
        //ThemeChooseActivity.start(LoginActivity.this, null);
    }

    private void onLoginDone() {
        DialogManager.dismissProgressDialog();
    }

    private void saveLoginInfo(final String account, final String token) {
        PreferenceUtil.saveUserAccount(account);
        PreferenceUtil.saveUserToken(token);
    }

    private void register() {
        if (!registerMode || !registerPanelInited) {
            return;
        }

        if (!checkRegisterContentValid()) {
            return;
        }

        if (!NetworkUtil.isNetworkAvailable()) {
            Toast.makeText(LoginActivity.this, R.string.network_is_not_available, Toast.LENGTH_SHORT).show();
            return;
        }

        DialogManager.showProgressDialog(this, getString(R.string.registering), false);

        final String account = registerAccountEdit.getText().toString();
        final String nickName = registerNickNameEdit.getText().toString();
        final String password = registerPasswordEdit.getText().toString();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                DialogManager.dismissProgressDialog();
            }
        };

        new Handler().postDelayed(runnable, 1000);

        HttpMethods.getInstance().registerCertification(nickName, password, null, account, null, new ProgressSubscriber(registerListener, this));
    }

    private boolean checkRegisterContentValid() {
        if (!registerMode || !registerPanelInited) {
            return false;
        }

        String account = registerAccountEdit.getText().toString().trim();
        if (account.length() != 11) {
            Toast.makeText(this, R.string.register_phone_tip, Toast.LENGTH_SHORT).show();

            return false;
        }

        String nick = registerNickNameEdit.getText().toString().trim();
        if (nick.length() < 4 || nick.length() > 30) {
            Toast.makeText(this, R.string.register_username_tip, Toast.LENGTH_SHORT).show();

            return false;
        }

        String password = registerPasswordEdit.getText().toString();
        if (password.length() < 6 || password.length() > 16) {
            Toast.makeText(this, R.string.register_password_tip, Toast.LENGTH_SHORT).show();

            return false;
        }

        String passwordConfirm = registerPasswordComfirmEdit.getText().toString();

        if (!password.equals(passwordConfirm)) {
            Toast.makeText(this, R.string.register_password_confirm_tip, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void switchMode() {
        registerMode = !registerMode;

        if (registerMode && !registerPanelInited) {
            account = (TextView) findViewById(R.id.login_account_textview);
            username = (TextView) findViewById(R.id.login_username_textview);
            password = (TextView) findViewById(R.id.login_password_textview);
            passwordConfirm = (TextView) findViewById(R.id.login_password_confirm_textview);

            registerAccountEdit = (ClearEditText) findViewById(R.id.edit_register_account);
            registerNickNameEdit = (ClearEditText) findViewById(R.id.edit_register_nickname);
            registerPasswordEdit = (ClearEditText) findViewById(R.id.edit_register_password);
            registerPasswordComfirmEdit = (ClearEditText) findViewById(R.id.edit_register_password_confirm);

            registerAccountEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
            registerNickNameEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(30)});
            registerPasswordEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
            registerPasswordComfirmEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});

            registerAccountEdit.addTextChangedListener(textWatcher);
            registerNickNameEdit.addTextChangedListener(textWatcher);
            registerPasswordEdit.addTextChangedListener(textWatcher);
            registerPasswordComfirmEdit.addTextChangedListener(textWatcher);

            registerPanelInited = true;
        }

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) logoImage.getLayoutParams();

        tipText.setVisibility(registerMode ? View.GONE : View.VISIBLE);
        loginLayout.setVisibility(registerMode ? View.GONE : View.VISIBLE);
        registerLayout.setVisibility(registerMode ? View.VISIBLE : View.GONE);
        switchModeBtn.setText(registerMode ? R.string.login_has_account : R.string.register_part1);

        if (registerMode) {
            layoutParams.setMargins(0, JUtils.dip2px(66), 0, 0);
            boolean isEnable = registerAccountEdit.getText().length() > 0
                    && registerPasswordEdit.getText().length() > 0
                    && registerPasswordComfirmEdit.getText().length() > 0;
            logBtn.setEnabled(isEnable);
            logBtn.setText(R.string.register);
        } else {
            layoutParams.setMargins(0, JUtils.dip2px(96), 0, 0);

            boolean isEnable = loginAccountEdit.getText().length() > 0
                    && loginPasswordEdit.getText().length() > 0;
            logBtn.setEnabled(isEnable);
            logBtn.setText(R.string.login);
        }

        logoImage.setLayoutParams(layoutParams);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
