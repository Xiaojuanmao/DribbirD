package com.xjm.xxd.dribbird.login

import android.content.Intent
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.widget.Toolbar
import android.text.TextUtils

import com.xjm.xxd.dribbird.R
import com.xjm.xxd.dribbird.main.MainActivity
import com.xjm.xxd.framework.base.BaseActivity
import com.xjm.xxd.dribbird.base.BaseDialog
import com.xjm.xxd.dribbird.widget.ProgressWebView

import butterknife.ButterKnife
import com.xjm.xxd.framework.ext.bindView
import com.xjm.xxd.framework.ext.toast

class LoginActivity : BaseActivity(), LoginActivityView {

    private val mToolbar by bindView<Toolbar>(R.id.tool_bar)
    private val mWebView by bindView<ProgressWebView>(R.id.web_view)

    private var mPresenter: ILoginActivityPresenter? = null

    private var mBaseDialog: BaseDialog? = null
    private var mWebViewClient: LoginWebViewClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)
        mPresenter = LoginActivityPresenter()

        initViews()

        mPresenter!!.bindIView(this)
        mPresenter!!.init()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter!!.onDestroy()
        }

        if (mWebViewClient != null) {
            mWebViewClient!!.onDestroy()
        }
    }

    private fun initViews() {
        mToolbar.setTitle(R.string.title_login)
        setSupportActionBar(mToolbar)

        val settings = mWebView.settings
        settings.javaScriptEnabled = true
        mWebViewClient = LoginWebViewClient(mPresenter)
        mWebView.setWebViewClient(mWebViewClient)
    }

    override fun loadUrl(url: String) {
        if (TextUtils.isEmpty(url)) {
            return
        }
        mWebView.loadUrl(url)
    }

    override fun showLoading(msg: String) {
        if (TextUtils.isEmpty(msg)) {
            return
        }
        if (mBaseDialog == null) {
            val builder = BaseDialog.Builder()
            builder.progressBar(true)
            mBaseDialog = builder.build()
        }
        mBaseDialog!!.message(msg)
        mBaseDialog!!.show(fragmentManager, TAG)
    }

    override fun showLoading(@StringRes strId: Int) {
        if (strId <= 0) {
            return
        }
        showLoading(getString(strId))
    }

    override fun hideLoading() {
        if (mBaseDialog != null && mBaseDialog!!.isVisible) {
            mBaseDialog!!.dismiss()
        }
    }

    override fun loginSuccess() {
        toast(R.string.authentication_success)
        MainActivity.open(this)
        finish()
    }

    override fun loginFailed() {
        toast(R.string.authentication_failed)
    }

    companion object {

        private val TAG = LoginActivity::class.java.simpleName

        fun open(activity: BaseActivity?) {
            if (activity == null) {
                return
            }
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
        }
    }

}
