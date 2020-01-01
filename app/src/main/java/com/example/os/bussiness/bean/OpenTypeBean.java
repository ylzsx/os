package com.example.os.bussiness.bean;

import java.io.Serializable;

/**
 * Time:2020/1/1 23:46
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class OpenTypeBean implements Serializable {

    private int type;
    private String userName;

    public OpenTypeBean(int type, String userName) {
        this.type = type;
        this.userName = userName;
    }

    public int getType() {
        return type;
    }

    public String getUserName() {
        return userName;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
