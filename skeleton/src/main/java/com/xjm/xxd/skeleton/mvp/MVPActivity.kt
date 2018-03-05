package com.xjm.xxd.skeleton.mvp

import android.os.Bundle
import com.xjm.xxd.skeleton.base.BaseActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by queda on 2017/12/29.
 */

abstract class MVPActivity<out P : BasePresenter<V>, V : Viewer> : BaseActivity() {

    private var mPresenter: P? = null
    private var mDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (this !is Viewer) {
            throw IllegalStateException("Viewer not implemented")
        }
        mPresenter = createPresenter()
        mPresenter?.attachViewer(this as V)
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

    protected fun presenter(): P {
        return if (mPresenter == null) {
            val presenter = createPresenter()
            mPresenter = presenter
            presenter
        } else {
            mPresenter!!
        }
    }

    override fun onDestroy() {
        dispose()
        mPresenter?.detachViewer()
        super.onDestroy()
    }

    protected abstract fun createPresenter(): P

}