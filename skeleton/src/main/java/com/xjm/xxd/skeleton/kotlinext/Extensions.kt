package com.xjm.xxd.skeleton.kotlinext

import android.support.annotation.StringRes
import android.widget.Toast
import com.xjm.xxd.skeleton.BaseApp

/**
 * Created by queda on 2017/12/23.
 */

fun Boolean?.nullAsFalse(): Boolean {
    return this != null && this
}

fun Boolean?.nullAsTrue() : Boolean {
    return this == null || this
}

fun toast(msg: CharSequence?) {
    msg?.let {
        Toast.makeText(BaseApp.instance.applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}

fun toast(@StringRes strResId: Int?) {
    strResId?.let {
        Toast.makeText(BaseApp.instance.applicationContext, strResId, Toast.LENGTH_SHORT).show()
    }
}