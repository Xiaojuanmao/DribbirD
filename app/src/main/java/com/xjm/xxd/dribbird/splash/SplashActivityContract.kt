package com.xjm.xxd.dribbird.splash

import com.xjm.xxd.framework.mvp.BasePresenter
import com.xjm.xxd.framework.mvp.Viewer

/**
 * Created by queda on 2017/12/29.
 */

class SplashActivityContract {

    abstract class Presenter: BasePresenter<Viewer>() {

        // 检测是否存在已登陆的账户
        abstract fun checkAccount()

    }
    
    interface Viewer: com.xjm.xxd.framework.mvp.Viewer {

        fun jumpToMainPage()

        fun jumpToLoginActivity()

    }
    
}
