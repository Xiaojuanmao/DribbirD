package com.xjm.xxd.framework

import android.annotation.SuppressLint
import android.app.Application
import com.xjm.xxd.framework.api.RetrofitManager

/**
 * Created by queda on 2017/12/24.
 */

open class BaseApp: Application() {

    init {
        instance = this
        RetrofitManager.instance.init()
    }

    companion object {

        @JvmStatic
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: Application
    }

}