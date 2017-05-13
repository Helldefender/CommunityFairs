package com.helldefender.enjoylife.delete.server;

import com.helldefender.enjoylife.delete.server.entity.ImageBean;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Helldefender on 2017/2/8.
 */

public interface FileUploadService {
    @Multipart
    @POST("PhotoUpload")
    //Call<ImageBean> upload(@Part("description") String description, @Part Multipart.);
    Call<ImageBean> imageUpload(@Part MultipartBody.Part body);
    //Call<ImageBean> imageUpload( @Part("file\"; filename=\"image.jpg\"") RequestBody requestBody);


    @FormUrlEncoded
    @POST("upload.article")
    Call<ImageBean> fileUpload(@Field("type") int type,
                               @Field("theme") String title,
                               @Field("content") String content, @Field("photo") String url);


}
