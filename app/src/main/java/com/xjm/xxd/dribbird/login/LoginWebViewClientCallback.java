package com.xjm.xxd.dribbird.login;

import com.xjm.xxd.dribbird.account.TokenBean;

/**
 * Created by queda on 2016/12/2.
 */

public interface LoginWebViewClientCallback {

    void loginSuccess(TokenBean tokenBean);

    void loginFailed();
}
