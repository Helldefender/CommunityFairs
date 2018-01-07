package com.helldefender.communityfairs.modules.main.discovery;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.helldefender.communityfairs.bindingadapter.command.ReplyCommand;
import com.helldefender.communityfairs.modules.community.detail.NewsDetailActivity;
import com.helldefender.communityfairs.modules.main.discovery.organization.OrganizationActivity;

import io.reactivex.functions.Action;
import io.reactivex.internal.operators.maybe.MaybeConcatArrayDelayError;

/**
 * Created by Helldefender on 2018/1/6 for CommunityFairs.
 * Function:
 * Description:
 */

public class OrganizationItemsViewModel extends AndroidViewModel {

    private Context mContext;

    public ReplyCommand itemClickCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
            Intent intent = new Intent(mContext, OrganizationActivity.class);
            mContext.startActivity(intent);
        }
    });

    public OrganizationItemsViewModel(@NonNull Application application) {
        super(application);
        mContext = application;
    }
}
