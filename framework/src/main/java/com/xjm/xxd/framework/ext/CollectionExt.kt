package com.xjm.xxd.framework.ext

fun <T> List<T>?.isNullOrEmpty() = this == null || this.isEmpty()

fun <T> Array<out T>?.isNullOrEmpty() : Boolean = this == null || this.isEmpty()
