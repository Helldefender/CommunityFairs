package com.helldefender.enjoylife.model.entity;

import java.io.Serializable;

/**
 * Created by ：AceMurder
 * Created on ：2017/5/16
 * Created for : U-Lab.
 * Enjoy it !!!
 */

public class Item implements Serializable{

    /**
     * id : 1
     * name : 试剂
     * type : 1
     * quantity : 60
     * location : 架子A
     * userTrueName : 孟德平
     * userId : 3
     * ownerTrueName : 孟德平
     * ownerId : 3
     * isShared : true
     * isUsing : true
     * laboratoryId : 1
     * contactNumber : 15340529572
     * unitPrice : 2.2
     * unitMeasurement : 支
     * factory : A
     * specification : B
     * dealer : C
     * afterServicePhone : 155555555555
     * description : 描述
     * image : http://....
     */

    private int id;
    private String name;
    private int type;
    private int quantity;
    private String location;
    private String userTrueName;
    private int userId;
    private String ownerTrueName;
    private int ownerId;
    private boolean isShared;
    private boolean isUsing;
    private int laboratoryId;
    private String contactNumber;
    private double unitPrice;
    private String unitMeasurement;
    private String factory;
    private String specification;
    private String dealer;
    private String afterServicePhone;
    private String description;
    private String image;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserTrueName() {
        return userTrueName;
    }

    public void setUserTrueName(String userTrueName) {
        this.userTrueName = userTrueName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOwnerTrueName() {
        return ownerTrueName;
    }

    public void setOwnerTrueName(String ownerTrueName) {
        this.ownerTrueName = ownerTrueName;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isIsShared() {
        return isShared;
    }

    public void setIsShared(boolean isShared) {
        this.isShared = isShared;
    }

    public boolean isIsUsing() {
        return isUsing;
    }

    public void setIsUsing(boolean isUsing) {
        this.isUsing = isUsing;
    }

    public int getLaboratoryId() {
        return laboratoryId;
    }

    public void setLaboratoryId(int laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnitMeasurement() {
        return unitMeasurement;
    }

    public void setUnitMeasurement(String unitMeasurement) {
        this.unitMeasurement = unitMeasurement;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public String getAfterServicePhone() {
        return afterServicePhone;
    }

    public void setAfterServicePhone(String afterServicePhone) {
        this.afterServicePhone = afterServicePhone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
