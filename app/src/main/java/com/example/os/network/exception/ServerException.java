package com.example.os.network.exception;

/**
 * 服务器定义错误
 * @author YangZhaoxin.
 * @since 2019/10/8 19:12.
 * email yangzhaoxin@hrsoft.net.
 */

public class ServerException extends RuntimeException {

    private int code;
    private String msg;

    public ServerException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
