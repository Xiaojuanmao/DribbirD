package com.xjm.xxd.framework.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * User : queda
 * Date : 17-3-19
 * 用于向request中添加header的拦截器
 */

public class TokenInterceptor implements Interceptor {

    private String mToken;

    private static final String KEY_AUTH = "Authorization";

    public TokenInterceptor(String token) {
        mToken = "Bearer " + token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request tokenRequest = originalRequest.newBuilder()
                .addHeader(KEY_AUTH, mToken)
                .build();
        return chain.proceed(tokenRequest);
    }

}
