package com.helldefender.enjoylife.modules.user.password;


import com.helldefender.enjoylife.config.Const;
import com.helldefender.enjoylife.model.entity.ApiWrapper;
import com.helldefender.enjoylife.model.entity.Key;
import com.helldefender.enjoylife.model.data.network.RequestManager;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by ：AceMurder
 * Created on ：2017/5/26
 * Created for : U-Lab.
 * Enjoy it !!!
 */

public class ResetPasswordPresenter extends PasswordContract.Presenter{

    private PasswordContract.View view;
    private Observer<ApiWrapper<Object>> resetObserver;
    private Observer<ApiWrapper<Key>> codeObserver;


    public ResetPasswordPresenter(final PasswordContract.View view) {
        this.view = view;
        subscribe();
        resetObserver = new Observer<ApiWrapper<Object>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposables.add(d);
                view.showProgress();
            }

            @Override
            public void onNext(@NonNull ApiWrapper<Object> objectApiWrapper) {
                if (objectApiWrapper.getErrCode() == Const.CODE_SUCCESS)
                    view.onResetSuccess();
                else if (objectApiWrapper.getErrCode() == Const.CODE_VERIFY_WRONG)
                    view.onVerificationCodeWrong();
                else {
                    view.onError(null);
                }
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

        codeObserver = new Observer<ApiWrapper<Key>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposables.add(d);
                view.showProgress();
            }

            @Override
            public void onNext(@NonNull ApiWrapper<Key> keyApiWrapper) {
                if (keyApiWrapper.getErrCode() == 0)
                    view.onAskCodeSuccess(keyApiWrapper.getData());
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


    }

    @Override
    public void resetPassword(String newPassword, String verificationCode, String key) {
        RequestManager.INSTANCE.resetPassword(newPassword,key,verificationCode,resetObserver);
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
