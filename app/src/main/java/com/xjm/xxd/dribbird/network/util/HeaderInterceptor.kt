package com.xjm.xxd.dribbird.network.util

import com.xjm.xxd.framework.data.CommonStore
import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Response

/**
 * User : queda
 * Date : 17-3-19
 * 用于向request中添加header的拦截器
 */

class HeaderInterceptor : Interceptor {

    private val mOauthToken by lazy { CommonStore.currentOauthToken }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val tokenRequest = originalRequest.newBuilder()
                .addHeader(KEY_AUTH, "Bearer $mOauthToken")
                .build()
        return chain.proceed(tokenRequest)
    }

    companion object {

        private val KEY_AUTH = "Authorization"
    }

}
