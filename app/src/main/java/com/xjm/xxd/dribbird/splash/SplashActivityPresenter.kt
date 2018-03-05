package com.xjm.xxd.dribbird.splash

import android.util.Log

import com.xjm.xxd.dribbird.account.TokenManager
import com.xjm.xxd.skeleton.util.rx.RxUtils

import java.util.concurrent.TimeUnit

import io.reactivex.Observable

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
                        Log.w(TAG, "checkAccount(), token exist -> main page")
                        viewer?.jumpToMainPage()
                    } else {
                        // token not exist, jump to login activity
                        Log.w(TAG, "checkAccount(), token not exist -> login activity")
                        viewer?.jumpToLoginActivity()
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
