package com.example.os.bussiness.bean;

/**
 * @author YangZhaoxin.
 * @since 2020/1/2 3:03.
 * email yangzhaoxin@hrsoft.net.
 */

public class FileDirRequest {


    /**
     * userId : 1
     * fileName : admin_file_1
     */

    private int userId;
    private String fileName;
    private int ufdId;
    private int fileLength;
    private String content;

    public FileDirRequest() {}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getUfdId() {
        return ufdId;
    }

    public void setUfdId(int ufdId) {
        this.ufdId = ufdId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFileLength() {
        return fileLength;
    }

    public void setFileLength(int fileLength) {
        this.fileLength = fileLength;
    }
}
