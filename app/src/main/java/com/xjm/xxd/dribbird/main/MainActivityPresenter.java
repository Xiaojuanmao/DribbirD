package com.xjm.xxd.dribbird.main;

import android.util.Log;

import com.google.gson.Gson;
import com.xjm.xxd.dribbird.DribApp;
import com.xjm.xxd.dribbird.api.retrofit.UserApi;
import com.xjm.xxd.dribbird.model.UserBean;
import com.xjm.xxd.dribbird.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by queda on 2016/11/21.
 */

public class MainActivityPresenter implements IMainActivityPresenter {

    private MainActivityView mView;

    private static final String TAG = MainActivityPresenter.class.getSimpleName();

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
                .compose(RxUtils.<UserBean>applyNetworkScheduler())
                .subscribe(new Consumer<UserBean>() {
                    @Override
                    public void accept(UserBean userBean) throws Exception {
                        Log.e(TAG, new Gson().toJson(userBean));

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, throwable.toString());

                    }
                });
    }

    @Override
    public void onDestroy() {

    }

}
