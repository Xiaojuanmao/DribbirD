package com.xjm.xxd.skeleton.util

import android.content.Context
import android.support.annotation.StringRes
import com.xjm.xxd.skeleton.BaseApp

/**
 * Created by queda on 2018/3/13.
 */

object ResourceUtil {

    private fun getContext(): Context? {
        return BaseApp.instance
    }

    fun getString(@StringRes strRes: Int): String? {
        val context = getContext()
        return context?.getString(strRes)
    }

}
