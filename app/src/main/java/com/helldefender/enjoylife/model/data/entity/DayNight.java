package com.helldefender.enjoylife.model.data.entity;

/**
 * Created by Helldefender on 2017/2/5.
 */

public enum DayNight {
    DAY("DAY", 0),
    NIGHT("NIGHT", 1);
    private String name;
    private int code;

    DayNight(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
