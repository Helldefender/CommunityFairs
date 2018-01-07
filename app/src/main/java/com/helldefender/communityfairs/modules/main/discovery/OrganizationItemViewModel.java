package com.helldefender.communityfairs.modules.main.discovery;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.bindingadapter.ItemView;
import com.helldefender.communityfairs.bindingadapter.ItemViewWrapper;
import com.helldefender.communityfairs.bindingadapter.MultiViewType;
import com.helldefender.communityfairs.bindingadapter.command.ReplyCommand;
import com.helldefender.communityfairs.modules.community.detail.NewsDetailActivity;
import com.helldefender.communityfairs.modules.main.discovery.organization.OrganizationActivity;

import io.reactivex.functions.Action;

/**
 * Created by Helldefender on 2018/1/6 for CommunityFairs.
 * Function:
 * Description:
 */

public class OrganizationItemViewModel extends AndroidViewModel {

    private Context mContext;

    public ReplyCommand moreClickCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
//            Intent intent = new Intent(mContext, OrganizationActivity.class);
//            mContext.startActivity(intent);
        }
    });

    public ObservableList<AndroidViewModel> viewModel = new ObservableArrayList<>();

    public ItemViewWrapper<AndroidViewModel> itemViewWrapper = new ItemViewWrapper<>(new MultiViewType<AndroidViewModel>() {

        @Override
        public int getViewTypeSpanCount(int viewType) {
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public int getLayoutRes(int viewType) {
            return 0;
        }

        @Override
        public void getLayoutRes(ItemView itemView, int position, AndroidViewModel viewModel) {
            itemView.set(BR.viewModel, R.layout.item_discovery_rv_organization_item);
        }

    });

    public OrganizationItemViewModel(@NonNull Application application) {
        super(application);
        mContext = application;
        viewModel.add(new OrganizationItemsViewModel(application));
        viewModel.add(new OrganizationItemsViewModel(application));
        viewModel.add(new OrganizationItemsViewModel(application));
        viewModel.add(new OrganizationItemsViewModel(application));
        viewModel.add(new OrganizationItemsViewModel(application));
        viewModel.add(new OrganizationItemsViewModel(application));
        viewModel.add(new OrganizationItemsViewModel(application));
        viewModel.add(new OrganizationItemsViewModel(application));
    }
}
