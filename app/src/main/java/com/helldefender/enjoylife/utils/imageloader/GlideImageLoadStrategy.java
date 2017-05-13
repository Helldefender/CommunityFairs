package com.helldefender.enjoylife.utils.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.stream.StreamModelLoader;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.helldefender.enjoylife.utils.NetworkUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Helldefender on 2017/4/10.
 */

public class GlideImageLoadStrategy implements BaseImageLoadStrategy {

    private Context mContext;

    @Override
    public void loadImage(ImageLoader imageLoader) {
        mContext = imageLoader.getContext();

        //若没有设置仅在wifi下加载图片
        if (!ImageLoaderUtil.wifiFlag) {
            loadImage(imageLoader);
            return;
        }

        int mImageLoadStrategy = imageLoader.getWifiStrategy();
        if (mImageLoadStrategy == ImageLoaderUtil.IMAGE_LOAD_STRATEGY_ONLY_WIFI) {
            //在有wifi的情况下，默认只加载默认图片
            //判断当前网络环境是否有Wifi
            //如果网络环境为wifi的话才记载图片（当前模式为仅在wifi模式下加载图片）
            if (NetworkUtil.isWifiAvailable()) {
                loadNormal(imageLoader);
            } else {
                loadCache(imageLoader);
            }
        } else {
            loadNormal(imageLoader);
        }
    }

    private void loadNormal(ImageLoader imageLoader) {
        Glide.with(mContext).load(imageLoader.getUrl())
                .placeholder(imageLoader.getPlaceHolder())
                .into(imageLoader.getImageView());
    }

    private void loadRound(final ImageLoader imageLoader) {
        Glide.with(imageLoader.getContext()).load(imageLoader.getUrl()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable imageDrawable = new BitmapDrawable(resource);
                Bitmap output = Bitmap.createBitmap(imageLoader.getRadius(), imageLoader.getRadius(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(output);
                RectF outerRect = new RectF(0, 0, imageLoader.getRadius(), imageLoader.getRadius());
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setColor(Color.RED);
                canvas.drawRoundRect(outerRect, imageLoader.getRadius(), imageLoader.getRadius(), paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                imageDrawable.setBounds(0, 0, imageLoader.getRadius(), imageLoader.getRadius());
                canvas.saveLayer(outerRect, paint, Canvas.ALL_SAVE_FLAG);
                imageDrawable.draw(canvas);
                canvas.restore();
                imageLoader.getImageView().setImageBitmap(output);
            }
        });
    }

    private void loadCache(ImageLoader imageLoader) {
        Glide.with(mContext).using(new StreamModelLoader<String>() {
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
                .load(imageLoader.getUrl())
                .placeholder(imageLoader.getPlaceHolder())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageLoader.getImageView());
    }
}
