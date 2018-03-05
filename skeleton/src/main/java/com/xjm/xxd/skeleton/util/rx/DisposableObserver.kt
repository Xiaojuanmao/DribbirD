package com.xjm.xxd.skeleton.util.rx

import android.support.annotation.CallSuper
import com.xjm.xxd.skeleton.kotlinext.nullAsTrue
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by queda on 2017/12/31.
 */

abstract class DisposableObserver<T>: Observer<T> {

    private var mDisposable: Disposable? = null

    @CallSuper
    override fun onSubscribe(d: Disposable?) {
        mDisposable = d
    }

    fun dispose(): Boolean {
        return if (mDisposable?.isDisposed.nullAsTrue()) {
            false
        } else {
            mDisposable?.dispose()
            true
        }
    }

}