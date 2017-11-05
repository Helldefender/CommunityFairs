package com.helldefender.enjoylife.modules.login;


import com.helldefender.enjoylife.config.Const;
import com.helldefender.enjoylife.model.ApiWrapper;
import com.helldefender.enjoylife.data.network.RequestManager;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by ：AceMurder
 * Created on ：2017/5/6
 * Created for : U-Lab.
 * Enjoy it !!!
 */

public class LoginPresenter  extends LoginContract.Presenter {

    private LoginContract.View view;
    private Observer<ApiWrapper<Object>> loginObserver;
    private CompositeDisposable mDisposables = new CompositeDisposable();


    public LoginPresenter(final LoginContract.View view) {
        this.view = view;
        loginObserver = new Observer<ApiWrapper<Object>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mDisposables.add(d);
                view.showProgress();
            }

            @Override
            public void onNext(@NonNull ApiWrapper<Object> objectApiWrapper) {
                if (objectApiWrapper.getErrCode() == Const.CODE_SUCCESS)
                    view.onLoginSuccess();
                else if (objectApiWrapper.getErrCode() == Const.CODE_PASSWORD_WRONG)
                    view.onPasswordWrong();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showProgress();
                view.onError(e);

            }

            @Override
            public void onComplete() {
                view.stopProgress();
            }
        };
        subscribe();
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

    @Override
    public void login(String userName, String password) {
        RequestManager.INSTANCE.login(userName, password, loginObserver);
    }
}
