package com.helldefender.enjoylife.model.entity;

import java.io.Serializable;

/**
 * Created by ：AceMurder
 * Created on ：2017/5/17
 * Created for : U-Lab.
 * Enjoy it !!!
 */

public class TodayWork implements Serializable{
    /**
     * id : 1
     * title : 写笔记
     * time : 2017-05-05 17:36:09
     * content : 写笔记
     * userId : 3
     */

    private int id;
    private String title;
    private String time;
    private String content;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
