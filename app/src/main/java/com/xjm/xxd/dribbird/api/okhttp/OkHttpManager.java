package com.xjm.xxd.dribbird.api.okhttp;

/**
 * Created by queda on 2016/12/1.
 */

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * wrap okhttp more easy to use
 */

public class OkHttpManager {

    private static OkHttpManager mInstance;

    private OkHttpClient mClient;

    private OkHttpManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        mClient = builder.build();
    }

    public static OkHttpManager getInstance() {
        if (mInstance == null) {
            mInstance = new OkHttpManager();
        }
        return mInstance;
    }

    public void get(String url) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
    }
}
