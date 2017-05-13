package com.helldefender.enjoylife.delete.server;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Helldefender on 2016/12/1.
 */

public class LogInterceptor implements Interceptor {
    private static String TAG = "LogInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long time1 = System.nanoTime();
        Response response = chain.proceed(request);
        long time2 = System.nanoTime();
        double time = time2 - time1 / 1e6d;
        MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Log.d(TAG, "time " + time
                + "\nurl " + response.request().url()
                + "\nheaders " + response.headers()
                + "\nmediaType " + mediaType
                + "\ncontent" + content);
        return response.newBuilder()
                .body(ResponseBody.create(mediaType, content))
                .build();
    }
}
