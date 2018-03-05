package com.xjm.xxd.dribbird.splash

import com.xjm.xxd.skeleton.mvp.BasePresenter

/**
 * Created by queda on 2017/12/29.
 */

class SplashActivityContract {

    abstract class Presenter: BasePresenter<Viewer>() {

        // 检测是否存在已登陆的账户
        abstract fun checkAccount()

    }
    
    interface Viewer: com.xjm.xxd.skeleton.mvp.Viewer {

        fun jumpToMainPage()

        fun jumpToLoginActivity()

    }
    
}
