package com.xjm.xxd.dribbird.login;

import android.support.annotation.StringRes;

import com.xjm.xxd.dribbird.account.TokenBean;

/**
 * Created by queda on 2016/12/2.
 */

public interface LoginWebViewClientCallback {

    void showLoading(String msg);
    void showLoading(@StringRes int strId);

    void hideLoading();

    void loginSuccess(TokenBean tokenBean);

    void loginFailed();
}
