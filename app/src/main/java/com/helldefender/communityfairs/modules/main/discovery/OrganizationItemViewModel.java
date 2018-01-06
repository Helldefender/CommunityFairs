package com.helldefender.communityfairs.modules.main.discovery;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.bindingadapter.ItemView;
import com.helldefender.communityfairs.bindingadapter.ItemViewWrapper;
import com.helldefender.communityfairs.bindingadapter.MultiViewType;

/**
 * Created by Helldefender on 2018/1/6 for CommunityFairs.
 * Function:
 * Description:
 */

public class OrganizationItemViewModel extends AndroidViewModel {

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
