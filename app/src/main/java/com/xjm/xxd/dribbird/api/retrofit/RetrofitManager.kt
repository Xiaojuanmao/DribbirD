package com.xjm.xxd.dribbird.api.retrofit

import android.text.TextUtils

import com.google.gson.Gson
import com.xjm.xxd.dribbird.api.ApiConstants
import com.xjm.xxd.dribbird.api.GsonManager
import com.xjm.xxd.dribbird.api.interceptor.TokenInterceptor

import java.util.HashMap

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author : xiaoxiaoda
 * date: 16-11-26
 * email: daque@hustunique.com
 */
class RetrofitManager private constructor() {

    private val mGson by lazy { GsonManager.gson() }
    private var mRetrofit: Retrofit? = null

    private val mApiMap by lazy { hashMapOf<Class<*>, Any>() }

    /**
     * create httpclient instance
     * with logger interceptor
     *
     * @return httpclient instance
     */
    private fun buildOkHttpClient(token: String?): OkHttpClient {
        val builder = OkHttpClient.Builder()
        //        builder.addInterceptor(buildLoggingInterceptor());
        if (!token.isNullOrEmpty()) {
            builder.addNetworkInterceptor(TokenInterceptor(token))
        }
        return builder.build()
    }

    /**
     * create retrofit instance
     * according to httpclient、rx、gson ect
     *
     * @return retrofit instance
     */
    private fun buildRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
        builder.client(client)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(mGson))

        return builder.build()
    }

    /**
     * 构造一个用于网络请求日志功能的拦截器
     *
     * @return logger interceptor instance
     */
    private fun buildLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    /**
     * 添加网络请求token的拦截器
     */
    fun addTokenInterceptor(token: String?) {
        val httpClient = buildOkHttpClient(token)
        mRetrofit = buildRetrofit(ApiConstants.BASE_URL, httpClient)
    }

    fun <T : Any> api(clazz: Class<T>): T {
        if (mApiMap[clazz] != null) {
            return mApiMap[clazz] as T
        }
        val api = mRetrofit!!.create(clazz)
        mApiMap.put(clazz, api)
        return api
    }


    companion object {
        @JvmStatic
        val instance by lazy { Holder.INSTANCE }
    }

    private object Holder {
        val INSTANCE = RetrofitManager()
    }

}
