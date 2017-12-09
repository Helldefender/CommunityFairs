package com.helldefender.communityfairs.utils.imageloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Helldefender on 2017/4/10.
 */

public interface BaseImageLoaderStrategy {

    void loadImage(ImageLoaderConfiguration imageLoaderConfiguration);

    void clearImageDiskCache(final Context context);

    void clearImageMemoryCache(Context context);

    //根据不同的内存状态来响应不同的内存释放策略
    void trimMemory(Context context, int level);

    String getCacheSize(Context context);

    void loadImage(ImageView imageView, String url);

    void loadImage(Context context, ImageView imageView, String url);

    void loadImage(ImageView imageView, String url, int placeHolderRes);

    void loadImage(Context context, ImageView imageView, String url, int placeHolderRes);

    void loadImage(ImageView imageView, String url, int placeHolderRes, int errorRes);

    void loadImage(ImageView imageView, String url, long modifyTime);

    void loadImage(ImageView imageView, String url, long modifyTime, int placeHolderRes);

    void loadImage(ImageView imageView, String url, long modifyTime, int placeHolderRes, int errorRes);

    //void loadGif(ImageView imageView, String url);
}
