package com.helldefender.communityfairs.bindingadapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.helldefender.communityfairs.app.App;
import com.helldefender.communityfairs.utils.imageloader.ImageLoader;
import com.helldefender.communityfairs.utils.imageloader.ImageLoaderConfiguration;

/**
 * Created by Helldefender on 2017/12/1 for CommunityFairs.
 * Function:
 * Description:
 */

public class ImageViewBindingAdapter {

    @BindingAdapter(value = {"url", "placeHolderRes", "errorRes", "modifyTime"}, requireAll = false)
    public static void loadImage(ImageView imageView, String url, int placeHodlerRes, int errorRes, int modifyTime) {
        ImageLoader.getInstance().loadImage(new ImageLoaderConfiguration.Builder()
                .context(App.getContext())
                .url(url)
                .imageView(imageView)
//                .placeHolderRes()
//                .errorRes()
                .build());
    }
}
