package com.xjm.xxd.dribbird

import com.xjm.xxd.framework.api.RetrofitManager
import com.xjm.xxd.skeleton.BaseApp

/**
 * User : xiaoxiaoda
 * Date : 2016/11/21
 * Email : wonderfulifeel@gmail.com
 */

class DribApp : BaseApp() {

    override fun onCreate() {
        super.onCreate()
        RetrofitManager.instance.init()
    }

}
