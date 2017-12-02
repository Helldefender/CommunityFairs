package com.helldefender.enjoylife.modules.user.password;

import com.helldefender.enjoylife.app.BasePresenter;
import com.helldefender.enjoylife.app.BaseView;
import com.helldefender.enjoylife.model.entity.Key;

/**
 * Created by ：AceMurder
 * Created on ：2017/5/26
 * Created for : U-Lab.
 * Enjoy it !!!
 */

public interface PasswordContract {
    interface View extends BaseView<Presenter> {
        void onAskCodeSuccess(Key key);

        void onResetSuccess();

        void onVerificationCodeWrong();
    }

    abstract class Presenter extends BasePresenter {
        /**
         * @param newPassword      新密码
         * @param verificationCode 验证码
         * @param key              请求验证码返回的key
         */
        public abstract void resetPassword(String newPassword, String verificationCode, String key);

        /**
         * @param phoneNumber 手机号
         */
        public abstract void askSmsCode(String phoneNumber);

        /**
         * @param email 邮箱
         */
        public abstract void askEmailCode(String email);
    }
}
