package com.helldefender.enjoylife.delete.server.entity;

import java.util.List;

/**
 * Created by Helldefender on 2017/2/21.
 */

public class TypeBean {

    /**
     * resultCode : 1
     * resultMessage : success
     *
     * firstPages : [{"id":5,"username":"fff","u_photo":"kakak","photo":"测试","time":"Feb 20, 2017 12:00:00 AM","theme":"测试","content":"/Article/测试.txt","watching":0,"liking":0,"type":1}]
     */

    private int resultCode;
    private String resultMessage;
    private List<FirstPagesBean> firstPages;

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

    public List<FirstPagesBean> getFirstPages() {
        return firstPages;
    }

    public void setFirstPages(List<FirstPagesBean> firstPages) {
        this.firstPages = firstPages;
    }

    public static class FirstPagesBean {
        /**
         * id : 5
         * username : fff
         * u_photo : kakak
         * photo : 测试
         * time : Feb 20, 2017 12:00:00 AM
         * theme : 测试
         * content : /Article/测试.txt
         * watching : 0
         * liking : 0
         * type : 1
         */

        private int id;
        private String username;
        private String u_photo;
        private String photo;
        private String time;
        private String theme;
        private String content;
        private int watching;
        private int liking;
        private int type;

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

        public String getU_photo() {
            return u_photo;
        }

        public void setU_photo(String u_photo) {
            this.u_photo = u_photo;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getWatching() {
            return watching;
        }

        public void setWatching(int watching) {
            this.watching = watching;
        }

        public int getLiking() {
            return liking;
        }

        public void setLiking(int liking) {
            this.liking = liking;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
