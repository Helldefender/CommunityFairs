package com.helldefender.enjoylife.model.entity;

import java.io.Serializable;

/**
 * Created by ：AceMurder
 * Created on ：2017/5/16
 * Created for : U-Lab.
 * Enjoy it !!!
 */

public class User implements Serializable{

    /**
     * id : 6
     * username : 孟德平
     * telephone : 15340529572
     * email : 744623329@qq.com
     * laboratoryId : 1
     * laboratoryName : 实验室
     * school : 重庆邮电大学
     * educationalHistory : 本科
     * researchDirection : 分布式
     * verify : false
     * status : false
     * createdAt : 2017-05-15 10:40:45
     * updatedAt : 2017-05-15 10:40:45
     * deletedAt : 2017-05-15 10:40:4
     */

    private int id;
    private String username;
    private String telephone;
    private String email;
    private int laboratoryId;
    private String laboratoryName;
    private String school;
    private String educationalHistory;
    private String researchDirection;
    private boolean verify;
    private boolean status;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLaboratoryId() {
        return laboratoryId;
    }

    public void setLaboratoryId(int laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEducationalHistory() {
        return educationalHistory;
    }

    public void setEducationalHistory(String educationalHistory) {
        this.educationalHistory = educationalHistory;
    }

    public String getResearchDirection() {
        return researchDirection;
    }

    public void setResearchDirection(String researchDirection) {
        this.researchDirection = researchDirection;
    }

    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }
}
