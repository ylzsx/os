package com.example.os.network;

/**
 * @author YangZhaoxin.
 * @since 2019/10/8 17:20.
 * email yangzhaoxin@hrsoft.net.
 */

public class HttpConstants {

    /**
     * 基 url
     */
    public static final String BASE_URL = "http://192.168.0.104:8888";

    /**
     * 网络请求连接超时时间，单位：s
     */
    public static final int APP_SERVER_CONNECT_TIME_OUT = 15;

    /**
     * 正确返回码
     */
    public static final int[] NET_CORRECT_CODE = {0};

    /**
     * 日志信息
     */
    public static final String LOG_TAG = "network >>> ";
}
