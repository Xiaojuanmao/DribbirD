package com.xjm.xxd.skeleton.util

import android.app.ActivityManager
import android.content.Context
import android.os.Process

/**
 * Created by queda on 2018/3/4.
 */

fun isMainProcess(context: Context): Boolean {
    val processName = getProcessName(context)
    return processName == null || (processName == context.packageName)
}

fun getProcessName(context: Context): String? {
    val processPid = Process.myPid()
    try {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?
        val runningAppProcesses = am?.runningAppProcesses
        val currentProcess = runningAppProcesses?.find { it.pid == processPid }
        return currentProcess?.processName
    } catch (e: Exception) {
    }
    return null
}