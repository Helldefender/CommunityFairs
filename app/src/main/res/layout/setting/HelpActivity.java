package com.acemurder.u_lab.modules.setting;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.acemurder.u_lab.R;
import com.acemurder.u_lab.base.BaseActivity;
import com.acemurder.u_lab.base.BaseFragment;
import com.acemurder.u_lab.modules.main.search.SearchActivity;

import butterknife.BindView;

/**
 * Created by Helldefender on 2017/6/7.
 */

public class HelpActivity extends BaseActivity {

    @BindView(R.id.tv_drawer_setting_help_title)
    TextView mTitleTv;

    @BindView(R.id.et_drawer_setting_help_search)
    EditText mSearchEt;

    @BindView(R.id.tv_drawer_setting_help_use)
    TextView mUseTv;

    @BindView(R.id.tv_drawer_setting_help_borrow)
    TextView mBorrowTv;

    @BindView(R.id.tv_drawer_setting_help_suggest)
    TextView mSuggestTv;


    @Override
    protected int getFragmentLayoutId() {
        return 0;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_help;
    }

    @Override
    protected BaseFragment getFragment() {
        return null;
    }

    @Override
    protected void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.tv_drawer_setting_help_title:
                finish();
                break;
            case R.id.tv_drawer_setting_help_use:
                startActivity(UsingHelpActivity.class);
                break;
            case R.id.tv_drawer_setting_help_borrow:
                startActivity(UsingHelpActivity.class);
                break;
            case R.id.tv_drawer_setting_help_suggest:
                startActivity(com.acemurder.u_lab.modules.setting.FeedBackActivity.class);
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTitleTv.setOnClickListener(this);
        mUseTv.setOnClickListener(this);
        mBorrowTv.setOnClickListener(this);
        mSuggestTv.setOnClickListener(this);

        mSearchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    ((InputMethodManager) mSearchEt.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(com.acemurder.u_lab.modules.setting.HelpActivity.this
                                            .getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    startActivity(SearchActivity.class);
                    return true;
                }
                return false;
            }
        });
    }
}
