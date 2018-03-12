package com.xjm.xxd.dribbird.login

import com.xjm.xxd.dribbird.account.TokenBean

/**
 * Created by queda on 2016/12/2.
 */

interface LoginWebViewClientCallback {

    fun showLoading(msg: String?)

    fun hideLoading()

    fun loginSuccess(tokenBean: TokenBean)

    fun loginFailed()
}
