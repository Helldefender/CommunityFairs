package com.helldefender.enjoylife.modules.register;

import com.helldefender.enjoylife.app.BasePresenter;
import com.helldefender.enjoylife.app.BaseView;
import com.helldefender.enjoylife.model.Key;

import io.reactivex.annotations.NonNull;

/**
 * Created by ：AceMurder
 * Created on ：2017/5/14
 * Created for : U-Lab.
 * Enjoy it !!!
 */

public interface RegisterContract {
    interface View extends BaseView<Presenter> {

        //验证码超时或者错误
        void onVerificationCodeWrong();

        void onAskSuccess(@NonNull Key key);

        //用户已存在
        void onUserAlreadyExist();

        //注册成功
        void onRegisterSuccess();

    }

     abstract class Presenter extends BasePresenter {

        /**
         * @param phoneNumber 手机号
=        */
        public abstract void askSmsCode(String phoneNumber);

        /**
         *
         * @param email 邮箱
         */
        public abstract void askEmailCode(String email);

        /**
         * 注册
         * @param userName 名字
         * @param password 密码
         * @param confirmPassword 确认密码
         * @param type 0:手机注册，1:邮箱注册
         * @param verificationCode 验证码
         * @param key askEmailCode或者askSmsCode返回的key
         *
         */
        public abstract void register(String userName, String password, String confirmPassword, int type, String verificationCode,
                           String key);

    }
}
