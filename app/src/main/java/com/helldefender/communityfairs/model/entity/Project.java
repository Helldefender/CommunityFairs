package com.helldefender.communityfairs.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ：AceMurder
 * Created on ：2017/6/10
 * Created for : U-Lab.
 * Enjoy it !!!
 */

public class Project implements Serializable{

    /**
     * id : 2
     * name : 脑研究实验
     * introduction : 脑研究实验2
     * createTime : 2017-05-20 16:05:57
     * userId : 3
     */

    private int id;
    private String name;
    private String introduction;
    private String createTime;
    private int userId;
    private List<Experiment> experimentList;

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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Experiment> getExperimentList() {
        return experimentList;
    }

    public void setExperimentList(List<Experiment> experimentList) {
        this.experimentList = experimentList;
    }
}
