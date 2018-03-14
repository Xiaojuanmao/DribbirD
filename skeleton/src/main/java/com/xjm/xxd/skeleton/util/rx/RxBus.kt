package com.xjm.xxd.skeleton.util.rx

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by queda on 2018/3/14.
 */

class RxBus {

    private val mBus = PublishRelay.create<Any>().toSerialized()

    private val mStickyMap = ConcurrentHashMap<Class<out Any>, Any>()

    public fun post(event: Any) {
        mBus.accept(event)
    }

    public fun <T> receive(eventType: Class<T>): Observable<T> {
        return mBus.ofType(eventType)
    }

    public fun postSticky(stickyEvent: Any) {
        mBus.accept(stickyEvent)
        mStickyMap[stickyEvent::class.java] = stickyEvent
    }

    public fun <T> receiveSticky(eventType: Class<T>): Observable<T> {
        val stickyEvent = mStickyMap[eventType]
        val receiveObservable = receive(eventType)
        return if (stickyEvent == null) {
            receiveObservable
        } else {
            Observable.create<T> { emitter ->
                if (!emitter.isDisposed) {
                    emitter.onNext(stickyEvent as T)
                }
            }.mergeWith(receiveObservable)
        }
    }

    public fun clearSticky() {
        mStickyMap.clear()
    }

    companion object {
        @JvmStatic
        val instance by lazy { HOLDER.INSTANCE }
    }

    private object HOLDER {
        val INSTANCE = RxBus()
    }

}