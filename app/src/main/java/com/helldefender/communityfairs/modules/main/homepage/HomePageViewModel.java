package com.helldefender.communityfairs.modules.main.homepage;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.util.Log;

import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.app.BaseFragment;
import com.helldefender.communityfairs.bindingadapter.MultiViewType;
import com.helldefender.communityfairs.bindingadapter.ItemView;
import com.helldefender.communityfairs.bindingadapter.ItemViewWrapper;
import com.helldefender.communityfairs.bindingadapter.command.ReplyCommand;

import java.util.Calendar;
import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Helldefender on 2017/12/4 for CommunityFairs.
 * Function:
 * Description:
 */

public class HomePageViewModel extends AndroidViewModel {

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
                itemView.set(BR.viewModel, R.layout.item_homepage_rv_banner);
                return;
            }
            itemView.set(BR.viewModel, R.layout.item_homepage_rv_content);
        }

    });

    public final ReplyCommand onRefreshCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
            Observable.just(Calendar.getInstance())
                    .doOnNext(new Consumer<Calendar>() {
                        @Override
                        public void accept(Calendar calendar) throws Exception {
                            calendar.add(Calendar.DAY_OF_MONTH, 1);
                        }
                    })
                    .map(new Function<Calendar, Date>() {
                        @Override
                        public Date apply(Calendar calendar) throws Exception {
                            return calendar.getTime();
                        }
                    }).subscribe(new Consumer<Date>() {
                @Override
                public void accept(Date date) throws Exception {
                }
            });
        }
    });

    public final ReplyCommand<Integer> onLoadMoreCommand = new ReplyCommand<Integer>(new Consumer<Integer>() {
        @Override
        public void accept(Integer integer) throws Exception {

        }
    });

    public final ViewStyle viewStyle = new ViewStyle();

    public class ViewStyle {
        public final ObservableBoolean isRefreshing = new ObservableBoolean(true);
        public final ObservableBoolean progressRefreshing = new ObservableBoolean(true);
    }


    public HomePageViewModel(Application application) {
        super(application);
        viewModel.add(new BannerViewModel(application));
        viewModel.add(new NewsItemViewModel(application));
        viewModel.add(new NewsItemViewModel(application));
        viewModel.add(new NewsItemViewModel(application));
        viewModel.add(new NewsItemViewModel(application));
        viewModel.add(new NewsItemViewModel(application));
    }

    private void loadMore() {
//        viewStyle.isRefreshing.set(true);
//
//        // TODO: 2017/12/31  加载数据，尝试显示或是隐藏progressbar，或是设置recyclerview的状态，通过回调
//        viewStyle.isRefreshing.set(false);
    }
}
