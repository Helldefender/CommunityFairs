package com.helldefender.enjoylife.model.data.network;


import com.helldefender.enjoylife.config.Contract;
import com.helldefender.enjoylife.model.entity.ApiWrapper;
import com.helldefender.enjoylife.model.entity.Item;
import com.helldefender.enjoylife.model.entity.Key;
import com.helldefender.enjoylife.model.entity.Lab;
import com.helldefender.enjoylife.model.entity.Order;
import com.helldefender.enjoylife.model.entity.Project;
import com.helldefender.enjoylife.model.entity.TodayWork;
import com.helldefender.enjoylife.model.entity.User;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by ：AceMurder
 * Created on ：2017/5/5
 * Created for : U-Lab.
 * Enjoy it !!!
 */
//接口提供数据
public interface DataProvider {

    /**
     * 请求手机验证
     *
     * @param phoneNumber 手机号码
     */
    @GET(Contract.SEND_MESSAGE)
    Observable<ApiWrapper<Key>> getSMSCode(@Query("phoneNumber") String phoneNumber);

    /**
     * 请求邮箱验证
     *
     * @param emailAddress 邮箱号
     */
    @GET(Contract.SEND_EMAIL)
    Observable<ApiWrapper<Key>> getEmailCode(@Query("emailAddress") String emailAddress);


    /**
     * 注册接口
     *
     * @param userName         用户名
     * @param password         密码
     * @param confirmPassword  确认密码
     * @param type             注册类型
     * @param verificationCode 验证码
     * @param key              返回的key
     */
    @FormUrlEncoded
    @POST(Contract.REGISTER)
    Observable<ApiWrapper<Object>> register(@Field("userName") String userName,
                                            @Field("password") String password,
                                            @Field("againPassword") String confirmPassword,
                                            @Field("type") int type,
                                            @Field("verificationCode") String verificationCode,
                                            @Field("key") String key);

    /**
     * 登录接口
     *
     * @param userName 用户名
     * @param password 密码
     */
    @FormUrlEncoded
    @POST(Contract.LOGIN)
    Observable<ApiWrapper<Object>> login(@Field("userName") String userName,
                                         @Field("password") String password);


    /**
     * 密码修改接口
     *
     * @param newPassword      新秘密
     * @param key              请求得到的Key
     * @param verificationCode 验证码
     */
    @FormUrlEncoded
    @POST(Contract.RESET_PASSWORD)
    Observable<ApiWrapper<Object>> resetPassword(@Field("newPassword") String newPassword,
                                                 @Field("verificationCode") String verificationCode,
                                                 @Field("key") String key);

    /**
     * 获取我的物品
     *
     * @param type 物品的类型(1耗材 2试剂 3动物 4仪器)
     * @param page 页数
     * @param size 每页数量
     */

    @GET(Contract.GET_ITEMS)
    Observable<ApiWrapper<List<Item>>> getItems(@Query("type") int type, @Query("page") int page,
                                                @Query("size") int size);

    @GET(Contract.GET_USER_INFO)
    Observable<ApiWrapper<User>> getUserInfo();

    /**
     * 我的订单
     *
     * @param type 0:借入订单，1:借出订单
     * @param page 分页参数，第几页
     * @param size 每页几条数据
     */
    @GET(Contract.GET_ORDERS)
    Observable<ApiWrapper<List<Order>>> getOrders(@Query("type") int type, @Query("page") int page,
                                                  @Query("size") int size);

    /**
     * 添加今日工作
     *
     * @param title   标题
     * @param content 内容
     * @param time    时间
     */
    @FormUrlEncoded
    @POST(Contract.TODAY_WORK)
    Observable<ApiWrapper<Object>> addTodayWork(@Field("title") String title,
                                                @Field("content") String content,
                                                @Field("time") String time);

    @GET(Contract.TODAY_WORK)
    Observable<ApiWrapper<List<TodayWork>>> getTodayWorks();

    /**
     * 搜索实验室
     *
     * @param name 实验室名字
     */
    @GET(Contract.LAB)
    Observable<ApiWrapper<List<Lab>>> searchLab(@Query("name") String name);

    /**
     * 根据id获取实验室信息
     *
     * @param labId 实验室ID
     */
    @GET(Contract.LAB)
    Observable<ApiWrapper<Lab>> getLabInfo(@Query("labId") int labId);

