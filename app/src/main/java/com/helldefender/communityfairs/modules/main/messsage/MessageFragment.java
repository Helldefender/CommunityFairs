package com.helldefender.communityfairs.modules.main.messsage;

import android.arch.lifecycle.ViewModel;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.app.BaseFragment;

/**
 * Created by Helldefender on 2017/2/5.
 */

public class MessageFragment extends BaseFragment  {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

    @Override
    protected ViewModel getViewModel() {
        return null;
    }

    @Override
    protected void initViews(ViewDataBinding binding, Bundle savedInstanceState) {

    }
}
