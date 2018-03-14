package com.xjm.xxd.framework.widget

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.TextView
import com.xjm.xxd.framework.R

/**
 * Created by queda on 2018/3/12.
 */

class DialogFactory {

    public fun createLoadingDialog(ctx: Context, msg: String?): AlertDialog {
        val builder = AlertDialog.Builder(ctx)
        val contentView = LayoutInflater.from(ctx).inflate(R.layout.framework_fragment_dialog_loading, null)
        msg?.let {
            val tvMsg = contentView.findViewById<TextView>(R.id.framework_tv_loading_msg)
            tvMsg.text = it
        }
        builder.setView(contentView)
        return builder.create()
    }

}