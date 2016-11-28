package com.xjm.xxd.dribbird.api;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
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
        mHttpClient = buildOkHttpClient();
        mRetrofit = buildRetrofit(ApiConstants.BASE_URL);

    }

    /**
     * create httpclient instance
     * with logger interceptor
     * @return httpclient instance
     */
    private OkHttpClient buildOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(buildLoggingInterceptor());
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
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(mGson));

        return builder.build();
    }

    /**
     * create logger for request
     * @return logger interceptor instance
     */
    private HttpLoggingInterceptor buildLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}
