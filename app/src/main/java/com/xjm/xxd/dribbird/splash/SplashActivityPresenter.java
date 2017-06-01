package com.xjm.xxd.dribbird.splash;

import android.util.Log;

import com.xjm.xxd.dribbird.account.TokenBean;
import com.xjm.xxd.dribbird.account.TokenManager;
import com.xjm.xxd.dribbird.api.retrofit.RetrofitManager;
import com.xjm.xxd.dribbird.utils.RxUtils;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by queda on 2016/12/3.
 */

public class SplashActivityPresenter implements ISplashActivityPresenter {

    private SplashActivityView mView;

    private CompositeDisposable mCompositeDisposable;

    private static final String TAG = SplashActivityPresenter.class.getSimpleName();


    @Override
    public void bindIView(SplashActivityView iView) {
        mView = iView;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void checkAccount() {
        mCompositeDisposable.add(Flowable.create(new FlowableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(FlowableEmitter<Boolean> flowableEmitter) throws Exception {
                boolean hasAvailableToken = TokenManager.hasAvailableToken();
                flowableEmitter.onNext(hasAvailableToken);
                flowableEmitter.onComplete();
            }
        }, BackpressureStrategy.DROP).delay(3, TimeUnit.SECONDS)
                .compose(RxUtils.<Boolean>applyNetworkScheduler())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {
                        if (aBoolean) {
                            // 存在有效的token，设置好网络请求的token拦截器，跳转主页
                            TokenBean tokenBean = TokenManager.getTokenBean();
                            if (tokenBean != null) {
                                RetrofitManager.getInstance().addTokenInterceptor(tokenBean.getAccessToken());
                            }
                            mView.jumpToMainPage();
                            Log.w(TAG, "checkAccount(), token exist -> main page");
                        } else {
                            // token not exist, jump to login activity
                            mView.jumpToLoginActivity();
                            Log.w(TAG, "checkAccount(), token not exist -> login activity");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        // exception, jump to login activity
                        Log.e(TAG, "checkAccount(), throwable : ", throwable);
                        mView.jumpToLoginActivity();
                    }
                }));

    }

    @Override
    public void onDestroy() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

}
