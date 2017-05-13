package com.helldefender.enjoylife.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.ui.fragment.base.BaseFragment;

/**
 * Created by Helldefender on 2017/2/5.
 */

public class UserFragment extends BaseFragment {

    private TextView likeText;

    private TextView originalText;

    private TextView settingText;

    private TextView aboutText;

    private ImageView avatar;

    private TextView userNameText;

    private TextView introductions;

    private TextView fansNumText;

    private TextView focusNumText;

    private TextView logoutText;

    private Switch nightModeSwitch;

    @Override
    protected void initInject() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
//        setAvatar(view);
//
//        setOriginalArticle(view);
//
//        setSwitch(view);
//
//        setAbout(view);
//
//        setLogout(view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected int getEmptyLayoutId() {
        return 0;
    }

//    private void setAvatar(View view) {
//        avatar = (ImageView) view.findViewById(R.id.user_avatar);
//    }
//
//    private void setAbout(View view) {
//        aboutText = (TextView) view.findViewById(R.id.user_about);
//
//        aboutText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addFragment(AboutFragment.getInstance());
//            }
//        });
//    }
//
//    private void setOriginalArticle(View view) {
//        originalText = (TextView) view.findViewById(R.id.user_original);
//        likeText = (TextView) view.findViewById(R.id.user_like);
//
//        originalText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addFragment(OriginalArticleFragment.getInstance("我的原创"));
//            }
//        });
//
//        likeText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addFragment(OriginalArticleFragment.getInstance("我的喜欢"));
//            }
//        });
//    }
//
//    private void setSwitch(View view) {
//        nightModeSwitch = (Switch) view.findViewById(R.id.user_night_mode_switch);
//        nightModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                MainActivity mainActivity = (MainActivity) getHoldingActivity();
//                if (isChecked) {
//                    //mainActivity.dayNightMode();
//                } else {
//                    //mainActivity.dayNightMode();
//                }
//            }
//        });
//    }
//
//
//    private void setLogout(View view) {
//        logoutText = (TextView) view.findViewById(R.id.logout_textview);
//
//        logoutText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                PreferenceUtil.clearUserToken();
////                LoginActivity.start(getHoldingActivity(), null);
////                getHoldingActivity().finish();
//                Logger.d("点击了按钮，发布事件");
//                EventBus.getDefault().post(new LoggingStatusEvent(false));
//            }
//        });
//    }
}
