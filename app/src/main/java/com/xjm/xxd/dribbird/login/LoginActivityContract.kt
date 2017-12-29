package com.xjm.xxd.dribbird.login

import android.support.annotation.StringRes
import com.xjm.xxd.framework.base.mvp.BasePresenter
import com.xjm.xxd.framework.base.mvp.Viewer

/**
 * Created by queda on 2017/12/29.
 */

class LoginActivityContract {

    abstract class Presenter : BasePresenter<Viewer>(), LoginWebViewClientCallback {

    }

    interface Viewer : com.xjm.xxd.framework.base.mvp.Viewer {

        abstract fun loadUrl(url: String)

        abstract fun showLoading(msg: String)
        abstract fun showLoading(@StringRes strId: Int)

        abstract fun hideLoading()

        abstract fun loginSuccess()

        abstract fun loginFailed()

    }

}