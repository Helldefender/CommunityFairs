package com.helldefender.communityfairs.model.data.server;

/**
 * Created by Helldefender on 2017/2/8.
 */

//public class ObservableManager {
//
//    public static Observable mObservable;
//
//    public static HttpApi httpApi;
//
//    public static Observable getObservable(Observable observable) {
//        mObservable = new ObservableBuilder(observable)
//                .addApiException()
//                .build();
//        return mObservable;
//    }
//
//    public static HttpApi getHttpApi() {
//        if (httpApi == null) {
//            httpApi = new HttpManager.RetrofitBuilder()
//                    .addSession()
//                    .addParameter()
//                    .build();
//        }
//        return httpApi;
//    }
//
//    public static Observable getHomePageNews(int date) {
//        return getObservable(getHttpApi().getNewsByDate(date));
//    }
//
//    public static class ObservableBuilder {
//
//        private Observable observable;
//
//        private boolean apiException;
//
//        private Scheduler subscriberScheduler;
//
//        private Scheduler observableScheduler;
//
//        public ObservableBuilder(Observable observable) {
//            this.observable = observable;
//        }
//
//        public void setObservableScheduler(Scheduler observableScheduler) {
//            this.observableScheduler = observableScheduler;
//        }
//
//        public void setSubscriberScheduler(Scheduler subscriberScheduler) {
//            this.subscriberScheduler = subscriberScheduler;
//        }
//
//        public ObservableBuilder addApiException() {
//            apiException = true;
//            return this;
//        }
//
//        public Observable build() {
//            if (apiException) {
//                observable = observable.flatMap(new HttpExceptionFunc1());
//            }
//            if (subscriberScheduler != null) {
//                observable = observable.subscribeOn(subscriberScheduler);
//            } else {
//                observable = observable.subscribeOn(Schedulers.io());
//            }
//            if (observableScheduler != null) {
//                observable = observable.observeOn(observableScheduler);
//            } else {
//                observable = observable.observeOn(AndroidSchedulers.mainThread());
//            }
//            return observable;
//        }
//    }
//}
