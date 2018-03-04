package com.xjm.xxd.framework.data

import android.content.Context
import android.content.SharedPreferences
import com.xjm.xxd.skeleton.BaseApp

/**
 * Created by queda on 2017/12/31.
 */

object UserStore {

    private val KEY_PREFIX = "user_"
    private val DEFAULT_USER = "default_user"

    private var mSharedPreferences: SharedPreferences? = null

    class UserPreference<T>(key: String, t: T) : Preference<T>(key, t) {
        override fun getSharedPreferences(): SharedPreferences? = mSharedPreferences
    }

    private fun refresh() {
        synchronized(UserStore::class.java) {
            var userID = currentUserId
            if (userID.isNullOrBlank()) {
                userID = DEFAULT_USER
            }
            userID = KEY_PREFIX + userID
            mSharedPreferences = BaseApp.instance.getSharedPreferences(userID, Context.MODE_PRIVATE)
        }
    }

    var currentUserId : String?
    get() {
        return CommonStore.currentUserId
    }
    set(value) {
        CommonStore.currentUserId = value
        refresh()
    }

}