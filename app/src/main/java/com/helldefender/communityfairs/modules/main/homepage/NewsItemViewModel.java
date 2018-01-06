package com.helldefender.communityfairs.modules.main.homepage;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.helldefender.communityfairs.bindingadapter.command.ReplyCommand;
import com.helldefender.communityfairs.modules.community.detail.NewsDetailActivity;

import org.apache.http.entity.InputStreamEntity;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Helldefender on 2017/12/8 for CommunityFairs.
 * Function:
 * Description:
 */

public class NewsItemViewModel extends AndroidViewModel {

    private Context context;

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();

    public ReplyCommand itemClickCommand = new ReplyCommand(new Action() {
        @Override
        public void run() throws Exception {
            Intent intent = new Intent(context, NewsDetailActivity.class);
            context.startActivity(intent);
        }
    });


    public NewsItemViewModel(Application application) {
        super(application);
        this.context = application;
        imageUrl.set("http://www.taopic.com/uploads/allimg/130331/240460-13033106243430.jpg");
        title.set("我是一个标题");
    }
}
