package com.helldefender.enjoylife.data.server;


import com.helldefender.enjoylife.data.entity.ResultsBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Helldefender on 2017/2/8.
 */

public interface HttpApi {
    @POST("byType.article")
    Observable<ResultsBean> getNewsByDate(@Query("type") int id);
}
