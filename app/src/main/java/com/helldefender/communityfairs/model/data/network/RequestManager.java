package com.helldefender.communityfairs.model.data.network;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.helldefender.communityfairs.BuildConfig;
import com.helldefender.communityfairs.app.App;
import com.helldefender.communityfairs.config.Const;
import com.helldefender.communityfairs.config.Contract;
import com.helldefender.communityfairs.model.entity.ApiWrapper;
import com.helldefender.communityfairs.model.entity.Key;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

;

/**
 * Created by ：AceMurder
 * Created on ：2017/5/5
 * Created for : U-Lab.
 * Enjoy it !!!
 */
//网络请求类
public enum RequestManager {
    INSTANCE;

    private DataProvider provider;


    /**
     * 统一订阅
     *
     * @param observable
     * @param observer
     * @param <T>        接口返回数据的类型
     */
    private <T> void emitObservable(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
