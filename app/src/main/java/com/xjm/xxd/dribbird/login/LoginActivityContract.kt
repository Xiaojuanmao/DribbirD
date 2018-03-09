package com.xjm.xxd.dribbird.login

import android.support.annotation.StringRes
import com.xjm.xxd.skeleton.mvp.BasePresenter

/**
 * Created by queda on 2017/12/29.
 */

class LoginActivityContract {

    abstract class Presenter : BasePresenter<Viewer>(), LoginWebViewClientCallback {

    }

    interface Viewer : com.xjm.xxd.skeleton.mvp.Viewer {

        fun loadUrl(url: String?)

        fun showLoading(msg: String)

        fun showLoading(@StringRes strId: Int)

        fun hideLoading()

        fun loginSuccess()

        fun loginFailed()

    }

}