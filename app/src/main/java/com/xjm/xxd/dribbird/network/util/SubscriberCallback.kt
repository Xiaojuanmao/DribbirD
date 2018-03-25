package com.xjm.xxd.dribbird.network.util

import com.xjm.xxd.dribbird.network.ERROR_CODE_UNKNOWN
import com.xjm.xxd.dribbird.network.ERROR_MSG_UNKNOWN
import com.xjm.xxd.framework.network.GsonManager
import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by queda on 2018/3/8.
 */

abstract class SubscriberCallback<T> : DisposableObserver<T>() {

    override fun onNext(value: T) {
        try {
            onSuccess(value)
        } catch (e: Throwable) {
            onError(e)
        }
    }

    override fun onComplete() {
        onFinish()
    }

    final override fun onError(e: Throwable) {
        e.printStackTrace()
        when (e) {

            is IOException -> {
                onNetError()
            }

            is HttpException -> {
                val httpException = e
                val errorBody = e.response()?.errorBody()
                try {
                    val errorResponse = GsonManager.gson().fromJson(errorBody?.string(), DribErrorResponse::class.java)
                    if (errorResponse == null) {
                        onNetError()
                    } else {
                        onApiError(httpException.code(), "${errorResponse.message}")
                    }
                } catch (e: Throwable) {
                    onApiError(httpException.code(), httpException.message() ?: "")
                }
            }

            else -> {
                onApiError(ERROR_CODE_UNKNOWN, ERROR_MSG_UNKNOWN)
            }
        }
        onFinish()
    }

    open fun onSuccess(t: T) {
        // empty
    }

    open fun onNetError() {
        // empty
    }

    open fun onApiError(errorCode: Int, errorMsg: String?) {
        // empty
    }

    /**
     * 在调用success或error函数之后
     * 最终都会调用一次finish
     */
    open fun onFinish() {
        // empty
    }

}