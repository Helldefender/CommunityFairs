package com.helldefender.communityfairs.model.entity;

import java.io.Serializable;

/**
 * Created by ：AceMurder
 * Created on ：2017/5/14
 * Created for : U-Lab.
 * Enjoy it !!!
 */

public class ApiWrapper<T> implements Serializable{

    /**
     * success : true
     * message : OK
     * errCode : 0
     */

    private int success;
    private String message;
    private int errCode;
    private T data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
