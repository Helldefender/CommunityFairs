package com.helldefender.enjoylife.delete.server.entity;

/**
 * Created by Helldefender on 2017/2/18.
 */

public class ImageBean {

    /**
     * resultCode : 0
     * resultMessage : 该请求类型不是表单
     */

    private int resultCode;
    private String resultMessage;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
