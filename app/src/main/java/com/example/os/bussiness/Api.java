package com.example.os.bussiness;


import com.example.os.bussiness.bean.FileResponse;
import com.example.os.network.response.ApiResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author YangZhaoxin.
 * @since 2020/1/1 13:31.
 * email yangzhaoxin@hrsoft.net.
 */

public interface Api {

    @GET("/mfd/search/{mfdId}")
    Call<ApiResponse<ArrayList<FileResponse>>> getAllFileByMFDId(@Path("mfdId") int mfdId);


    @GET("/mfd/open_file")
    Call<ApiResponse<Integer>> openFile(@Query("ufdId") int ufdId);
}
