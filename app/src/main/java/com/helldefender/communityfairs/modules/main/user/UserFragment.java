package com.helldefender.communityfairs.modules.main.user;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;

import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.app.BaseFragment;
import com.helldefender.communityfairs.app.ViewModelFactory;
import com.helldefender.communityfairs.databinding.FragmentUserBinding;
import com.helldefender.communityfairs.utils.DeviceUtil;
import com.helldefender.communityfairs.widget.translucent.TranslucentScrollView;


/**
 * Created by Helldefender on 2017/2/5.
 */

public class UserFragment extends BaseFragment<FragmentUserBinding, UserViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected int getVariableId() {
        return BR.viewModel;
    }

    @Override
    protected UserViewModel getViewModel() {
        return ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(UserViewModel.class);
    }

    @Override
    protected void initViews(final FragmentUserBinding binding, Bundle savedInstanceState) {
        binding.actionbarTranslucentMy.setData("我的", 0, null, 0, null, null);
        binding.actionbarTranslucentMy.setNeedTranslucent();
        binding.actionbarTranslucentMy.setStatusBarHeight(DeviceUtil.getStatusBarHeight());
        binding.scrollviewTranslucent.setTranslucentChangedListener(new TranslucentScrollView.TranslucentChangedListener() {
            @Override
            public void onTranslucentChanged(int transAlpha) {
                binding.actionbarTranslucentMy.tvTitle.setVisibility(transAlpha > 68 ? View.VISIBLE : View.GONE);
            }
        });
        binding.scrollviewTranslucent.setTransView(binding.actionbarTranslucentMy);
        binding.scrollviewTranslucent.setPullZoomView(binding.llHeader);
    }

}
