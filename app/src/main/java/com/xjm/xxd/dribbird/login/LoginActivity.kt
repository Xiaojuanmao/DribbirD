package com.xjm.xxd.dribbird.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import com.xjm.xxd.dribbird.R
import com.xjm.xxd.dribbird.account.TokenManager
import com.xjm.xxd.dribbird.homepage.MainActivity
import com.xjm.xxd.dribbird.widget.ProgressWebView
import com.xjm.xxd.framework.kotlinext.bindView
import com.xjm.xxd.framework.widget.DialogFactory
import com.xjm.xxd.skeleton.kotlinext.toast
import com.xjm.xxd.skeleton.mvp.MVPActivity

class LoginActivity : MVPActivity<LoginActivityContract.Presenter, LoginActivityContract.Viewer>(),
        LoginActivityContract.Viewer {

    private val mToolbar by bindView<Toolbar>(R.id.tool_bar)
    private val mWebView by bindView<ProgressWebView>(R.id.web_view)

    private var mLoadingDialog: AlertDialog? = null
    private val mDialogFactory by lazy { DialogFactory() }

    private var mWebViewClient: LoginWebViewClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()

        loadUrl(TokenManager.oAuth2Url)
    }

    override fun createPresenter(): LoginActivityContract.Presenter = LoginActivityPresenter()

    @SuppressLint("SetJavaScriptEnabled")
    private fun initViews() {
        mToolbar.setTitle(R.string.title_login_activity)
        setSupportActionBar(mToolbar)

        val settings = mWebView.settings
        settings.javaScriptEnabled = true

        mWebViewClient = LoginWebViewClient(presenter())
        mWebView.webViewClient = mWebViewClient
    }

    override fun loadUrl(url: String?) {
        url?.let {
            mWebView.loadUrl(it)
        }
    }

    override fun showLoading(msg: String?) {
        if (TextUtils.isEmpty(msg)) {
            return
        }
        showLoadingDialog(msg)
    }

    private fun showLoadingDialog(msg: String?) {
        mLoadingDialog?.dismiss()
        mLoadingDialog = mDialogFactory.createLoadingDialog(this@LoginActivity, msg)
        mLoadingDialog?.show()
    }

    override fun hideLoading() {
        mLoadingDialog?.dismiss()
    }

    override fun loginSuccess() {
        toast(R.string.authentication_success)
        MainActivity.open(this)
        finish()
    }

    override fun loginFailed() {
        toast(R.string.authentication_failed)
    }

    override fun onDestroy() {
        mLoadingDialog?.dismiss()
        mWebViewClient?.onDestroy()
        super.onDestroy()
    }

    companion object {

        const val LOADING_DIALOG_FRAGMENT_TAG = "LoadingDialog"

        @JvmStatic
        fun open(context: Context?) {
            context?.let {
                val intent = Intent(it, LoginActivity::class.java)
                it.startActivity(intent)
            }
        }
    }

}
