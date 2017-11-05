package com.helldefender.enjoylife.modules.login;

import com.helldefender.enjoylife.app.BasePresenter;
import com.helldefender.enjoylife.app.BaseView;

/**
 * Created by ：AceMurder
 * Created on ：2017/5/6
 * Created for : U-Lab.
 * Enjoy it !!!
 */

public interface LoginContract {
    interface View extends BaseView<Presenter> {

        void onLoginSuccess();

        void onPasswordWrong();

        void showProgress();
    }

    abstract class Presenter extends BasePresenter {
        public abstract void login(String userName, String password);
    }
}
