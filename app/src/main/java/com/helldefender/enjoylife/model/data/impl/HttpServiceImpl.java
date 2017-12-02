package com.helldefender.enjoylife.model.data.impl;

/**
 * Created by Helldefender on 2017/4/8.
 */
//@PerApp
//public class HttpServiceImpl implements HttpServer<String> {
//
//    private HttpManager httpManager;
//
//    private Context mContext;
//
//    @Inject
//    public HttpServiceImpl(HttpManager httpManager, @ApplicationContext Context context) {
//        //这里的Context应该是ApplicationContext
//        this.httpManager = httpManager;
//        this.mContext = context;
//    }
//
//    @Override
//    public Subscription getFromService(final OnRequestCallBackListener onRequestCallBackListener) {
//        //onRequestCallBackListener.onEmpty();
//        //onRequestCallBackListener.on
//        return null;
//
////        return httpManager.with(mContext).setShowWaitingDialog(true).setObservable(ObservableManager.getHomePageNews(1)).subscriber(new ProgressSubscriber<String>() {
////            @Override
////            public void onNext(String s) {
//////                onRequestCallBackListener.onSuccess(s);
//////                onRequestCallBackListener.onEmpty();
//////                onRequestCallBackListener.onError("error");
////            }
////        });
//    }
//
//    @Override
//    public void getFromCache(OnRequestCallBackListener onRequestCallBackListener) {
//
//    }
//}
