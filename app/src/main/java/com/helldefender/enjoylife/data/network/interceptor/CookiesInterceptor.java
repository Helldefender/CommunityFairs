package com.helldefender.enjoylife.data.network.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ：AceMurder
 * Created on ：2017/5/23
 * Created for : U-Lab.
 * Enjoy it !!!
 */

public class CookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }
}
