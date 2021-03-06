package com.example.os.bussiness;


import com.example.os.bussiness.bean.BitMapData;
import com.example.os.bussiness.bean.FileContentResponse;
import com.example.os.bussiness.bean.FileDirRequest;
import com.example.os.bussiness.bean.FileResponse;
import com.example.os.bussiness.bean.SignUpRequestBody;
import com.example.os.network.NetworkFactory;
import com.example.os.network.response.ApiResponse;
import com.example.os.network.response.ResponseCallBack;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 获取磁盘位示图
     * @param listener
     */
    public void getDiskUseInfo(IOSListener.OnGetDiskUseInfoListener listener) {
        mApi.getDiskUseInfo().enqueue(new ResponseCallBack<List<BitMapData>>() {
            @Override
            protected void onDataResponse(Call<ApiResponse<List<BitMapData>>> call, ApiResponse<List<BitMapData>> responseData) {
                listener.onGetDiskUseInfo(responseData.getData());
            }
        });
    }


    public void identifyUser(IOSListener.OnIdentifyUserListener listener, String userName) {
        mApi.identifyUser(userName).enqueue(new ResponseCallBack<Integer>() {
            @Override
            protected void onDataResponse(Call<ApiResponse<Integer>> call, ApiResponse<Integer> responseData) {
                listener.onIdentifyUser(responseData.getData());
            }
        });
    }

    /**
     * 创建新用户
     * @param loadListener
     * @param body
     */
    public void createUser(IOSListener.LoadListener loadListener, SignUpRequestBody body) {
        mApi.createUser(body).enqueue(new ResponseCallBack<Integer>() {
            @Override
            protected void onDataResponse(Call<ApiResponse<Integer>> call, ApiResponse<Integer> responseData) {
                loadListener.onLoadListener(responseData.getData());
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

    /**
     * 登陆
     * @param loadListener
     * @param body
     */
    public void login(IOSListener.LoadListener loadListener, SignUpRequestBody body) {
        mApi.login(body).enqueue(new ResponseCallBack<Integer>() {
            @Override
            protected void onDataResponse(Call<ApiResponse<Integer>> call, ApiResponse<Integer> responseData) {
                loadListener.onLoadListener(responseData.getData());
            }
        });
    }

    /**
     * 创建目录项
     * @param dirRequest
     * @param listener
     */
    public void createFileDir(FileDirRequest dirRequest, final IOSListener.ICreateFileDir listener) {
        mApi.createFileDir(dirRequest).enqueue(new ResponseCallBack<Integer>() {
            @Override
            protected void onDataResponse(Call<ApiResponse<Integer>> call, ApiResponse<Integer> responseData) {
                listener.onCreateFileDir(responseData.getData());
            }
        });
    }

    /**
     * 创建文件
     * @param dirRequest
     * @param listener
     */
    public void createFile(FileDirRequest dirRequest, final IOSListener.ICreateFile listener) {
        mApi.createFile(dirRequest).enqueue(new ResponseCallBack<Integer>() {
            @Override
            protected void onDataResponse(Call<ApiResponse<Integer>> call, ApiResponse<Integer> responseData) {
                listener.onCreateFile(responseData.getData());
            }
        });
    }

    public void deleteFile(int ufdId, final IOSListener.IDeleteFile listener) {
        mApi.deleteFile(ufdId).enqueue(new ResponseCallBack<Integer>() {
            @Override
            protected void onDataResponse(Call<ApiResponse<Integer>> call, ApiResponse<Integer> responseData) {
                listener.onDeleteFile(responseData.getData());
            }
        });
    }

}
