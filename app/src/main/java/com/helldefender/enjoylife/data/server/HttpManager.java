package com.helldefender.enjoylife.data.server;

import android.content.Context;
import android.text.TextUtils;

import com.helldefender.enjoylife.config.HttpApiConfig;
import com.helldefender.enjoylife.data.server.interceptors.DynamicParameterInterceptor;
import com.helldefender.enjoylife.data.server.interceptors.HeadInterceptor;
import com.helldefender.enjoylife.data.server.interceptors.ParameterInterceptor;
import com.helldefender.enjoylife.inject.scope.PerApp;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscription;


/**
 * Created by Helldefender on 2017/2/8.
 */
@PerApp
public class HttpManager {

    private static final String TAG = HttpManager.class.getSimpleName();

    //public static HttpManager instance = new HttpManager();

    private Context mContext;

    private boolean isShowWaitingDialog;

    private Observable observable;

    //private static WeakReference<Context> weakReference;

    @Inject
    public HttpManager() {

    }

    public HttpManager with(Context context) {
        //weakReference = new WeakReference<Context>(context);
        //mContext = weakReference.get();
        //return instance;
        mContext = context;
        return this;
    }

    public HttpManager setShowWaitingDialog(boolean isShowWaitingDialog) {
        this.isShowWaitingDialog = isShowWaitingDialog;
        //return instance;
        return this;
    }

    public HttpManager setObservable(Observable observable) {
        this.observable = observable;
        //return instance;
        return this;
    }

    public Subscription subscriber(ProgressSubscriber progressSubscriber) {
        progressSubscriber.setContext(mContext);
        progressSubscriber.setShowWaitDialog(isShowWaitingDialog);
         return observable.subscribe(progressSubscriber);
        //return instance;
        //return this;
    }

    public static class RetrofitBuilder {
        private Retrofit.Builder rtBuilder;
        private OkHttpClient.Builder okBuilder;
        private Converter.Factory convertFactory;
        private String baseUrl;
        private boolean isAddSession;
        private boolean isAddParameter;
        private HashMap<String, String> addDynamicParameterMap;

        public RetrofitBuilder setConverFactory(Converter.Factory convertFactory) {
            this.convertFactory = convertFactory;
            return this;
        }

        public RetrofitBuilder setBaeUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public RetrofitBuilder addSession() {
            isAddSession = true;
            return this;
        }

        public RetrofitBuilder addParameter() {
            isAddParameter = true;
            return this;
        }

        public RetrofitBuilder addDynamicParameter(HashMap hashMap) {
            addDynamicParameterMap = hashMap;
            return this;
        }

        public HttpApi build() {
            rtBuilder = new Retrofit.Builder();
            okBuilder = new OkHttpClient().newBuilder();
            if (TextUtils.isEmpty(baseUrl)) {
                rtBuilder.baseUrl(baseUrl);
            } else {

                rtBuilder.baseUrl(HttpApiConfig.BASE_URL);
            }
            if (isAddSession) {
                okBuilder.addInterceptor(new HeadInterceptor());
            }
            if (isAddParameter) {
                okBuilder.addInterceptor(new ParameterInterceptor());
            }
            if (addDynamicParameterMap != null) {
                okBuilder.addInterceptor(new DynamicParameterInterceptor());
            }
//            if (Log.is) {
//                okBuilder.addInterceptor(new LogInterceptor());
//            }
            if (convertFactory != null) {
                rtBuilder.addConverterFactory(convertFactory);
            } else {
                rtBuilder.addConverterFactory(GsonConverterFactory.create());
            }
            rtBuilder.addCallAdapterFactory(RxJavaCallAdapterFactory.create()).client(okBuilder.build());
            return rtBuilder.build().create(HttpApi.class);
        }

    }

}
