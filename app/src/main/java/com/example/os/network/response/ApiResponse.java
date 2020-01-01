package com.example.os.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * @author YangZhaoxin.
 * @since 2019/5/1 21:46.
 * email yangzhaoxin@hrsoft.net.
 */

public class ApiResponse<T> {

    // 状态码
    @SerializedName("code")
    private int code;

    // 返回信息
    @SerializedName("message")
    private String message;

    // 返回具体数据
    @SerializedName("data")
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
