package com.helldefender.enjoylife.delete.server.entity;

import java.util.List;

/**
 * Created by Helldefender on 2017/2/28.
 */

public class CommentBean {

    /**
     * resultCode : 1
     * resultMessage : success
     * list : [{"id":3,"iF":1,"aId":5,"uId":3,"toId":2,"time":"Feb 27, 2017 11:34:35 AM","content":"这个是测试"}]
     */

    private int resultCode;
    private String resultMessage;
    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 3
         * iF : 1
         * aId : 5
         * uId : 3
         * toId : 2
         * time : Feb 27, 2017 11:34:35 AM
         * content : 这个是测试
         */

        private int id;
        private int iF;
        private int aId;
        private int uId;
        private int toId;
        private String time;
        private String content;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIF() {
            return iF;
        }

        public void setIF(int iF) {
            this.iF = iF;
        }

        public int getAId() {
            return aId;
        }

        public void setAId(int aId) {
            this.aId = aId;
        }

        public int getUId() {
            return uId;
        }

        public void setUId(int uId) {
            this.uId = uId;
        }

        public int getToId() {
            return toId;
        }

        public void setToId(int toId) {
            this.toId = toId;
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
    }
}
