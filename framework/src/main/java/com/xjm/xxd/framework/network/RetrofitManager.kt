package com.xjm.xxd.framework.network

import okhttp3.Interceptor
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
     * 添加网络请求token的拦截器
     */
    fun init(baseUrl: String, interceptors: List<Interceptor>) {
        val httpClient = buildOkHttpClient(interceptors)
        mRetrofit = buildRetrofit(baseUrl, httpClient)
    }

    /**
     * create httpclient instance
     * with logger interceptor
     *
     * @return httpclient instance
     */
    private fun buildOkHttpClient(interceptors: List<Interceptor>): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(buildLoggingInterceptor())
        interceptors.forEach { builder.addInterceptor(it) }
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

    fun <T : Any> api(clazz: Class<T>): T {
        if (mApiMap[clazz] != null) {
            return mApiMap[clazz] as T
        }
        val api = mRetrofit!!.create(clazz)
        mApiMap[clazz] = api
        return api
    }

    companion object {
        @JvmStatic
        val instance by lazy { RetrofitManager() }
    }

}
