package com.xjm.xxd.framework.api

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

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val tokenRequest = originalRequest.newBuilder()
                .addHeader(KEY_AUTH, "Bearer ${CommonStore.currentOauthToken}")
                .build()
        return chain.proceed(tokenRequest)
    }

    companion object {

        private val KEY_AUTH = "Authorization"
    }

}
