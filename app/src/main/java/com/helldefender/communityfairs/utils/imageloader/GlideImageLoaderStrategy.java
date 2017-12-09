package com.helldefender.communityfairs.utils.imageloader;

import android.content.Context;
import android.os.Looper;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.stream.StreamModelLoader;
import com.bumptech.glide.signature.StringSignature;
import com.helldefender.communityfairs.utils.DeviceUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Helldefender on 2017/4/10.
 */

public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy {

    private Context mContext;

    @Override
    public void loadImage(ImageLoaderConfiguration imageLoaderConfiguration) {
        mContext = imageLoaderConfiguration.getContext();

        if (imageLoaderConfiguration.getModifyTime() != -1) {
            loadCacheInvalidate(imageLoaderConfiguration);
            return;
        }

        if (imageLoaderConfiguration.getLoadStrategy() == ImageLoader.IMAGE_LOADER_STRATEGY_ONLY_NETWORK) {
            if (DeviceUtil.isNetworkAvailable()) {
                loadDefault(imageLoaderConfiguration);
            } else {
                loadCache(imageLoaderConfiguration);
            }
        } else if (imageLoaderConfiguration.getLoadStrategy() == ImageLoader.IMAGE_LOADER_STRATEGY_ONLY_WIFI) {
            if (DeviceUtil.isWifiAvailable()) {
                loadDefault(imageLoaderConfiguration);
            } else {
                loadCache(imageLoaderConfiguration);
            }
        } else {
            loadDefault(imageLoaderConfiguration);
        }
    }

    @Override
    public void clearImageDiskCache(final Context context) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Glide.get(context.getApplicationContext()).clearDiskCache();
                }
            }).start();
        } else {
            Glide.get(context.getApplicationContext()).clearDiskCache();
        }
    }

    @Override
    public void clearImageMemoryCache(Context context) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Glide.get(context.getApplicationContext()).clearMemory();
        }
    }

    @Override
    public void trimMemory(Context context, int level) {
        Glide.get(context).trimMemory(level);
    }

    @Override
    public String getCacheSize(Context context) {
        try {
            return DeviceUtil.getFormatSize(DeviceUtil.getFolderSize(Glide.getPhotoCacheDir(context.getApplicationContext())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void loadImage(ImageView imageView, String url) {
        loadImage(imageView.getContext(), imageView, url);
    }

    @Override
    public void loadImage(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url).dontAnimate().into(imageView);
    }

    @Override
    public void loadImage(ImageView imageView, String url, int placeHolderRes) {
        loadImage(imageView.getContext(), imageView, url, placeHolderRes);
    }

    @Override
    public void loadImage(Context context, ImageView imageView, String url, int placeHolderRes) {
        Glide.with(context).load(url).dontAnimate().placeholder(placeHolderRes).into(imageView);
    }

    @Override
    public void loadImage(ImageView imageView, String url, int placeHolderRes, int errorRes) {
        Glide.with(imageView.getContext()).load(url).dontAnimate().placeholder(placeHolderRes).error(errorRes).into(imageView);
    }

    @Override
    public void loadImage(ImageView imageView, String url, long modifyTime) {
        Glide.with(imageView.getContext()).load(url).dontAnimate().signature(new StringSignature(String.valueOf(modifyTime))).into(imageView);
    }

    @Override
    public void loadImage(ImageView imageView, String url, long modifyTime, int placeHolderRes) {
        Glide.with(imageView.getContext()).load(url).dontAnimate()
                .signature(new StringSignature(String.valueOf(modifyTime)))
                .placeholder(placeHolderRes).into(imageView);
    }

    @Override
    public void loadImage(ImageView imageView, String url, long modifyTime, int placeHolderRes, int errorRes) {
        Glide.with(imageView.getContext()).load(url).dontAnimate()
                .signature(new StringSignature(String.valueOf(modifyTime)))
                .placeholder(placeHolderRes).error(errorRes).into(imageView);
    }

    private void loadDefault(ImageLoaderConfiguration imageLoaderConfiguration) {
        if (imageLoaderConfiguration.getPlaceHolderRes() != -1 && imageLoaderConfiguration.getErrorRes() != -1) {
            loadImage(imageLoaderConfiguration.getImageView(), imageLoaderConfiguration.getUrl(), imageLoaderConfiguration.getPlaceHolderRes(), imageLoaderConfiguration.getErrorRes());
        } else if (imageLoaderConfiguration.getPlaceHolderRes() != -1) {
            loadImage(imageLoaderConfiguration.getImageView(), imageLoaderConfiguration.getUrl(), imageLoaderConfiguration.getPlaceHolderRes());
        } else if (imageLoaderConfiguration.getErrorRes() != -1) {
            loadImage(imageLoaderConfiguration.getImageView(), imageLoaderConfiguration.getUrl(), imageLoaderConfiguration.getErrorRes());
        } else {
            loadImage(imageLoaderConfiguration.getImageView(), imageLoaderConfiguration.getUrl());
        }
    }

    private void loadCache(ImageLoaderConfiguration imageLoaderConfiguration) {
        Glide.with(mContext).
                using(new StreamModelLoader<String>() {
                    @Override
                    public DataFetcher<InputStream> getResourceFetcher(final String model, int width, int height) {
                        return new DataFetcher<InputStream>() {
                            @Override
                            public InputStream loadData(Priority priority) throws Exception {
                                throw new IOException();
                            }

                            @Override
                            public void cleanup() {

                            }

                            @Override
                            public String getId() {
                                return model;
                            }

                            @Override
                            public void cancel() {

                            }
                        };
                    }
                })
                .load(imageLoaderConfiguration.getUrl())
                .dontAnimate()
                .placeholder(imageLoaderConfiguration.getPlaceHolderRes())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageLoaderConfiguration.getImageView());
    }

    private void loadCacheInvalidate(ImageLoaderConfiguration imageLoaderConfiguration) {
        if (imageLoaderConfiguration.getPlaceHolderRes() != -1 && imageLoaderConfiguration.getErrorRes() != -1) {
            loadImage(imageLoaderConfiguration.getImageView(), imageLoaderConfiguration.getUrl(), imageLoaderConfiguration.getModifyTime(), imageLoaderConfiguration.getPlaceHolderRes(), imageLoaderConfiguration.getErrorRes());
        } else if (imageLoaderConfiguration.getPlaceHolderRes() != -1) {
            loadImage(imageLoaderConfiguration.getImageView(), imageLoaderConfiguration.getUrl(), imageLoaderConfiguration.getModifyTime(), imageLoaderConfiguration.getPlaceHolderRes());
        } else {
            loadImage(imageLoaderConfiguration.getImageView(), imageLoaderConfiguration.getUrl(), imageLoaderConfiguration.getModifyTime());
        }

    }
}
