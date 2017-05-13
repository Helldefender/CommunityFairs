package com.helldefender.enjoylife.utils.imageloader;

/**
 * Created by Helldefender on 2017/4/9.
 */

public class ImageLoaderUtil {

    public static final int IMAGE_TYPE_SMALL = 0;

    public static final int IAMGE_TYPE_MIDDLE = 1;

    public static final int IMAGE_TYPE_LARGE = 2;

    public static final int IMAGE_LOAD_STRATEGY_NORMAL = 0;

    public static final int IMAGE_LOAD_STRATEGY_ONLY_WIFI = 1;

    public static boolean wifiFlag = false;

    //private static ImageLoaderUtil mImageLoaderUtil;

    private BaseImageLoadStrategy mBaseImageLoadStrategy;

    //@Inject
    public ImageLoaderUtil(BaseImageLoadStrategy baseImageLoadStrategy) {
        //mBaseImageLoadStrategy = new GlideImageLoadStrategy();
        mBaseImageLoadStrategy = baseImageLoadStrategy;
    }

//    public static ImageLoaderUtil getInstance() {
//        if (mImageLoaderUtil == null) {
//            synchronized (ImageLoaderUtil.class) {
//                if (mImageLoaderUtil == null) {
//                    mImageLoaderUtil = new ImageLoaderUtil();
//                }
//            }
//        }
//        return mImageLoaderUtil;
//    }

//    @Inject
//    public ImageLoaderUtil() {
//    }

    public void loadImage(ImageLoader imageLoader) {
        mBaseImageLoadStrategy.loadImage(imageLoader);
    }

    public void setImageLoadStrategy(BaseImageLoadStrategy baseImageLoadStrategy) {
        mBaseImageLoadStrategy = baseImageLoadStrategy;
    }
}
