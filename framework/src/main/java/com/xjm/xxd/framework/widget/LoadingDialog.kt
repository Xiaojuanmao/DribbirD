package com.xjm.xxd.framework.widget

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import com.xjm.xxd.framework.R
import com.xjm.xxd.framework.kotlinext.bindView
import com.xjm.xxd.skeleton.util.ResourceUtil

/**
 * Created by queda on 2018/3/12.
 */

class LoadingDialog : DialogFragment() {

    private var mMessage = ResourceUtil.getString(R.string.framework_loading)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(R.layout.framework_fragment_dialog_loading, container)
    }

    override fun show(manager: FragmentManager?, tag: String?) {
        super.show(manager, tag)
    }

    fun setMessage(msg: String?): LoadingDialog {
        mMessage = msg
        return this
    }

}