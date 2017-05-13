package com.helldefender.enjoylife.utils.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.helldefender.enjoylife.R;

/**
 * Created by Helldefender on 2017/4/10.
 */

public class ImageLoader {

    private Context context;

    private int type;

    private String url;

    private int placeHolder;

    private ImageView imageView;

    private int wifiStrategy;

    private boolean isRound;

    private int radius;

    public ImageLoader(Builder builder) {
        this.context = builder.context;
        this.type = builder.type;
        this.url = builder.url;
        this.placeHolder = builder.placeHolder;
        this.imageView = builder.imageView;
        this.wifiStrategy = builder.wifiStrategy;
        this.isRound = builder.isRound;
        this.radius = builder.radius;
    }

    public Context getContext() {
        return context;
    }

    public int getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public int getPlaceHolder() {
        return placeHolder;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getWifiStrategy() {
        return wifiStrategy;
    }

    public boolean isRound() {
        return isRound;
    }

    public int getRadius() {
        return radius;
    }

    public static class Builder {

        private Context context;

        private int type;

        private String url;

        private int placeHolder;

        private ImageView imageView;

        private int wifiStrategy;

        private boolean isRound;

        private int radius;

        public Builder() {
            this.type = ImageLoaderUtil.IMAGE_TYPE_SMALL;
            this.url = "";
            this.placeHolder = R.mipmap.ic_launcher;
            this.imageView = null;
            this.wifiStrategy = ImageLoaderUtil.IMAGE_LOAD_STRATEGY_NORMAL;
            this.isRound = false;
            this.radius = 0;
        }

        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public Builder type(int type) {
            this.type = type;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder placeHolder(int placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public Builder imageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public Builder strategy(int wifiStrategy) {
            this.wifiStrategy = wifiStrategy;
            return this;
        }

        public Builder isRound(boolean isRound) {
            this.isRound = isRound;
            return this;
        }

        public Builder radius(int radius) {
            this.radius = radius;
            return this;
        }

        public ImageLoader build() {
            return new ImageLoader(this);
        }
    }
}
