package com.xjm.xxd.framework.base.mvp

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

    private var mCompositeDisposable: CompositeDisposable? = null

    override fun attachViewer(v: V) {
        mViewerRef = WeakReference(v)
        checkCompositeDisposable()
    }

    protected fun addDispose(disposable: Disposable) {
        checkCompositeDisposable()
        mCompositeDisposable?.add(disposable)
    }

    override fun detachViewer() {
        mViewerRef?.clear()
        mViewerRef = null
        mCompositeDisposable?.dispose()
        mCompositeDisposable = null
    }

    private fun checkCompositeDisposable() {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
    }

}
