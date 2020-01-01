package com.example.os.bussiness;


import com.example.os.bussiness.bean.BitMapData;
import com.example.os.bussiness.bean.FileResponse;
import com.example.os.bussiness.bean.SignUpRequestBody;
import com.example.os.network.response.ApiResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @GET("/disk/show_bitmap")
    Call<ApiResponse<List<BitMapData>>> getDiskUseInfo();


    @GET("/user/search")
    Call<ApiResponse<Integer>> identifyUser(@Query("userName") String userName);

    @POST("/user/create_user")
    Call<ApiResponse<Integer>> createUser(@Body SignUpRequestBody body);

    @POST("/user/login")
    Call<ApiResponse<Integer>> login(@Body SignUpRequestBody body);
}
