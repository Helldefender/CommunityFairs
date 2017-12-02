package com.helldefender.enjoylife.modules.main.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.modules.community.attend.MyAttendActivity;
import com.helldefender.enjoylife.modules.community.publish.MyPublishActivity;
import com.helldefender.enjoylife.modules.user.setting.AboutActivity;
import com.helldefender.enjoylife.modules.user.setting.SettingActivity;
import com.helldefender.enjoylife.app.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Helldefender on 2017/2/5.
 */

public class UserFragment extends BaseFragment {

    @BindView(R.id.img_user_avatar)
    ImageView imgUserAvatar;

    @BindView(R.id.frame_user_avatarContainer)
    FrameLayout frameUserAvatarContainer;

    @BindView(R.id.tv_user_followers)
    TextView tvUserFollowers;

    @BindView(R.id.tv_user_following)
    TextView tvUserFollowing;

    @BindView(R.id.ll_user_followStatus)
    LinearLayout llUserFollowStatus;

    @BindView(R.id.ll_user_userInfo)
    LinearLayout llUserUserInfo;

    @BindView(R.id.tv_user_userName)
    TextView tvUserUserName;

    @BindView(R.id.tv_user_location)
    TextView tvUserLocation;

    @BindView(R.id.tv_user_organization)
    TextView tvUserOrganization;

    @BindView(R.id.tv_user_popular)
    TextView tvUserPopular;

    @BindView(R.id.tv_user_favorite)
    TextView tvUserFavorite;

    @BindView(R.id.tv_user_attend)
    TextView tvUserAttend;

    @BindView(R.id.tv_user_release)
    TextView tvUserRelease;

    @BindView(R.id.tv_user_setting)
    TextView tvUserSetting;

    @BindView(R.id.tv_user_about)
    TextView tvUserAbout;

    @BindView(R.id.tv_user_logout)
    TextView tvUserLogout;

    Unbinder unbinder;

    @Override
    protected void initInject() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        tvUserRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHoldingActivity().startActivity(MyPublishActivity.class);
            }
        });

        tvUserAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHoldingActivity().startActivity(AboutActivity.class);
            }
        });

        tvUserAttend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHoldingActivity().startActivity(MyAttendActivity.class);
            }
        });

        tvUserSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHoldingActivity().startActivity(SettingActivity.class);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected int getEmptyLayoutId() {
        return 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
