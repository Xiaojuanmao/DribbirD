package com.xjm.xxd.dribbird.splash;

import com.xjm.xxd.dribbird.base.IPresenter;

/**
 * Created by queda on 2016/12/3.
 */

public interface ISplashActivityPresenter extends IPresenter<SplashActivityView> {

    // 检测是否存在已登陆的账户
    void checkAccount();

}
