package com.helldefender.communityfairs.modules.main.discovery.organization;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.helldefender.communityfairs.bindingadapter.command.ReplyCommand;
import com.helldefender.communityfairs.modules.community.detail.NewsDetailActivity;

import io.reactivex.functions.Action;

/**
 * Created by Helldefender on 2018/1/7 for CommunityFairs.
 * Function:
 * Description:
 */

public class OrganizationItemViewModel extends AndroidViewModel {

    private Context mContext;

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();

    public ReplyCommand itemClickCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
            Intent intent = new Intent(mContext, NewsDetailActivity.class);
            mContext.startActivity(intent);
        }
    });

    public OrganizationItemViewModel(@NonNull Application application) {
        super(application);
        this.mContext = application;
        imageUrl.set("http://www.taopic.com/uploads/allimg/130331/240460-13033106243430.jpg");
        title.set("我是一个标题");
    }
}
