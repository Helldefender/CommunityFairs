package com.helldefender.communityfairs.modules.main.homepage;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.helldefender.communityfairs.model.entity.Image;

/**
 * Created by Helldefender on 2017/12/10 for CommunityFairs.
 * Function:
 * Description:
 */

public class BannerItemViewModel extends AndroidViewModel {

    Image image;

    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> title = new ObservableField<>();

    public BannerItemViewModel(@NonNull Application application) {
        super(application);

        image = new Image();
        imageUrl.set(image.imageUrl);
        title.set(image.title);
    }
}
