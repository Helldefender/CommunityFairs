package com.helldefender.communityfairs.modules.community.detail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.helldefender.communityfairs.R;

/**
 * Created by Helldefender on 2018/1/2 for CommunityFairs.
 * Function:
 * Description:
 */

public class ContentViewModel extends AndroidViewModel {

    private Context context;

    public ObservableField<String> title = new ObservableField<>();

    public ContentViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        title.set(context.getString(R.string.test_content));
    }
}
