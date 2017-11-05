package com.helldefender.enjoylife.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ：AceMurder
 * Created on ：2017/5/29
 * Created for : U-Lab.
 * Enjoy it !!!
 */

public class Lab implements Parcelable {

    /**
     * id : 2
     * name : 第三军医大学脑研究院
     * location : 渝北区
     * introduction : xxxxx
     * researchDirection : xxxxx
     * setupTime : 2017-05-23 16:49:59
     * image :
     * score : 10
     * status : true
     */

    private int id;
    private String name;
    private String location;
    private String introduction;
    private String researchDirection;
    private String setupTime;
    private String image;
    private int score;
    private boolean status;

    public static final Creator<Lab> CREATOR = new Creator<Lab>() {
        @Override
        public Lab createFromParcel(Parcel in) {
            Lab lab = new Lab();
            lab.id = in.readInt();
            lab.name = in.readString();
            lab.location = in.readString();
            lab.introduction = in.readString();
            lab.researchDirection = in.readString();
            lab.setupTime = in.readString();
            lab.image = in.readString();
            lab.score = in.readInt();
            lab.status = in.readByte() != 0;
            return lab;
        }

        @Override
        public Lab[] newArray(int size) {
            return new Lab[size];
        }
    };

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getResearchDirection() {
        return researchDirection;
    }

    public void setResearchDirection(String researchDirection) {
        this.researchDirection = researchDirection;
    }

    public String getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(String setupTime) {
        this.setupTime = setupTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(introduction);
        dest.writeString(researchDirection);
        dest.writeString(setupTime);
        dest.writeString(image);
        dest.writeInt(score);
        dest.writeByte((byte) (status ? 1 : 0));
    }


}
