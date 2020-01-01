package com.example.os.bussiness;

import com.example.os.bussiness.bean.FileContentResponse;
import com.example.os.bussiness.bean.FileResponse;
import com.example.os.network.NetworkFactory;
import com.example.os.network.response.ApiResponse;
import com.example.os.network.response.ResponseCallBack;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author YangZhaoxin.
 * @since 2020/1/1 13:44.
 * email yangzhaoxin@hrsoft.net.
 */

public class Repository {

    private Api mApi;

    private Repository(Api api) {
        this.mApi = api;
    }

    private static class InstanceHolder {
        private static final Repository INSTANCE = new Repository(
                NetworkFactory.getService(Api.class)
        );
    }

    public static Repository getInstance() {
        return InstanceHolder.INSTANCE;
    }


    public void getAllFile(int mfdId, final IOSListener.IGetAllFileListener listener) {
        mApi.getAllFileByMFDId(mfdId).enqueue(new ResponseCallBack<ArrayList<FileResponse>>() {
            @Override
            protected void onDataResponse(Call<ApiResponse<ArrayList<FileResponse>>> call, ApiResponse<ArrayList<FileResponse>> responseData) {
                listener.onGetAllFileListener(responseData.getData());
            }

            @Override
            protected void onDataFailure(Call<ApiResponse<ArrayList<FileResponse>>> call, Response<ApiResponse<ArrayList<FileResponse>>> response, Throwable t) {
                super.onDataFailure(call, response, t);
                listener.onError(t);
            }
        });
    }

    public void openFile(int ufdId, final IOSListener.IOpenFileListener listener) {
        mApi.openFile(ufdId).enqueue(new ResponseCallBack<Integer>() {
            @Override
            protected void onDataResponse(Call<ApiResponse<Integer>> call, ApiResponse<Integer> responseData) {
                listener.onOpenFileListener(responseData.getData());
            }
        });
    }

    public void searchFile(int ufdId, int page, final IOSListener.ISearchFileListener listener) {
        mApi.searchFile(ufdId, page).enqueue(new ResponseCallBack<ArrayList<FileContentResponse>>() {
            @Override
            protected void onDataResponse(Call<ApiResponse<ArrayList<FileContentResponse>>> call, ApiResponse<ArrayList<FileContentResponse>> responseData) {
                listener.onSearchFileListener(responseData.getData());
            }
        });
    }

    public void closeFile(int ufdId, final IOSListener.ICloseFileListener listener) {
        mApi.closeFile(ufdId).enqueue(new ResponseCallBack<Integer>() {
            @Override
            protected void onDataResponse(Call<ApiResponse<Integer>> call, ApiResponse<Integer> responseData) {
                listener.onCloseFileListener(responseData.getData());
            }
        });
    }

}
