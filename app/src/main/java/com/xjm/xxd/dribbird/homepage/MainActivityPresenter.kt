package com.xjm.xxd.dribbird.homepage

import com.xjm.xxd.dribbird.model.UserModel
import com.xjm.xxd.dribbird.user.UserManager
import com.xjm.xxd.dribbird.network.util.SubscriberCallback

/**
 * Created by queda on 2016/11/21.
 */

class MainActivityPresenter : Presenter() {

    override fun loadUserModel() {
        val callback = object : SubscriberCallback<UserModel>() {
            override fun onSuccess(model: UserModel) {
                viewer?.showUserModel(model)
            }
        }
        UserManager.instance.syncUserInfo().subscribe(callback)
        addDispose(callback)
    }

}
