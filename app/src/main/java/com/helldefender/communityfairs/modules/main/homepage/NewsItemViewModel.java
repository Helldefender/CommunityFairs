package com.helldefender.communityfairs.modules.main.homepage;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableField;

import com.helldefender.communityfairs.bindingadapter.command.ReplyCommand;

import io.reactivex.functions.Action;

/**
 * Created by Helldefender on 2017/12/8 for CommunityFairs.
 * Function:
 * Description:
 */

public class NewsItemViewModel extends AndroidViewModel {

    ObservableField<String> title = new ObservableField<>();
    ObservableField<String> data = new ObservableField<>();

    public ReplyCommand itemClickCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {

        }
    });

    public NewsItemViewModel(Application application) {
        super(application);

    }
}
