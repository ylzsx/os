package com.example.os.network.response;


import com.example.os.network.exception.ApiException;
import com.example.os.network.exception.ServerException;
import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.ParseException;

import retrofit2.HttpException;

/**
 * @author YangZhaoxin.
 * @since 2019/5/1 22:46.
 * email yangzhaoxin@hrsoft.net.
 */

public class GlobalAPIErrorHandler {

    /**未知错误*/
    private static final int UN_KNOWN_ERROR = 1000;
    /**解析(服务器)数据错误*/
    private static final int PARSE_SERVER_DATA_ERROR = 1001;
    /**解析(客户端)数据错误*/
    public static final int PARSE_CLIENT_DATA_ERROR = 1002;
    /**网络连接错误*/
    private static final int CONNECT_ERROR = 1003;
    /**网络连接超时*/
    private static final int CONNECT_TIME_OUT_ERROR = 1004;

    public static ApiException handleException(Throwable throwable) {
        throwable.printStackTrace();
        ApiException apiException;
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            apiException = new ApiException(throwable, httpException.code());
            apiException.setMessage("网络错误");
            return apiException;
        } else if (throwable instanceof JsonParseException
                || throwable instanceof JSONException
                || throwable instanceof ParseException
                || throwable instanceof MalformedJsonException) {
            apiException = new ApiException(throwable, PARSE_SERVER_DATA_ERROR);
            apiException.setMessage("解析数据错误");
            return apiException;
        } else if (throwable instanceof ConnectException) {
            apiException = new ApiException(throwable, CONNECT_ERROR);
            apiException.setMessage("网络连接失败");
            return apiException;
        } else if (throwable instanceof SocketTimeoutException) {
            apiException = new ApiException(throwable, CONNECT_TIME_OUT_ERROR);
            apiException.setMessage("网络连接超时");
            return apiException;
        } else if (throwable instanceof ServerException) {
            ServerException serverException = (ServerException) throwable;
            apiException = new ApiException(serverException, serverException.getCode());
            apiException.setMessage(serverException.getMsg());
            return apiException;
        } else {
            apiException = new ApiException(throwable, UN_KNOWN_ERROR);
            apiException.setMessage("未知错误");
            return apiException;
        }
    }

    public static String handlerResponseError(int code) {
        String message;
        switch (code) {
            default:
                message = "请求不被允许，请确定是否有权进行该操作";
                break;
        }
        return message;
    }

}
