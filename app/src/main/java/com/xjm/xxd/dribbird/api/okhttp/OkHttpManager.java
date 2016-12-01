package com.xjm.xxd.dribbird.api.okhttp;

/**
 * Created by queda on 2016/12/1.
 */

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * wrap okhttp more easy to use
 */

public class OkHttpManager {

    private static OkHttpManager mInstance;

    private Gson mGson;
    private OkHttpClient mClient;

    private OkHttpManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        mClient = builder.build();
        mGson = new Gson();
    }

    public static OkHttpManager getInstance() {
        if (mInstance == null) {
            mInstance = new OkHttpManager();
        }
        return mInstance;
    }

    private
    @NonNull
    Request giveMeGetRequest(String url) {
        return new Request.Builder()
                .url(url)
                .get()
                .build();
    }

    private
    @NonNull
    Request giveMePostRequest(String url, Map<String, String> params) {
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        if (params != null) {
            Set<Map.Entry<String, String>> paramsSet = params.entrySet();
            if (!paramsSet.isEmpty()) {
                for (Map.Entry<String, String> entry : paramsSet) {
                    bodyBuilder.add(entry.getKey(), entry.getValue());
                }
            }
        }
        return new Request.Builder()
                .url(url)
                .post(bodyBuilder.build())
                .build();
    }

    private
    @Nullable
    Response sync(Request request) {
        Call call = mClient.newCall(request);
        try {
            return call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private
    @NonNull
    Call async(Request request, Callback callback) {
        Call call = mClient.newCall(request);
        call.enqueue(callback);
        return call;
    }

    public
    @Nullable
    Response getSync(String url) {
        Request request = giveMeGetRequest(url);
        return sync(request);
    }

    public
    @NonNull
    Call getAsync(String url, Callback callback) {
        Request request = giveMeGetRequest(url);
        return async(request, callback);
    }

    public
    @Nullable
    Response postSync(String url, Map<String, String> params) {
        Request request = giveMePostRequest(url, params);
        return sync(request);
    }

    public
    @NonNull
    Call postAsync(String url, Map<String, String> params, Callback callback) {
        Request request = giveMePostRequest(url, params);
        return async(request, callback);
    }

    public Gson getGson() {
        return mGson;
    }

}
