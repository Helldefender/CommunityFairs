package com.helldefender.communityfairs.modules.main;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.bindingadapter.command.ReplyCommand;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Helldefender on 2017/12/4 for CommunityFairs.
 * Function:
 * Description:
 */

public class MainViewModel extends AndroidViewModel {

    private static final int TAB_HOMEPAGE = 0;

    private static final int TAB_DISCOVERY = 1;

    private static final int TAB_MESSAGE = 2;

    private static final int TAB_USER = 3;

    public final ReplyCommand<View> replyCommand = new ReplyCommand<View>(new Consumer<View>() {
        @Override
        public void accept(View view) throws Exception {
//            switch (view.getId()) {
//                case R.id.radio_homepage:
//                    viewpager.setCurrentItem(TAB_HOMEPAGE, false);
//                    break;
//                case R.id.radio_discovery:
//                    viewpager.setCurrentItem(TAB_DISCOVERY, false);
//                    break;
//                case R.id.radio_message:
//                    viewpager.setCurrentItem(TAB_MESSAGE, false);
//                    break;
//                case R.id.radio_user:
//                    viewpager.setCurrentItem(TAB_USER, false);
//                    break;
//                case R.id.img_posted:
//                    //PublishActivity.start(MainActivity.this, null);
//                    break;
//            }
        }
    });

    public MainViewModel(@NonNull Application application) {
        super(application);
    }
}

