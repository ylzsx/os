package com.example.os.bussiness.bean;

import java.io.Serializable;

/**
 * Time:2020/1/2 0:35
 * Author: han1254
 * Email: 1254763408@qq.com
 * Function:
 */
public class SignUpRequestBody implements Serializable {

    String userName;
    String password;

    public SignUpRequestBody(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
