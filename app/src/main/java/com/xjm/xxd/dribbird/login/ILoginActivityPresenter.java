package com.xjm.xxd.dribbird.login;

import com.xjm.xxd.framework.base.IPresenter;

/**
 * Created by queda on 2016/12/2.
 */

public interface ILoginActivityPresenter extends
        IPresenter<LoginActivityView>,
        LoginWebViewClientCallback {

    // initialize data for views
    void init();

}
