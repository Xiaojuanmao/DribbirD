package com.xjm.xxd.skeleton.kotlinext

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

/**
 * Created by queda on 2018/3/24.
 */

fun <T> Observable<T>?.subscribeSafely() {
    this?.subscribe({}, {})
}

fun <T> Observable<T>?.subscribeSafelyWithDisposable(): Disposable? {
    return this?.subscribe({}, {})
}