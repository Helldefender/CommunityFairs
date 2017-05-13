package com.helldefender.enjoylife.data.server;


import com.helldefender.enjoylife.data.entity.HttpResultModel;

import rx.Observable;
import rx.functions.Func1;


/**
 * Created by Helldefender on 2017/2/8.
 */

public class HttpExceptionFunc1<T> implements Func1<HttpResultModel<T>, Observable<T>> {
    @Override
    public Observable<T> call(HttpResultModel<T> tHttpResultModel) {
        if (tHttpResultModel.getCode() != 200) {
            Observable.error(new HttpException(tHttpResultModel.getMessage(), tHttpResultModel.getCode()));
        }
        return Observable.just(tHttpResultModel.getData());
    }
}
