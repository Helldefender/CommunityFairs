package com.helldefender.enjoylife.model;

import java.io.Serializable;

/**
 * Created by ：AceMurder
 * Created on ：2017/5/16
 * Created for : U-Lab.
 * Enjoy it !!!
 */

public class Order implements Serializable{

    /**
     * id : 1
     * senderId : 3
     * senderName : 孟德平
     * receiverId : 3
     * receiverName : 孟德平
     * time : 2017-05-19 15:26:33
     * itemId : 1
     * quantity : 20
     * senderTelephone : 15340529572
     * qrcodeImage :
     * returnQrcodeImage :
     * status : 0
     * duration : 6
     * amount : 680
     * laboratoryId : 1
     */

    private int id;
    private int senderId;
    private String senderName;
    private int receiverId;
    private String receiverName;
    private String time;
    private int itemId;
    private String quantity;
    private String senderTelephone;
    private String qrcodeImage;
    private String returnQrcodeImage;
    private int status;
    private int duration;
    private int amount;
    private int laboratoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSenderTelephone() {
        return senderTelephone;
    }

    public void setSenderTelephone(String senderTelephone) {
        this.senderTelephone = senderTelephone;
    }

    public String getQrcodeImage() {
        return qrcodeImage;
    }

    public void setQrcodeImage(String qrcodeImage) {
        this.qrcodeImage = qrcodeImage;
    }

    public String getReturnQrcodeImage() {
        return returnQrcodeImage;
    }

    public void setReturnQrcodeImage(String returnQrcodeImage) {
        this.returnQrcodeImage = returnQrcodeImage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getLaboratoryId() {
        return laboratoryId;
    }

    public void setLaboratoryId(int laboratoryId) {
        this.laboratoryId = laboratoryId;
    }
}
