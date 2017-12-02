package com.helldefender.enjoylife.modules.user.register;

import com.helldefender.enjoylife.config.Const;
import com.helldefender.enjoylife.model.entity.ApiWrapper;
import com.helldefender.enjoylife.model.entity.Key;
import com.helldefender.enjoylife.model.data.network.RequestManager;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by ：AceMurder
 * Created on ：2017/5/17
 * Created for : U-Lab.
 * Enjoy it !!!
 */

public class RegisterPresenter extends RegisterContract.Presenter {


    private RegisterContract.View view;
    private Observer<ApiWrapper<Key>> codeObserver;
    private Observer<ApiWrapper<Object>> registerObserver;


    public RegisterPresenter(final RegisterContract.View view) {
        this.view = view;
        //view.setPresenter(this);
        codeObserver = new Observer<ApiWrapper<Key>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposables.add(d);
                view.showProgress();
            }

            @Override
            public void onNext(@NonNull ApiWrapper<Key> keyApiWrapper) {
                if (keyApiWrapper.getErrCode() == 0)
                    view.onAskSuccess(keyApiWrapper.getData());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.onError(e);
                view.stopProgress();
            }

            @Override
            public void onComplete() {
                view.stopProgress();
            }
        };

        registerObserver = new Observer<ApiWrapper<Object>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposables.add(d);
                view.showProgress();
            }

            @Override
            public void onNext(@NonNull ApiWrapper<Object> objectApiWrapper) {
                if (objectApiWrapper.getErrCode() == Const.CODE_SUCCESS)
                    view.onRegisterSuccess();
                else if (objectApiWrapper.getErrCode() == Const.CODE_VERIFY_WRONG)
                    view.onVerificationCodeWrong();
                else if (objectApiWrapper.getErrCode() == Const.CODE_USER_ALREADY_EXIST)
                    view.onUserAlreadyExist();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.stopProgress();
                view.onError(e);
            }

            @Override
            public void onComplete() {
                view.stopProgress();
            }
        };
        subscribe();

    }

    /**
     * 请求发送手机验证码
     * @param phoneNumber 手机号
     */
    @Override
    public void askSmsCode(String phoneNumber) {
        RequestManager.INSTANCE.getSMSCode(phoneNumber, codeObserver);
    }

    /**
     * 请求发送邮箱验证码
     * @param email 邮箱
     */
    @Override
    public void askEmailCode(String email) {
        RequestManager.INSTANCE.getEmailCode(email, codeObserver);
    }

    /**
     * 注册接口
     * @param userName 名字
     * @param password 密码
     * @param confirmPassword 确认密码
     * @param type 0:手机注册，1:邮箱注册
     * @param verificationCode 验证码
     * @param key askEmailCode或者askSmsCode返回的key
     */
    @Override
    public void register(String userName, String password, String confirmPassword,
                         int type, String verificationCode, String key) {
        RequestManager.INSTANCE.register(userName,password,confirmPassword,type,verificationCode,key,registerObserver);
    }

    @Override
    public void subscribe() {
        view.setPresenter(this);
    }

    @Override
    public void unSubscribe() {
        super.unSubscribe();
        this.view = null;
    }
}
