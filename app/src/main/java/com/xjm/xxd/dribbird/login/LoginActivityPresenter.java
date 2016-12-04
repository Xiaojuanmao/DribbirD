package com.xjm.xxd.dribbird.login;

import android.support.annotation.StringRes;
import android.util.Log;

import com.xjm.xxd.dribbird.account.TokenBean;
import com.xjm.xxd.dribbird.account.TokenManager;

/**
 * Created by queda on 2016/12/2.
 */

public class LoginActivityPresenter implements ILoginActivityPresenter {

    private LoginActivityView mView;

    @Override
    public void bindIView(LoginActivityView iView) {
        mView = iView;
    }

    @Override
    public void init() {
        mView.loadUrl(TokenManager.getOAuth2Url());
    }

    @Override
    public void showLoading(String msg) {
        mView.showLoading(msg);
    }

    @Override
    public void showLoading(@StringRes int strId) {
        mView.showLoading(strId);
    }

    @Override
    public void hideLoading() {
        mView.hideLoading();
    }

    @Override
    public void loginSuccess(TokenBean tokenBean) {
        mView.loginSuccess();
    }

    @Override
    public void loginFailed() {
        mView.loginFailed();
    }

    @Override
    public void onDestroy() {

    }

}
