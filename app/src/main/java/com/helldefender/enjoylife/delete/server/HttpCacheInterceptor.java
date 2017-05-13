package com.helldefender.enjoylife.delete.server;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.helldefender.enjoylife.app.MyApplication;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Helldefender on 2016/12/1.
 */

public class HttpCacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!isNetworkAvailable()) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }
        Response originalResponse = chain.proceed(request);
        if (isNetworkAvailable()) {
            //String cacheControl = request.cacheControl().toString();
            return originalResponse.newBuilder()
                    .header("Cache-Control", "max-age=60")
                    .removeHeader("Pragma")
                    .build();
        } else {
            int maxAge = 60 * 60;
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public,only-if-cached,max-age=" + maxAge)
                    .removeHeader("Pragma")
                    .build();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivity = (ConnectivityManager) MyApplication.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request request = chain.request();
//        Response originalResponse = chain.proceed(request);
//        return originalResponse.newBuilder()
//                .header("Cache-Control", "max-age=60")
//                .removeHeader("Pragma")
//                .build();
//    }
}
