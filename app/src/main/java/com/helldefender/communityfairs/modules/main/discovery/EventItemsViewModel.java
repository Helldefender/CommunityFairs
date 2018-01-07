package com.helldefender.communityfairs.modules.main.discovery;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.helldefender.communityfairs.bindingadapter.command.ReplyCommand;
import com.helldefender.communityfairs.modules.community.detail.NewsDetailActivity;

import io.reactivex.functions.Action;

/**
 * Created by Helldefender on 2018/1/6 for CommunityFairs.
 * Function:
 * Description:
 */

public class EventItemsViewModel extends AndroidViewModel {

    private Context mContext;

    public EventItemsViewModel(@NonNull Application application) {
        super(application);
        mContext = application;
    }
}
