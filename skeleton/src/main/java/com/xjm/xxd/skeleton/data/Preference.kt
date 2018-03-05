package com.xjm.xxd.skeleton.data

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by queda on 2017/12/24.
 */

abstract class Preference<T>(val key: String, val defaultValue: T) : ReadWriteProperty<Any?, T> {

    @Suppress("IMPLICIT_CAST_TO_ANY")
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val sharedPreferences = getSharedPreferences() ?: return defaultValue
        return with(sharedPreferences) {
            when (defaultValue) {
                is Long -> getLong(key, defaultValue)
                is Int -> getInt(key, defaultValue)
                is String -> getString(key, defaultValue)
                is Boolean -> getBoolean(key, defaultValue)
                is Float -> getFloat(key, defaultValue)
                else -> throw IllegalArgumentException("This type can be saved into Preferences")
            }
        } as T
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        val sharedPreferences = getSharedPreferences() ?: return
        val editor = sharedPreferences.edit()
        with(editor) {
            when (value) {
                is Long -> putLong(key, value)
                is Int -> putInt(key, value)
                is String -> putString(key, value)
                is Boolean -> putBoolean(key, value)
                is Float -> putFloat(key, value)
                else -> throw IllegalArgumentException("This type can be saved into Preferences")
            }.apply()
        }
    }

    abstract fun getSharedPreferences(): SharedPreferences?
}