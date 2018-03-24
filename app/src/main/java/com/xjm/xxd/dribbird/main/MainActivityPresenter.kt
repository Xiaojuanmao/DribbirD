package com.xjm.xxd.dribbird.main

import com.xjm.xxd.dribbird.model.UserModel
import com.xjm.xxd.dribbird.user.UserManager
import com.xjm.xxd.framework.network.api.SubscriberCallback

/**
 * Created by queda on 2016/11/21.
 */

class MainActivityPresenter : MainActivityContract.Presenter() {

    override fun loadUserModel() {
        val callback = object : SubscriberCallback<UserModel>() {
            override fun onSuccess(model: UserModel) {
                viewer?.showUserModel(model)
            }

            override fun onNetError() {
            }

            override fun onApiError(errorCode: Int, errorMsg: String?) {
            }

            override fun onFinish() {
            }

        }
        UserManager.instance.syncUserInfo().subscribe(callback)
        addDispose(callback)
    }

}
