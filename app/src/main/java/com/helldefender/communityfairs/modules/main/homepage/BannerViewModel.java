package com.helldefender.communityfairs.modules.main.homepage;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

/**
 * Created by Helldefender on 2017/12/10 for CommunityFairs.
 * Function:
 * Description:
 */

public class BannerViewModel extends AndroidViewModel {

    public ObservableList<BannerItemViewModel> viewModels = new ObservableArrayList<>();

    public BannerViewModel(@NonNull Application application) {
        super(application);
        viewModels.add(new BannerItemViewModel(application));
        viewModels.add(new BannerItemViewModel(application));
        viewModels.add(new BannerItemViewModel(application));
        viewModels.add(new BannerItemViewModel(application));
    }
}
