package com.example.os.network;

import com.example.os.common.CacheKey;
import com.example.os.utils.CacheUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author YangZhaoxin.
 * @since 2019/10/8 17:11.
 * email yangzhaoxin@hrsoft.net.
 */

public class NetworkFactory {

    private static OkHttpClient sOkHttpClient;
    private static Retrofit sRetrofit;

    /**
     * 生成Service接口
     * @param apiService
     * @param <T>
     * @return
     */
    public static <T> T getService(Class<T> apiService) {
        return getRetrofit().create(apiService);
    }

    /**
     * 构造Retrofit,设置相关参数
     * @return
     */
    private static Retrofit getRetrofit() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .client(getOkhttpClient())
                    .baseUrl(HttpConstants.BASE_URL)
                    //增加对返回值为自定义Response类型的支持,默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }

    private static OkHttpClient getOkhttpClient() {
        if (sOkHttpClient == null) {
            sOkHttpClient = new OkHttpClient.Builder()
                    //设置超时时间
                    .connectTimeout(HttpConstants.APP_SERVER_CONNECT_TIME_OUT, TimeUnit.SECONDS)
                    //设置出现错误时重新连接
                    .retryOnConnectionFailure(true)
                    //添加应用拦截器，统一打印请求与响应的json
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    //添加网络拦截器，发送请求时加入token
                    .addNetworkInterceptor(getNetworkInterceptor())
                    .build();
        }
        return sOkHttpClient;
    }

    private static Interceptor getNetworkInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String token = CacheUtil.getSP().getString(CacheKey.TOKEN, "");
                token = "0369a818dd8648dfba404601be9ea607";
                //请求时加入token
                Request request = chain.request().newBuilder()
                        .header(CacheKey.TOKEN, token)
                        .build();
                return chain.proceed(request);
            }
        };
    }

}
