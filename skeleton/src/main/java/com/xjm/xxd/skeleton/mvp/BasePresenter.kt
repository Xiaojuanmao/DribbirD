package com.xjm.xxd.skeleton.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

/**
 * Created by queda on 2017/12/26.
 */

abstract class BasePresenter<V : Viewer> : Presenter<V> {

    protected var viewer: V? = null
        get() {
            return mViewerRef?.get()
        }

    private var mViewerRef: WeakReference<V>? = null

    private var mDisposable: CompositeDisposable? = null

    override fun attachViewer(v: V) {
        mViewerRef = WeakReference(v)
    }

    protected fun addDispose(disposable: Disposable) {
        if (mDisposable == null) {
            mDisposable = CompositeDisposable()
        }
        mDisposable?.add(disposable)
    }

    protected fun dispose() {
        mDisposable?.dispose()
        mDisposable = null
    }

    override fun detachViewer() {
        dispose()
        mViewerRef?.clear()
        mViewerRef = null
    }

}
