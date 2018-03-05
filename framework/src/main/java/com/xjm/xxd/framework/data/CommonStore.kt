package com.xjm.xxd.framework.data

import android.content.Context
import android.content.SharedPreferences
import com.xjm.xxd.skeleton.BaseApp
import com.xjm.xxd.skeleton.data.Preference

/**
 * Created by queda on 2017/12/24.
 */

object CommonStore {

    var currentOauthToken by CommonPreference("oauth_token", "")
    var currentTokenBean by CommonPreference("token_bean", "")

    var currentUserId by CommonPreference<String?>("current_user_id", null)

    private val mSharePreferences by lazy {
        BaseApp.instance.getSharedPreferences("common", Context.MODE_PRIVATE)
    }

    class CommonPreference<T>(key: String, t: T) : Preference<T>(key, t) {
        override fun getSharedPreferences(): SharedPreferences? = mSharePreferences
    }

}

