package com.helldefender.enjoylife.utils.file;

import java.io.Serializable;

/**
 * Created by Helldefender on 2017/5/25.
 */

public class FileInfo implements Serializable {
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件长度
     */
    private long fileLength;


    /**
     * 是不是一个数据块
     * true 表示是一个文件，false表示是一个块（分片的数据）
     */
    private boolean isChunk;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isChunk() {
        return isChunk;
    }

    public void setIsChunk(boolean isChunk) {
        this.isChunk = isChunk;
    }
    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "filePath='" + filePath + '\'' +
                ", fileLength=" + fileLength +
                ", isChunk=" + isChunk +
                '}';
    }
}
