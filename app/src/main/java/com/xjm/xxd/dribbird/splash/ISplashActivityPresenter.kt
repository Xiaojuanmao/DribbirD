package com.xjm.xxd.dribbird.splash

import com.xjm.xxd.framework.base.IPresenter

/**
 * Created by queda on 2016/12/3.
 */

interface ISplashActivityPresenter : IPresenter<SplashActivityView> {

    // 检测是否存在已登陆的账户
    fun checkAccount()

}
