package com.helldefender.communityfairs.modules.main.homepage;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableArrayList;
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

/**
 * Created by Helldefender on 2017/12/4 for CommunityFairs.
 * Function:
 * Description:
 */

public class HomePageViewModel extends AndroidViewModel {

    public ObservableList<NewsItemViewModel> viewModel = new ObservableArrayList<>();

    public ItemViewWrapper<NewsItemViewModel> itemViewWrapper = new ItemViewWrapper<NewsItemViewModel>(new MultiViewType<NewsItemViewModel>() {
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
        public void getLayoutRes(ItemView itemView, int position, NewsItemViewModel viewModel) {
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


    public HomePageViewModel(Application application) {
        super(application);
        viewModel.add(new NewsItemViewModel(application));
        viewModel.add(new NewsItemViewModel(application));
        viewModel.add(new NewsItemViewModel(application));
        viewModel.add(new NewsItemViewModel(application));
    }
}
