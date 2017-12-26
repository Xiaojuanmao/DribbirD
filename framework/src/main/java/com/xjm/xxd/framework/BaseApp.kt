package com.xjm.xxd.framework

import android.annotation.SuppressLint
import android.app.Application

/**
 * Created by queda on 2017/12/24.
 */

open class BaseApp: Application() {

    init {
        instance = this
    }

    companion object {

        @JvmStatic
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: Application
    }

}