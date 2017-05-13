package com.helldefender.enjoylife.data.entity;

/**
 * Created by Helldefender on 2017/2/8.
 */

public class HttpResultModel<T> {
    private int code;
    private String message;
    private T data;
    private String responseStamp;

    private boolean error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResponseStamp() {
        return responseStamp;
    }

    public void setResponseStamp(String responseStamp) {
        this.responseStamp = responseStamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
