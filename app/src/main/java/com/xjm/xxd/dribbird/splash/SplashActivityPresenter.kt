package com.xjm.xxd.dribbird.splash

import android.util.Log

import com.xjm.xxd.dribbird.account.TokenManager
import com.xjm.xxd.framework.api.RetrofitManager
import com.xjm.xxd.framework.utils.RxUtils

import java.util.concurrent.TimeUnit

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by queda on 2016/12/3.
 */

class SplashActivityPresenter: SplashActivityContract.Presenter() {

    override fun checkAccount() {

        addDispose(Observable.create<Boolean> { emitter ->
            val hasAvailableToken = TokenManager.hasAvailableToken()
            emitter.onNext(hasAvailableToken)
            emitter.onComplete()
        }.delay(3, TimeUnit.SECONDS)
                .compose(RxUtils.applyNetworkScheduler<Boolean>())
                .subscribe({ aBoolean ->
                    if (aBoolean) {
                        // 存在有效的token，设置好网络请求的token拦截器，跳转主页
                        val tokenBean = TokenManager.tokenBean
                        tokenBean?.let {
                            RetrofitManager.instance.addTokenInterceptor(it.accessToken)
                        }
                        viewer?.jumpToMainPage()
                        Log.w(TAG, "checkAccount(), token exist -> main page")
                    } else {
                        // token not exist, jump to login activity
                        viewer?.jumpToLoginActivity()
                        Log.w(TAG, "checkAccount(), token not exist -> login activity")
                    }
                }) { throwable ->
                    // exception, jump to login activity
                    Log.e(TAG, "checkAccount(), throwable : ", throwable)
                    viewer?.jumpToLoginActivity()
                })

    }

    companion object {
        private val TAG = SplashActivityPresenter::class.java.simpleName
    }

}
