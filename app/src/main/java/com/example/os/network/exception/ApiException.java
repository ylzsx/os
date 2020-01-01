package com.example.os.network.exception;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;

/**
 * 异常响应体
 * @author YangZhaoxin.
 * @since 2019/5/1 22:22.
 * email yangzhaoxin@hrsoft.net.
 */

public class ApiException extends IOException {

    // 错误码
    @SerializedName("code")
    private int code;

    // 错误信息
    @SerializedName("message")
    private String message;


    public ApiException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
