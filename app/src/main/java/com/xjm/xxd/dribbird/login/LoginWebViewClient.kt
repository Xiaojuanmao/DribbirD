package com.xjm.xxd.dribbird.login

import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.xjm.xxd.dribbird.R
import com.xjm.xxd.dribbird.account.TokenBean
import com.xjm.xxd.dribbird.account.TokenManager
import com.xjm.xxd.dribbird.network.CODE
import com.xjm.xxd.skeleton.util.ResourceUtil
import com.xjm.xxd.skeleton.util.rx.RxUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author : xiaoxiaoda
 * date: 16-12-2
 * email: daque@hustunique.com
 */
class LoginWebViewClient(callback: LoginWebViewClientCallback) : WebViewClient() {

    private val mAuthDisposable by lazy { CompositeDisposable() }

    private val mCallback: LoginWebViewClientCallback?

    init {
        mCallback = callback
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        request?.let {
            val uri = it.url
            if (TokenManager.isMatchRedirectUrl(uri)) {
                // get the return code
                val returnCode = uri.getQueryParameter(CODE)
                processReturnCode(returnCode)
            } else {
                view?.loadUrl(uri?.toString())
            }
        }
        return true;
    }

    private fun processReturnCode(returnCode: String?) {
        returnCode?.let {
            // request for access token with return code
            mCallback?.showLoading(ResourceUtil.getString(R.string.being_authorized))
            Observable.just(returnCode)
                    .map { s -> TokenManager.requestForToken(s) }
                    .compose(RxUtils.applyNetworkScheduler())
                    .subscribe(object : Observer<TokenBean?> {

                        override fun onNext(tokenBean: TokenBean?) {
                            if (tokenBean != null) {
                                // authentic success
                                mCallback?.loginSuccess(tokenBean)
                            } else {
                                // auth failed
                                mCallback?.loginFailed()
                            }
                        }

                        override fun onComplete() {
                            mCallback?.hideLoading()
                        }

                        override fun onSubscribe(d: Disposable?) {
                            d?.let { mAuthDisposable.add(it) }
                        }

                        override fun onError(e: Throwable?) {
                            e?.let {
                                Log.w(TAG, "LoginWebViewClient request for access token error ", it)
                            }
                            mCallback?.hideLoading()
                            mCallback?.loginFailed()
                        }
                    })
        }
    }

    internal fun onDestroy() {
        mAuthDisposable.dispose()
    }

    companion object {
        private val TAG = LoginWebViewClient::class.java.simpleName
    }

}
