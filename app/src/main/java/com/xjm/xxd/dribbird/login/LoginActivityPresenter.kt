package com.xjm.xxd.dribbird.login

import android.support.annotation.StringRes

import com.xjm.xxd.dribbird.account.TokenBean
import com.xjm.xxd.framework.network.GsonManager
import com.xjm.xxd.framework.data.CommonStore

/**
 * Created by queda on 2016/12/2.
 */

class LoginActivityPresenter : LoginActivityContract.Presenter() {

    override fun showLoading(@StringRes strId: Int) {
        viewer?.showLoading(strId)
    }

    override fun hideLoading() {
        viewer?.hideLoading()
    }

    override fun loginSuccess(tokenBean: TokenBean) {
        CommonStore.currentOauthToken = tokenBean.accessToken ?: ""
        CommonStore.currentTokenBean = GsonManager.gson().toJson(tokenBean)
        viewer?.loginSuccess()
    }

    override fun loginFailed() {
        viewer?.loginFailed()
    }

}
