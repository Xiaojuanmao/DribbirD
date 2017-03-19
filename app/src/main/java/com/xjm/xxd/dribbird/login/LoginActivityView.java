package com.xjm.xxd.dribbird.login;

import android.support.annotation.StringRes;

import com.xjm.xxd.dribbird.base.IView;

/**
 * Created by queda on 2016/12/2.
 */

public interface LoginActivityView extends IView {

    void loadUrl(String url);

    void showLoading(String msg);
    void showLoading(@StringRes int strId);

    void hideLoading();

    void loginSuccess();

    void loginFailed();
}
