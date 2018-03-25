package com.xjm.xxd.dribbird.homepage

import com.xjm.xxd.dribbird.model.UserModel
import com.xjm.xxd.skeleton.mvp.BasePresenter

/**
 * Created by queda on 2017/12/29.
 */

abstract class Presenter : BasePresenter<Viewer>() {
    abstract fun loadUserModel()
}

interface Viewer : com.xjm.xxd.skeleton.mvp.Viewer {
    fun showUserModel(userModel: UserModel?)
}
