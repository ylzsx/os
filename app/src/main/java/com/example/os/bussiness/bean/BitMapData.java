package com.example.os.bussiness.bean;

/**
 * Time:2020/1/1 20:28
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class BitMapData {

    private int diskId;
    private int isUseFlag;

    public BitMapData(int diskId, int isUseFlag) {
        this.diskId = diskId;
        this.isUseFlag = isUseFlag;
    }

    public int getDiskId() {
        return diskId;
    }

    public int getIsUseFlag() {
        return isUseFlag;
    }

    public void setDiskId(int diskId) {
        this.diskId = diskId;
    }

    public void setIsUseFlag(int isUseFlag) {
        this.isUseFlag = isUseFlag;
    }
}
