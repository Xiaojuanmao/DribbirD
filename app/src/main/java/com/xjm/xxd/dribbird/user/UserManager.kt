package com.xjm.xxd.dribbird.user

import com.xjm.xxd.dribbird.api.UserApi
import com.xjm.xxd.dribbird.model.UserModel
import com.xjm.xxd.framework.network.RetrofitManager
import com.xjm.xxd.skeleton.util.rx.RxUtils
import io.reactivex.Observable

/**
 * Created by queda on 2018/3/18.
 */

class UserManager private constructor() {

    private var mUserModel: UserModel? = null
    private val mUserApi by lazy { RetrofitManager.instance.api(UserApi::class.java) }

    fun syncUserInfo(): Observable<UserModel> {
        return mUserApi.getAuthenticatedUser()
                .compose(RxUtils.applyNetworkScheduler())
                .doOnNext {
                    mUserModel = it
                }
    }

    fun getCurrentUser(): UserModel? = mUserModel

    fun setCurrentUser(userModel: UserModel?) {
        mUserModel = userModel
    }

    companion object {
        val instance by lazy { UserManager() }
    }

}