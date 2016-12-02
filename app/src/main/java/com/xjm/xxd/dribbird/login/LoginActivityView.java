package com.xjm.xxd.dribbird.login;

import com.xjm.xxd.dribbird.view.IView;

/**
 * Created by queda on 2016/12/2.
 */

public interface LoginActivityView extends IView {

    void loadUrl(String url);

    void loginSuccess();

    void loginFailed();
}
