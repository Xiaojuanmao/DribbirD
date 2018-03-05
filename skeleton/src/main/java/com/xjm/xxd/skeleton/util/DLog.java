package com.xjm.xxd.skeleton.util;

import android.util.Log;

import com.google.gson.Gson;
import com.xjm.xxd.skeleton.BuildConfig;

public class DLog {
    public static void i(String msg) {
        if (isDebug()) {
            Log.i(getCallerName(), msg);
        }
    }

    public static void d(String msg) {
        if (isDebug()) {
            Log.d(getCallerName(), msg);
        }
    }

    public static void v(String msg) {
        if (isDebug()) {
            Log.v(getCallerName(), msg);
        }
    }

    public static void e(String msg) {
        if (isDebug()) {
            Log.e(getCallerName(), msg);
        }
    }

    public static void e(String msg, Throwable e) {
        if (isDebug()) {
            Log.e(getCallerName(), msg, e);
        }
    }

    public static void w(String msg) {
        if (isDebug()) {
            Log.w(getCallerName(), msg);
        }
    }

    private static String getCallerName() {
        StackTraceElement[] elements = new Throwable().getStackTrace();
        return elements[2].getClassName();
    }

    private static Boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    public static String toJson(Object obj) {
        return new Gson().toJson(obj);
    }
}
