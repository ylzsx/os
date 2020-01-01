package com.example.os.network.response;


import com.example.os.network.HttpConstants;
import com.example.os.network.exception.ApiException;
import com.example.os.utils.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author YangZhaoxin.
 * @since 2019/10/8 17:35.
 * email yangzhaoxin@hrsoft.net.
 */

public abstract class ResponseCallBack<T> implements Callback<ApiResponse<T>> {

    /**
     * 有返回时回调（包括 Http五种状态码：1xx/2xx/3xx/4xx/5xx）
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call<ApiResponse<T>> call, Response<ApiResponse<T>> response) {
        if (response != null) {
            if (response.code() != 200) {
                onFailure(call, response, null);
            } else {    // 请求成功，同时返回码是 200 时的回调
                boolean flag = false;
                for (int code : HttpConstants.NET_CORRECT_CODE) {
                    if (response.body().getCode() == code) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    onDataResponse(call, response.body());
                } else {    // 返回 code 不为 0
                    onFailure(call, response, new ApiException(response.body().getCode(), response.body().getMessage()));
                }
            }
        }
    }

    /**
     * 请求延迟、超时，或者网络状态差等网络问题
     * @param call
     * @param t
     */
    @Override
    public void onFailure(Call<ApiResponse<T>> call, Throwable t) {
        onFailure(call, null, t);
    }

    /**
     * 统一错误处理方法
     * @param call
     * @param response
     */
    private void onFailure(Call<ApiResponse<T>> call, Response<ApiResponse<T>> response, Throwable t) {
        onDataFailure(call, response, t);
        if (t != null) {
            if (t instanceof ApiException) { // 有返回，返回码为200，但code不为 0
                ToastUtil.showToast(t.getMessage());
            } else { // 处理非服务器产生的错误，如本地无网络，JSON解析错误等
                ToastUtil.showToast(GlobalAPIErrorHandler.handleException(t).getMessage());
            }
            return;
        }

        // 有返回值，返回码不是200
        if (response != null) {
            ToastUtil.showToast(GlobalAPIErrorHandler.handlerResponseError(response.code()));
        }
    }

    /**
     * 当请求正常，同时业务逻辑返回code为 0 时调用
     * @param call
     * @param responseData
     */
    protected abstract void onDataResponse(Call<ApiResponse<T>> call, ApiResponse<T> responseData);

    /**
     * 当请求正常，同时业务逻辑返回code不为 0 时调用，用户可重写此方法做业务逻辑处理
     * @param call
     * @param response
     */
    protected void onDataFailure(Call<ApiResponse<T>> call, Response<ApiResponse<T>> response, Throwable t) {}

}
