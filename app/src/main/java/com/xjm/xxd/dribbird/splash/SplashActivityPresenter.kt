package com.xjm.xxd.dribbird.splash

import android.util.Log

import com.xjm.xxd.dribbird.account.TokenBean
import com.xjm.xxd.dribbird.account.TokenManager
import com.xjm.xxd.dribbird.api.retrofit.RetrofitManager
import com.xjm.xxd.dribbird.utils.RxUtils

import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber

import java.util.concurrent.TimeUnit

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.FlowableOnSubscribe
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

/**
 * Created by queda on 2016/12/3.
 */

class SplashActivityPresenter : ISplashActivityPresenter {

    private var mView: SplashActivityView? = null

    private val mCompositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }


    override fun bindIView(iView: SplashActivityView) {
        mView = iView
    }

    override fun checkAccount() {

        mCompositeDisposable.add(Observable.create<Boolean> { emitter ->
            val hasAvailableToken = TokenManager.hasAvailableToken()
            emitter.onNext(hasAvailableToken)
            emitter.onComplete()
        }.delay(3, TimeUnit.SECONDS)
                .compose(RxUtils.applyNetworkScheduler<Boolean>())
                .subscribe({ aBoolean ->
                    if (aBoolean!!) {
                        // 存在有效的token，设置好网络请求的token拦截器，跳转主页
                        val tokenBean = TokenManager.tokenBean
                        if (tokenBean != null) {
                            RetrofitManager.getInstance().addTokenInterceptor(tokenBean.accessToken)
                        }
                        mView!!.jumpToMainPage()
                        Log.w(TAG, "checkAccount(), token exist -> main page")
                    } else {
                        // token not exist, jump to login activity
                        mView!!.jumpToLoginActivity()
                        Log.w(TAG, "checkAccount(), token not exist -> login activity")
                    }
                }) { throwable ->
                    // exception, jump to login activity
                    Log.e(TAG, "checkAccount(), throwable : ", throwable)
                    mView!!.jumpToLoginActivity()
                })

    }

    override fun onDestroy() {
        mCompositeDisposable.dispose()
    }

    companion object {

        private val TAG = SplashActivityPresenter::class.java.simpleName
    }

}
