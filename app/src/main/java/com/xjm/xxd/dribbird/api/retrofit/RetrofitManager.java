package com.xjm.xxd.dribbird.api.retrofit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.xjm.xxd.dribbird.api.ApiConstants;
import com.xjm.xxd.dribbird.api.interceptor.TokenInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author : xiaoxiaoda
 *         date: 16-11-26
 *         email: daque@hustunique.com
 */
public class RetrofitManager {

    private Gson mGson;
    private OkHttpClient mHttpClient;
    private Retrofit mRetrofit;

    private static RetrofitManager mInstance;

    public static RetrofitManager getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitManager();
        }
        return mInstance;
    }

    private RetrofitManager() {
        mGson = new Gson();
    }

    /**
     * create httpclient instance
     * with logger interceptor
     * @return httpclient instance
     */
    private OkHttpClient buildOkHttpClient(@Nullable String token) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.addInterceptor(buildLoggingInterceptor());
        if (!TextUtils.isEmpty(token)) {
            builder.addNetworkInterceptor(new TokenInterceptor(token));
        }
        return builder.build();
    }

    /**
     * create retrofit instance
     * according to httpclient、rx、gson ect
     * @return retrofit instance
     */
    private Retrofit buildRetrofit(@NonNull String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(mHttpClient)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(mGson));

        return builder.build();
    }

    /**
     * 构造一个用于网络请求日志功能的拦截器
     * @return logger interceptor instance
     */
    private HttpLoggingInterceptor buildLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    /**
     * 添加网络请求token的拦截器
     */
    public void addTokenInterceptor(String token) {
        mHttpClient = buildOkHttpClient(token);
        mRetrofit = buildRetrofit(ApiConstants.BASE_URL);
    }


    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}
