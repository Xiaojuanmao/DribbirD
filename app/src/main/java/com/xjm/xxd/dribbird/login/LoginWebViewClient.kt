package com.xjm.xxd.dribbird.login

import android.net.Uri
import android.text.TextUtils
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.xjm.xxd.dribbird.R
import com.xjm.xxd.dribbird.account.TokenBean
import com.xjm.xxd.dribbird.account.TokenManager
import com.xjm.xxd.framework.api.ApiConstants
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

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (TextUtils.isEmpty(url)) {
            // TODO : url is null
        } else {
            // url is match with oauth request url
            if (TokenManager.isMatchRedirectUrl(url)) {
                // get the return code
                val uri = Uri.parse(url)
                val returnCode = uri.getQueryParameter(ApiConstants.CODE)
                processReturnCode(returnCode)
            } else {
                view?.loadUrl(url)
            }
        }
        return true
    }

    private fun processReturnCode(returnCode: String?) {
        if (!returnCode.isNullOrEmpty()) {
            // request for access token with return code
            mCallback?.showLoading(R.string.being_authorized)
            Observable.just(returnCode)
                    .map { s -> TokenManager.requestForToken(s) }
                    .compose(RxUtils.applyNetworkScheduler())
                    .subscribe(object : Observer<TokenBean?> {

                        override fun onNext(tokenBean: TokenBean?) {
                            mCallback?.hideLoading()
                            if (tokenBean != null) {
                                // authentic success
                                mCallback?.loginSuccess(tokenBean)
                            } else {
                                // auth failed
                                mCallback?.loginFailed()
                            }
                        }

                        override fun onComplete() {

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
