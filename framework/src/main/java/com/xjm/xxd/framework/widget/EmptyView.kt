package com.xjm.xxd.framework.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.xjm.xxd.framework.kotlinext.bindView

import com.xjm.xxd.skeleton.R

class EmptyView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    private val tvMsg by bindView<TextView>(R.id.framework_tv_empty_message)

    init {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.framework_layout_empty_message, this, true)
    }

    fun setMessage(msg: String) {
        tvMsg.text = msg
    }

}
