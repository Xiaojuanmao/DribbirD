package com.xjm.xxd.framework.network

import com.google.gson.Gson

/**
 * Created by queda on 2017/12/23.
 */

object GsonManager {

    private var gson: Gson? = null

    @JvmStatic
    fun gson(): Gson {
        if (gson == null) {
            gson = Gson()
        }
        return gson!!
    }

}