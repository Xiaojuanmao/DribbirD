package com.xjm.xxd.dribbird.splash;

import android.util.Log;

import com.xjm.xxd.dribbird.account.TokenManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by queda on 2016/12/3.
 */

public class SplashActivityPresenter implements ISplashActivityPresenter {

    private SplashActivityView mView;

    private List<Subscription> mSubscriptions;

    private static final String TAG = SplashActivityPresenter.class.getSimpleName();


    @Override
    public void bindIView(SplashActivityView iView) {
        mView = iView;
        mSubscriptions = new ArrayList<>();
    }

    @Override
    public void checkAccount() {
        mSubscriptions.add(Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                boolean hasAvailableToken = TokenManager.hasAvaliableToken();
                subscriber.onNext(hasAvailableToken);
                subscriber.onCompleted();
            }
        }).delay(3, TimeUnit.SECONDS)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            // token exist, jump to main page
                            mView.jumpToMainPage();
                            Log.w(TAG, "checkAccount(), token exist -> main page");
                        } else {
                            // token not exist, jump to login activity
                            mView.jumpToLoginActivity();
                            Log.w(TAG, "checkAccount(), token not exist -> login activity");
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        // exception, jump to login activity
                        Log.e(TAG, "checkAccount(), throwable : ", throwable);
                        mView.jumpToLoginActivity();
                    }
                }));

    }

    @Override
    public void onDestroy() {
        if (mSubscriptions != null) {
            for (Subscription subscription : mSubscriptions) {
                if (subscription != null && subscription.isUnsubscribed()) {
                    subscription.unsubscribe();
                }
            }
        }
    }

}
