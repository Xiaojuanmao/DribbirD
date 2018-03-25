package com.xjm.xxd.dribbird.api

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by queda on 2018/3/25.
 */

class PathFixInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val originRequest = chain.request()
        var requestUrl = originRequest.url().toString()
        requestUrl = requestUrl.replace(ApiConstants.BASE_URL, "${ApiConstants.BASE_URL}${ApiConstants.API_VERSION}")
        builder.url(requestUrl)
        return chain.proceed(builder.build())
    }

}