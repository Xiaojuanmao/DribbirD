package com.xjm.xxd.framework.data

import android.content.Context
import android.content.SharedPreferences
import com.xjm.xxd.framework.BaseApp

/**
 * Created by queda on 2017/12/24.
 */

object CommonStore {

    var tokenBean by CommonPreference("token_bean", "")

    private val mSharePreferences by lazy {
        BaseApp.instance.getSharedPreferences("common", Context.MODE_PRIVATE)
    }

    class CommonPreference<T>(key: String, t: T) : Preference<T>(key, t) {
        override fun getSharedPreferences(): SharedPreferences? = mSharePreferences
    }

}