    /**
     * 获取实验室成员
     *
     * @param labId 实验室ID
     */
    @GET(Contract.LAB_USER)
    Observable<ApiWrapper<List<User>>> getLabMembers(@Query("labId") int labId);

    /**
     * 获取实验室物品信息
     *
     * @param page  第几页
     * @param size  每页数量
     * @param labId 实验室id
     * @param type  物品的类型(1耗材 2试剂 3动物 4仪器)
     */
    @GET(Contract.LAB_ITEMS)
    Observable<ApiWrapper<List<Item>>> getLabItems(@Query("page") int page, @Query("size") int size,
                                                   @Query("labId") int labId, @Query("type") int type);

    /**
     * 获得友好实验室列表
     *
     * @param labId 实验室Id
     * @return
     */
    @GET(Contract.LAB_FRIEND)
    Observable<ApiWrapper<List<Lab>>> getFriendlyLabs(@Query("labId") int labId);

    /**
     * @param itemName          物品名字
     * @param quantity          数量
     * @param unitMeasurement   单位:支
     * @param specification     规格型号
     * @param factory           厂商
     * @param dealer            经销商
     * @param afterServicePhone 售后电话
     * @param description       备注
     * @return
     */
    @FormUrlEncoded
    @POST(Contract.ITEM_PURCHASES)
    Observable<ApiWrapper<Object>> applyPurchase(@Field("itemName") String itemName,
                                                 @Field("quantity") int quantity,
                                                 @Field("unitMeasurement") String unitMeasurement,
                                                 @Field("specification") String specification,
                                                 @Field("factory") String factory,
                                                 @Field("dealer") String dealer,
                                                 @Field("afterServicePhone") String afterServicePhone,
                                                 @Field("description") String description);

    @FormUrlEncoded
    @POST(Contract.ITEM_BORROW)
    Observable<ApiWrapper<Object>> borrowItem(@Field("senderId") int senderId,
                                              @Field("itemId") int itemId,
                                              @Field("quantity") String quantity);

    @FormUrlEncoded
    @POST(Contract.ITEM)
    Observable<ApiWrapper<Item>> getItemInfo(@Field("itemId") String itemId);


    @FormUrlEncoded
    @POST(Contract.ITEM)
    Observable<ApiWrapper<Object>> storeItem(@FieldMap Map<String, String> params,
                                             @Field("userIds") List<Integer> userIds,
                                             @Field("labIds") List<Integer> labIds,
                                             @Field("groupIds") List<Integer> groupIds);

    @FormUrlEncoded
    @POST(Contract.USER_PROJECT)
    Observable<ApiWrapper<Integer>> addProject(@Field("name") String name,
                                               @Field("introduction") String introduction);

    @FormUrlEncoded
    @PUT(Contract.USER_PROJECT)
    Observable<ApiWrapper<Integer>> updateProject(@Field("id") int id, @Field("name") String name,

                                                  @Field("introduction") String introduction);

    @GET(Contract.USER_PROJECT)
    Observable<ApiWrapper<List<Project>>> getProjects();

//    {
//        "name":"脑研究实验一", //实验名称
//            "startTime":"2017-05-20 15:21:03", //开始时间
//            "endTime":"2017-05-20 15:21:07", //截止时间
//            "mainPoint":"注意注意", //要点
//            "introduction":"haha", //介绍
//            "difficult":"难点", //难点
//            "projectId":2 //项目id
//    }
//    返

    @FormUrlEncoded
    @POST(Contract.USER_EXPERIMENT)
    Observable<ApiWrapper<Object>> addExperiment(@Field("name") String name,
                                                 @Field("startTime") String startTime,
                                                 @Field("endTime") String endPoint,
                                                 @Field("mainPoint") String mainPoint,
                                                 @Field("introduction") String introduction,
                                                 @Field("difficult") String difficult,
                                                 @Field("projectId") int projectId);

    @FormUrlEncoded
    @PUT(Contract.USER_EXPERIMENT)
    Observable<ApiWrapper<Object>> updateExperiment(@Field("id") int id, @Field("name") String name,
                                                    @Field("startTime") String startTime,
                                                    @Field("endTime") String endPoint,
                                                    @Field("mainPoint") String mainPoint,
                                                    @Field("introduction") String introduction,
                                                    @Field("difficult") String difficult);


}
