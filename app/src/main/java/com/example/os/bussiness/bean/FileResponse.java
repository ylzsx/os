package com.example.os.bussiness.bean;

/**
 * @author YangZhaoxin.
 * @since 2020/1/1 10:12.
 * email yangzhaoxin@hrsoft.net.
 */

public class FileResponse {


    /**
     * ufdId : 1
     * userId : 2
     * fileName : aaa1
     * fileLength : 10
     * isOpenFlag : 0
     */

    private int ufdId;
    private int userId;
    private String fileName;
    private int fileLength;
    private int isOpenFlag = 0;     // 0代表文件关闭
    private Integer[] mMemoryBlock;

    public FileResponse() {}

    public int getUfdId() {
        return ufdId;
    }

    public void setUfdId(int ufdId) {
        this.ufdId = ufdId;
    }

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

    public int getFileLength() {
        return fileLength;
    }

    public void setFileLength(int fileLength) {
        this.fileLength = fileLength;
    }

    public int getIsOpenFlag() {
        return isOpenFlag;
    }

    public void setIsOpenFlag(int isOpenFlag) {
        this.isOpenFlag = isOpenFlag;
    }

    public Integer[] getMemoryBlock() {
        return mMemoryBlock;
    }

    public void setMemoryBlock(Integer[] memoryBlock) {
        mMemoryBlock = memoryBlock;
    }
}
