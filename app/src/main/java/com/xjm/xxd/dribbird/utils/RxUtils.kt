package com.xjm.xxd.dribbird.utils

import io.reactivex.*
import org.reactivestreams.Publisher

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * User : xiaoxiaoda
 * Date : 17-4-19
 * Email : wonderfulifeel@gmail.com
 */

object RxUtils {

    @JvmStatic
    fun <T> applyNetworkScheduler(): ObservableTransformer<T, T> {
        return sNetworkScheduler as ObservableTransformer<T, T>
    }

    private val sNetworkScheduler = ObservableTransformer<Any, Any> { upstream: Observable<Any> ->
        upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}
