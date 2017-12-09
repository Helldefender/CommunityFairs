package com.helldefender.communityfairs.utils.imageloader;

import android.content.Context;

/**
 * Created by Helldefender on 2017/4/9.
 */

public class ImageLoader {

    public static final int PIC_TYPE_DEFAULT = 0;

    public static final int IMAGE_LOADER_STRATEGY_DEFAULT = 0;

    public static final int IMAGE_LOADER_STRATEGY_ONLY_NETWORK = 1;

    public static final int IMAGE_LOADER_STRATEGY_ONLY_WIFI = 2;

    private static ImageLoader mInstance;

    private BaseImageLoaderStrategy mImageLoadStrategy;

    public ImageLoader() {
        mImageLoadStrategy = new GlideImageLoaderStrategy();
    }


    public ImageLoader(BaseImageLoaderStrategy baseImageLoaderStrategy) {
        mImageLoadStrategy = baseImageLoaderStrategy;
    }

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader();
                }
            }
        }
        return mInstance;
    }

    public void setImageLoaderStrategy(BaseImageLoaderStrategy baseImageLoaderStrategy) {
        mImageLoadStrategy = baseImageLoaderStrategy;
    }

    public void loadImage(ImageLoaderConfiguration imageLoaderConfiguration) {
        mImageLoadStrategy.loadImage(imageLoaderConfiguration);
    }

    public void clearImageDiskCache(Context context) {
        mImageLoadStrategy.clearImageDiskCache(context);
    }

    public void clearImageMemoryCache(Context context) {
        mImageLoadStrategy.clearImageMemoryCache(context);
    }

    public void trimMemory(Context context, int level) {
        mImageLoadStrategy.trimMemory(context, level);
    }

    public void clearImageAllCache(Context context) {
        clearImageDiskCache(context.getApplicationContext());
        clearImageMemoryCache(context.getApplicationContext());
    }

    public String getCacheSize(Context context) {
        return mImageLoadStrategy.getCacheSize(context);
    }
}
