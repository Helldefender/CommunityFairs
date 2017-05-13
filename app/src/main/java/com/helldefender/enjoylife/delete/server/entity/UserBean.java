package com.helldefender.enjoylife.delete.server.entity;

/**
 * Created by Helldefender on 2017/2/27.
 */

public class UserBean {

    /**
     * resultCode : 1
     * resultMessage : 登录成功
     * list : {"id":1,"name":"liu","first":0,"password":"1235","fans_number":0,"like_file":"0-"}
     */

    private int resultCode;
    private String resultMessage;
    private ListBean list;

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

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1
         * name : liu
         * first : 0
         * password : 1235
         * fans_number : 0
         * like_file : 0-
         */

        private int id;
        private String name;
        private int first;
        private String password;
        private int fans_number;
        private String like_file;
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

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

        public int getFirst() {
            return first;
        }

        public void setFirst(int first) {
            this.first = first;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getFans_number() {
            return fans_number;
        }

        public void setFans_number(int fans_number) {
            this.fans_number = fans_number;
        }

        public String getLike_file() {
            return like_file;
        }

        public void setLike_file(String like_file) {
            this.like_file = like_file;
        }
    }
}
