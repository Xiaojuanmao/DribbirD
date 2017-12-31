package com.xjm.xxd.framework.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xjm.xxd.framework.base.BaseActivity
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

        checkCompositeDisposable()
    }

    override fun onDestroy() {
        mDisposable?.dispose()
        mDisposable = null
        mPresenter?.detachViewer()
        super.onDestroy()
    }

    protected fun addDispose(disposable: Disposable) {
        checkCompositeDisposable()
        mDisposable?.add(disposable)
    }

    private fun checkCompositeDisposable() {
        if (mDisposable == null) {
            mDisposable = CompositeDisposable()
        }
    }

    protected fun presenter(): P {
        if (mPresenter == null) {
            mPresenter = createPresenter()
        }
        return mPresenter!!
    }

    protected abstract fun createPresenter(): P

}