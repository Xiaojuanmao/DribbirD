package com.xjm.xxd.dribbird.api.okhttp

import com.google.gson.Gson
import com.xjm.xxd.dribbird.DribApp
import com.xjm.xxd.dribbird.api.GsonManager
import com.xjm.xxd.dribbird.api.retrofit.RetrofitManager

import java.io.IOException

import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

/**
 * @author : xiaoxiaoda
 * date: 16-11-26
 * email: daque@hustunique.com
 */
class OkHttpManager private constructor() {

    val gson by lazy { GsonManager.gson() }
    private val mClient by lazy { OkHttpClient.Builder().build() }

    private fun giveMeGetRequest(url: String): Request {
        return Request.Builder()
                .url(url)
                .get()
                .build()
    }

    private fun giveMePostRequest(url: String, params: Map<String, String>?): Request {
        val bodyBuilder = FormBody.Builder()
        params?.let {
            for ((key, value) in it) {
                bodyBuilder.add(key, value)
            }
        }
        return Request.Builder()
                .url(url)
                .post(bodyBuilder.build())
                .build()
    }

    private fun sync(request: Request): Response? {
        val call = mClient.newCall(request)
        try {
            return call.execute()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    private fun async(request: Request, callback: Callback): Call {
        val call = mClient.newCall(request)
        call.enqueue(callback)
        return call
    }

    fun getSync(url: String): Response? {
        val request = giveMeGetRequest(url)
        return sync(request)
    }

    fun getAsync(url: String, callback: Callback): Call {
        val request = giveMeGetRequest(url)
        return async(request, callback)
    }

    fun postSync(url: String, params: Map<String, String>): Response? {
        val request = giveMePostRequest(url, params)
        return sync(request)
    }

    fun postAsync(url: String, params: Map<String, String>, callback: Callback): Call {
        val request = giveMePostRequest(url, params)
        return async(request, callback)
    }

    companion object {
        @JvmStatic
        val instance by lazy { Holder.INSTANCE }
    }

    private object Holder {
        val INSTANCE = OkHttpManager()
    }

}
