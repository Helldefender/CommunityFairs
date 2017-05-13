package com.helldefender.enjoylife.delete.server;

import com.helldefender.enjoylife.delete.server.entity.ImageBean;
import com.helldefender.enjoylife.delete.server.entity.TypeBean;
import com.helldefender.enjoylife.delete.server.entity.UserBean;

import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Helldefender on 2017/2/8.
 */

public interface HttpApi {

    @POST("byType.article")
    Observable<TypeBean> getTypeContent(@Query("type") int id);

    @POST("firstPage.article")
    Observable<TypeBean> getRecommendContent(@Query("type") String type);

    @POST("search.article")
    Observable<TypeBean> getSearchResult(@Query("search") String keyword);

    @POST("detail.article")
    Observable<TypeBean> getAtricleDetail(@Query("id") int id);

    @POST("comment.article")
    Observable<TypeBean> postComment(@Query("comment") String keyword);

    @Multipart
    @POST("PhotoUpload")
    Observable<ImageBean> imageUpload(@Part MultipartBody.Part body);

    @FormUrlEncoded
    @POST("upload.article")
    Observable<ImageBean> articleUpload(@Field("type") int type,
                                        @Field("theme") String title,
                                        @Field("content") String content, @Field("photo") String url);

    @FormUrlEncoded
    @POST("login.user")
    Observable<UserBean> login(@Field("user") String user,
                               @Field("password") String password);
    @FormUrlEncoded
    @POST("update.user")
    Observable<UserBean.ListBean> updateUser(@Field("type") UserBean.ListBean userBean);

    @FormUrlEncoded
    @POST("regist.user")
    Observable<UserBean> register(@Field("name") String name,
                                   @Field("password") String password,
                                   @Field("type") String type,
                                   @Field("phone") String phone, @Field("photo") String photo);

}
