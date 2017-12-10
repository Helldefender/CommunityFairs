package com.helldefender.communityfairs.modules.main.discovery;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.app.BaseFragment;
import com.helldefender.communityfairs.app.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by Helldefender on 2017/2/16.
 */

public class DiscoveryFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discovery;
    }

    @Override
    protected int getVariableId() {
        return BR.viewModel;
    }

    @Override
    protected ViewModel getViewModel() {
        return ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(DiscoveryViewModel.class);
    }

    @Override
    protected void initViews(ViewDataBinding binding, Bundle savedInstanceState) {

    }

    private void initRecyclerView() {
//            @Override
//            public int getItemViewType(int position) {
//                if (position == 0) {
//                    return DiscoveryRVAdapter.DISCOVERY_TYPE_ORGANIZATION__TITLE;
//                } else if (position == 2) {
//                    return DiscoveryRVAdapter.DISCOVERY_TYPE_ORGANIZATION;
//                } else if (position == 3) {
//                    return DiscoveryRVAdapter.DISCOVERY_TYPE_EVENT_TITLE;
//                }
//                return DiscoveryRVAdapter.DISCOVERT_TYPE_EVENT;
//            }
//
//            @Override
//            public int getLayoutId(int viewType) {
//                if (viewType == DiscoveryRVAdapter.DISCOVERY_TYPE_ORGANIZATION__TITLE) {
//
//                }else if(viewType == DiscoveryRVAdapter.DISCOVERY_TYPE_ORGANIZATION){
//
//                } else if (viewType == DiscoveryRVAdapter.DISCOVERY_TYPE_EVENT_TITLE) {
//
//                }
//                return
//            }
//        }, data));
    }
}
