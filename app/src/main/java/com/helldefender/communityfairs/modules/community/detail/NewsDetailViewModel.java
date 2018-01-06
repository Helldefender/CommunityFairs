package com.helldefender.communityfairs.modules.community.detail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.bindingadapter.ItemView;
import com.helldefender.communityfairs.bindingadapter.ItemViewWrapper;
import com.helldefender.communityfairs.bindingadapter.MultiViewType;

/**
 * Created by Helldefender on 2018/1/1 for CommunityFairs.
 * Function:
 * Description:
 */

public class NewsDetailViewModel extends AndroidViewModel {

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();

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
            if (position == 0) {
                itemView.set(BR.viewModel, R.layout.item_detail_rv_content);
                return;
            }
            itemView.set(BR.viewModel, R.layout.item_detail_rv_comment);
        }

    });


    public NewsDetailViewModel(@NonNull Application application) {
        super(application);
        viewModel.add(new ContentViewModel(application));
        viewModel.add(new CommentViewModel(application));
        imageUrl.set("http://www.taopic.com/uploads/allimg/130331/240460-13033106243430.jpg");
        title.set("我是一个标题");
    }
}
