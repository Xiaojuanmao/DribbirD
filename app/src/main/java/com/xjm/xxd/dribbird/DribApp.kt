package com.xjm.xxd.dribbird

import com.xjm.xxd.dribbird.api.ApiConstants
import com.xjm.xxd.dribbird.api.HeaderInterceptor
import com.xjm.xxd.dribbird.api.PathFixInterceptor
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
        RetrofitManager.instance.init(ApiConstants.BASE_URL, arrayListOf(
                HeaderInterceptor(),
                PathFixInterceptor())
        )
    }

}
