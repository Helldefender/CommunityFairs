package com.helldefender.enjoylife.model.entity;

import java.io.Serializable;

/**
 * Created by ：AceMurder
 * Created on ：2017/6/10
 * Created for : U-Lab.
 * Enjoy it !!!
 */

public class Experiment implements Serializable {


    /**
     * id : 3
     * name : 脑研究实验一
     * startTime : 2017-05-20 15:21:03
     * endTime : 2017-05-20 15:21:07
     * mainPoint : 注意注意
     * introduction : haha
     * difficult : 难点
     * projectId : 2
     * userId : 1
     */

    private int id;
    private String name;
    private String startTime;
    private String endTime;
    private String mainPoint;
    private String introduction;
    private String difficult;
    private int projectId;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMainPoint() {
        return mainPoint;
    }

    public void setMainPoint(String mainPoint) {
        this.mainPoint = mainPoint;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
