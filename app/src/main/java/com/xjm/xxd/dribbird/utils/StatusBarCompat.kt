package com.xjm.xxd.dribbird.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.support.annotation.ColorRes
import android.view.View
import android.view.ViewGroup
import com.xjm.xxd.dribbird.R

/**
 * User : queda
 * Date : 17-3-19
 */

object StatusBarCompat {

    private val COLOR_DEFAULT_ID = R.color.transparent

    @SuppressLint("ObsoleteSdkInt")
    @JvmOverloads
    fun compat(activity: Activity?, @ColorRes statusColor: Int = COLOR_DEFAULT_ID) {
        if (activity == null) {
            return
        }
        val resources = activity.resources
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.statusBarColor = resources.getColor(statusColor, null)
            return
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            val contentView = activity.findViewById(android.R.id.content) as ViewGroup
            val statusBarView = View(activity)
            val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(activity))
            statusBarView.setBackgroundColor(resources.getColor(statusColor, null))
            contentView.addView(statusBarView, lp)
        }

    }

    private fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}
