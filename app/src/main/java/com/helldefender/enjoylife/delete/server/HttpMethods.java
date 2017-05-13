package com.helldefender.enjoylife.delete.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.helldefender.enjoylife.app.MyApplication;
import com.helldefender.enjoylife.config.HttpApiConfig;
import com.helldefender.enjoylife.delete.server.entity.UserBean;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Helldefender on 2016/10/27.
 */

public class HttpMethods {

    private HttpApi httpApi;

    private Retrofit retrofit;

    private static final int DEFAUTL_TIMEOUT = 10;

    private HttpMethods() {
        File cacheFile = new File(MyApplication.getInstance().getCacheDir(), "Cache");
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(cacheFile, cacheSize);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAUTL_TIMEOUT, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(new LogInterceptor())
                .addInterceptor(new HttpCacheInterceptor())
                .build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        retrofit = new Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(HttpApiConfig.BASE_URL)
                .build();
        httpApi = retrofit.create(HttpApi.class);
    }

    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void getRecommendContent(Subscriber subscriber, String types) {
        toSubscribe(httpApi.getRecommendContent(types), subscriber);
    }

    public void getTypeContent(Subscriber subscriber, int type) {
        toSubscribe(httpApi.getTypeContent(type), subscriber);
    }

    public void postImage(String imagePath, Subscriber subscriber) {
        File file = new File(imagePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("picture", file.getName(), requestFile);
        toSubscribe(httpApi.imageUpload(body), subscriber);
    }

    public void postArticle(int type, String title, String content, String url, Subscriber subscriber) {
        toSubscribe(httpApi.articleUpload(type, title, content, url), subscriber);
    }

    public void postSearchKey(String keyWord, Subscriber subscriber) {
        toSubscribe(httpApi.getSearchResult(keyWord), subscriber);
    }

    public void postComment(String comment, Subscriber subscriber) {
        //toSubscribe(,subscriber);
    }

    public void loginCertification(String user, String password, Subscriber subscriber) {
        toSubscribe(httpApi.login(user, password), subscriber);
    }

    public void registerCertification(String name, String password, String type, String phone, String photo, Subscriber subscriber) {
        toSubscribe(httpApi.register(name, password, type, phone, photo), subscriber);
    }

    public void updateUserInfo(UserBean.ListBean type, Subscriber subscriber) {
        toSubscribe(httpApi.updateUser(type), subscriber);
    }

    private void toSubscribe(Observable observable, Subscriber subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


}
