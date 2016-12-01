package com.xjm.xxd.dribbird.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.xjm.xxd.dribbird.DribApp;


public class SharedPreferencesUitl {

    private static final String SYS_CONFIG_PREFS_NAME = "sysconfig";

    private static SharedPreferences getSysSharedPreferences() {
        return DribApp.getInstance().getSharedPreferences(SYS_CONFIG_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static String getDefaultString(String key, String defaultString) {
        SharedPreferences sysPreferences = getSysSharedPreferences();
        return sysPreferences.getString(key, defaultString);
    }

    public static void setDefaultString(String key, String defaultString) {
        SharedPreferences sysPreferences = getSysSharedPreferences();
        SharedPreferences.Editor editor = sysPreferences.edit();
        editor.putString(key, defaultString);
        editor.apply();
    }

    public static boolean getDefaultBool(String key, boolean defaultValue) {
        SharedPreferences sysPreferences = getSysSharedPreferences();
        return sysPreferences.getBoolean(key, defaultValue);
    }

    public static void setDefaultBool(String key, boolean bValue) {
        SharedPreferences sysPreferences = getSysSharedPreferences();
        SharedPreferences.Editor editor = sysPreferences.edit();
        editor.putBoolean(key, bValue);
        editor.apply();
    }

    public static int getDefaultInteger(String key, int defaultValue) {
        SharedPreferences sysPreferences = getSysSharedPreferences();
        return sysPreferences.getInt(key, defaultValue);
    }

    public static void setDefaultInteger(String key, int defaultValue) {
        SharedPreferences sysPreferences = getSysSharedPreferences();
        SharedPreferences.Editor editor = sysPreferences.edit();
        editor.putInt(key, defaultValue);
        editor.apply();
    }

    public static long getDefaultLong(String key, long defaultValue) {
        SharedPreferences sysPreferences = getSysSharedPreferences();
        return sysPreferences.getLong(key, defaultValue);
    }

    public static void setDefaultLong(String key, long defaultValue) {
        SharedPreferences sysPreferences = getSysSharedPreferences();
        SharedPreferences.Editor editor = sysPreferences.edit();
        editor.putLong(key, defaultValue);
        editor.apply();
    }

}
