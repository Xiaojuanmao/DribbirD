package com.xjm.xxd.dribbird.mainpage;

import android.util.Log;

import com.google.gson.Gson;
import com.xjm.xxd.dribbird.DribApp;
import com.xjm.xxd.dribbird.api.retrofit.UserApi;
import com.xjm.xxd.dribbird.di.component.DaggerActivityComponent;
import com.xjm.xxd.dribbird.di.component.DaggerApplicationComponent;
import com.xjm.xxd.dribbird.model.UserBean;

import javax.inject.Inject;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by queda on 2016/11/21.
 */

public class MainActivityPresenter implements IMainActivityPresenter {

    private MainActivityView mView;

    @Inject
    UserApi mUserApi;

    public MainActivityPresenter() {
        DribApp.getInstance().getApplicationComponent().inject(this);
    }

    @Override
    public void bindIView(MainActivityView iView) {
        mView = iView;
    }

    @Override
    public void start() {
        mUserApi.getAuthenticatedUser()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<UserBean>() {
            @Override
            public void call(UserBean userBean) {
                Log.e("here", new Gson().toJson(userBean));
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e("here", throwable.toString());
            }
        });
    }

    @Override
    public void onDestroy() {

    }

}
