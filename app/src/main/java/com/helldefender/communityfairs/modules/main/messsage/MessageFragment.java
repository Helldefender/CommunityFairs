package com.helldefender.communityfairs.modules.main.messsage;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.app.BaseFragment;
import com.helldefender.communityfairs.app.ViewModelFactory;
import com.helldefender.communityfairs.databinding.FragmentMessageBinding;
import com.helldefender.communityfairs.utils.DeviceUtil;

/**
 * Created by Helldefender on 2017/2/5.
 */

public class MessageFragment extends BaseFragment<FragmentMessageBinding, MessageViewModel> {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected int getVariableId() {
        return BR.viewModel;
    }

    @Override
    protected MessageViewModel getViewModel() {
        return ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(MessageViewModel.class);
    }

    @Override
    protected void initViews(FragmentMessageBinding binding, Bundle savedInstanceState) {
        binding.actionbarTranslucentMessage.setData("消息", 0, null, 0, null, null);
        binding.actionbarTranslucentMessage.setStatusBarHeight(DeviceUtil.getStatusBarHeight());
    }

}
