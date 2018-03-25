package com.xjm.xxd.dribbird

import com.facebook.drawee.backends.pipeline.Fresco
import com.xjm.xxd.dribbird.network.BASE_URL
import com.xjm.xxd.dribbird.network.util.HeaderInterceptor
import com.xjm.xxd.dribbird.network.util.PathFixInterceptor
import com.xjm.xxd.framework.network.RetrofitManager
import com.xjm.xxd.skeleton.BaseApp

/**
 * User : xiaoxiaoda
 * Date : 2016/11/21
 * Email : wonderfulifeel@gmail.com
 */

class DribApp : BaseApp() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        RetrofitManager.instance.init(BASE_URL, arrayListOf(
                HeaderInterceptor(),
                PathFixInterceptor()))

    }

}
