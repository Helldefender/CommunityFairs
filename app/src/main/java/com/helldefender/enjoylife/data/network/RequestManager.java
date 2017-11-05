package com.helldefender.enjoylife.data.network;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.helldefender.enjoylife.BuildConfig;
import com.helldefender.enjoylife.app.App;
import com.helldefender.enjoylife.config.Const;
import com.helldefender.enjoylife.config.Contract;
import com.helldefender.enjoylife.model.ApiWrapper;
import com.helldefender.enjoylife.model.Item;
import com.helldefender.enjoylife.model.Key;
import com.helldefender.enjoylife.model.Lab;
import com.helldefender.enjoylife.model.Order;
import com.helldefender.enjoylife.model.Project;
import com.helldefender.enjoylife.model.TodayWork;
import com.helldefender.enjoylife.model.User;

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
     * 初始化网络请求配置
     */
    RequestManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(Const.TIMEOUT, TimeUnit.SECONDS);
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getContext()));
        builder.cookieJar(cookieJar);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        OkHttpClient okHttpClient = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Contract.SERVER_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        provider = retrofit.create(DataProvider.class);
    }

    /**
     * 请求手机验证
     *
     * @param phoneNumber 手机号码
     * @param observer    验证码请求的观察者
     */
    public void getSMSCode(String phoneNumber, Observer<ApiWrapper<Key>> observer) {
        emitObservable(provider.getSMSCode(phoneNumber), observer);
    }

    /**
     * 请求邮箱验证
     *
     * @param emailAddress 邮箱号
     * @param observer     验证码请求的观察者
     */
    public void getEmailCode(String emailAddress, Observer<ApiWrapper<Key>> observer) {
        emitObservable(provider.getEmailCode(emailAddress), observer);
    }

    /**
     * 注册接口
     *
     * @param userName         用户名
     * @param password         密码
     * @param confirmPassword  确认密码
     * @param type             注册类型
     * @param verificationCode 验证码
     * @param key              返回的key
     * @param observer         注册观察者
     */
    public void register(String userName, String password, String confirmPassword, int type,
                         String verificationCode, String key, Observer<ApiWrapper<Object>> observer) {
        emitObservable(provider.register(userName, password, confirmPassword, type,
                verificationCode, key), observer);
    }

    /**
     * 登录接口
     *
     * @param userName 用户名
     * @param password 密码
     * @param observer 登录观察者
     */
    public void login(String userName, String password, Observer<ApiWrapper<Object>> observer) {
        emitObservable(provider.login(userName, password), observer);
    }


    /**
     * @param newPassword      新秘密
     * @param key              请求得到的Key
     * @param verificationCode 验证码
     * @param observer         观察者
     */
    public void resetPassword(String newPassword, String key, String verificationCode, Observer<ApiWrapper<Object>> observer) {
        emitObservable(provider.resetPassword(newPassword, verificationCode, key), observer);
    }

    /**
     * 我的订单
     *
     * @param type     0:借入订单，1:借出订单
     * @param page     分页参数，第几页
     * @param size     每页几条数据
     * @param observer 观察者
     */
    public void getOrders(int type, int page, int size, Observer<ApiWrapper<List<Order>>> observer) {
        emitObservable(provider.getOrders(type, page, size), observer);
    }

    /**
     * 获取我的物品
     *
     * @param type
     * @param page
     * @param size
     * @param observer
     */
    public void getItems(int type, int page, int size, Observer<ApiWrapper<List<Item>>> observer) {
        emitObservable(provider.getItems(type, page, size), observer);
    }

    /**
     * 添加今日工作
     *
     * @param title    标题
     * @param content  内容
     * @param time     时间
     * @param observer 观察者
     */
    public void addTodayWork(String title, String content, String time, Observer<ApiWrapper<Object>> observer) {
        emitObservable(provider.addTodayWork(title, content, time), observer);
    }

    /**
     * 获取今日工作
     *
     * @param observer
     */
    public void getTodayWork(Observer<ApiWrapper<List<TodayWork>>> observer) {
        emitObservable(provider.getTodayWorks(), observer);
    }

    /**
     * 搜索实验室
     *
     * @param name     实验室名字
     * @param observer 观察者
     */
    public void searchLab(String name, Observer<ApiWrapper<List<Lab>>> observer) {
        emitObservable(provider.searchLab(name), observer);
    }

    /**
     * 根据id获取实验室信息
     *
     * @param labId    实验室ID
     * @param observer
     */
    public void getLabInfo(int labId, Observer<ApiWrapper<Lab>> observer) {
        emitObservable(provider.getLabInfo(labId), observer);
    }

    /**
     * 获取实验室成员
     *
     * @param labId    实验室ID
     * @param observer
     */
    public void getLabMembers(int labId, Observer<ApiWrapper<List<User>>> observer) {
        emitObservable(provider.getLabMembers(labId), observer);
    }


    /**
     * 获取实验室物品信息
     *
     * @param page     第几页
     * @param size     每页数量
     * @param labId    实验室id
     * @param type     物品的类型(1耗材 2试剂 3动物 4仪器)
     * @param observer 观察者
     */
    public void getLabItems(int page, int size, int labId, int type, Observer<ApiWrapper<List<Item>>> observer) {
        emitObservable(provider.getLabItems(page, size, labId, type), observer);
    }

    /**
     * 获取友好实验室列表
     *
     * @param labId    实验室id
     * @param observer 观察者
     */
    public void getFriendlyLabs(int labId, Observer<ApiWrapper<List<Lab>>> observer) {
        emitObservable(provider.getFriendlyLabs(labId), observer);
    }

    /**
     * 申请购买
     *
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
    public void applyPurchase(String itemName, int quantity, String unitMeasurement,
                              String specification, String factory, String dealer,
                              String afterServicePhone, String description, Observer<ApiWrapper<Object>> observer) {
        emitObservable(provider.applyPurchase(itemName, quantity, unitMeasurement, specification,
                factory, dealer, afterServicePhone, description), observer);
    }

    public void getItemInfo(String itemId, Observer<ApiWrapper<Item>> observer) {
        emitObservable(provider.getItemInfo(itemId), observer);
    }

    public void storeItem(Map<String, String> params, List<Integer> userIds, List<Integer> labIds,
                          List<Integer> groupIds, Observer<ApiWrapper<Object>> observer) {
        emitObservable(provider.storeItem(params, userIds, labIds, groupIds), observer);
    }

    public void addProject(String name, String introduction, Observer<ApiWrapper<Integer>> observer) {
        emitObservable(provider.addProject(name, introduction), observer);
    }

    public void updateProject(int id, String name, String introduction, Observer<ApiWrapper<Integer>> observer) {
        emitObservable(provider.updateProject(id, name, introduction), observer);
    }

    public void addExperiment(String name, String startTime, String endTime, String mainPoint,
                              String introduction, String difficult, int projectId,
                              Observer<ApiWrapper<Object>>observer){
        emitObservable(provider.addExperiment(name,startTime,endTime,
                mainPoint,introduction, difficult,projectId),observer);
    }

    public void updateExperiment(int experimentId, String name, String startTime, String endTime, String mainPoint,
                              String introduction, String difficult,
                              Observer<ApiWrapper<Object>>observer){
        emitObservable(provider.updateExperiment(experimentId,name,startTime,endTime,
                mainPoint,introduction, difficult),observer);
    }

    public void getUserInfo(Observer<ApiWrapper<User>> observer){
        emitObservable(provider.getUserInfo(),observer);
    }


    public void borrow(int senderId, int itemId, String quantity,
                       Observer<ApiWrapper<Object>> observer){
        emitObservable(provider.borrowItem(senderId,itemId,quantity),observer);
    }

    public void getProjects(Observer<ApiWrapper<List<Project>>> observer){
        emitObservable(provider.getProjects(),observer);
    }


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
