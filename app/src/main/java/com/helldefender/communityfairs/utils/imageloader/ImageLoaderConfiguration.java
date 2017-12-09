package com.helldefender.communityfairs.utils.imageloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Helldefender on 2017/4/10.
 */

public class ImageLoaderConfiguration {

    private Context context;

    private String url;

    private int placeHolderRes;

    private int errorRes;

    private ImageView imageView;

    private int picType; //图片加载类型(原图、略缩图)

    private int loadStrategy; //图片加载策略（是否在网络情况下加载图片）

    private long modifyTime;

    public ImageLoaderConfiguration(Builder builder) {
        this.context = builder.context;
        this.url = builder.url;
        this.placeHolderRes = builder.placeHolderRes;
        this.errorRes = builder.errorRes;
        this.imageView = builder.imageView;
        this.picType = builder.picType;
        this.loadStrategy = builder.loadStrategy;
        this.modifyTime = builder.modifyTime;
    }

    public Context getContext() {
        return context;
    }

    public String getUrl() {
        return url;
    }

    public int getPlaceHolderRes() {
        return placeHolderRes;
    }

    public int getErrorRes() {
        return errorRes;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getPicType() {
        return picType;
    }

    public int getLoadStrategy() {
        return loadStrategy;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public static class Builder {

        private Context context;

        private String url;

        private int placeHolderRes;

        private int errorRes;

        private ImageView imageView;

        private int picType;

        private int loadStrategy;

        private long modifyTime;

        public Builder() {
            this.url = "";
            this.placeHolderRes = -1;
            this.errorRes = -1;
            this.imageView = null;
            this.picType = ImageLoader.PIC_TYPE_DEFAULT;
            this.loadStrategy = ImageLoader.IMAGE_LOADER_STRATEGY_DEFAULT;
            this.modifyTime = -1;
        }

        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder imageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public Builder placeHolderRes(int placeHolderRes) {
            this.placeHolderRes = placeHolderRes;
            return this;
        }

        public Builder errorRes(int errorRes) {
            this.errorRes = errorRes;
            return this;
        }

        public Builder picType(int picType) {
            this.picType = picType;
            return this;
        }

        public Builder imgLoadStrategy(int loadStrategy) {
            this.loadStrategy = loadStrategy;
            return this;
        }

        public Builder modifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
            return this;
        }

        public ImageLoaderConfiguration build() {
            return new ImageLoaderConfiguration(this);
        }
    }
}
