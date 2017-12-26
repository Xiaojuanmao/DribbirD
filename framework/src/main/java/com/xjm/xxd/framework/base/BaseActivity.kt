package com.xjm.xxd.framework.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by queda on 2016/11/21.
 */

abstract class BaseActivity : AppCompatActivity() {

    protected val tag: String
        get() = this.javaClass.simpleName

    private var mCompositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
    }

    protected fun addDispose(disposable: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable?.add(disposable)
    }

    override fun onDestroy() {
        mCompositeDisposable?.dispose()
        mCompositeDisposable = null
        super.onDestroy()
    }

}
