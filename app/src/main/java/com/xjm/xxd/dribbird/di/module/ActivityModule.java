package com.xjm.xxd.dribbird.di.module;

import com.xjm.xxd.dribbird.di.PerActivity;
import com.xjm.xxd.dribbird.login.ILoginActivityPresenter;
import com.xjm.xxd.dribbird.login.LoginActivityPresenter;
import com.xjm.xxd.dribbird.mainpage.IMainActivityPresenter;
import com.xjm.xxd.dribbird.mainpage.MainActivityPresenter;
import com.xjm.xxd.dribbird.splash.ISplashActivityPresenter;
import com.xjm.xxd.dribbird.splash.SplashActivityPresenter;
import com.xjm.xxd.dribbird.base.BaseActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by queda on 2016/11/21.
 */

@Module
public class ActivityModule {

    private final BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    BaseActivity activity() {
        return this.activity;
    }

    @Provides
    ISplashActivityPresenter provideSplashActivityPresenter() {
        return new SplashActivityPresenter();
    }

    @Provides
    IMainActivityPresenter provideMainActivityPresenter() {
        return new MainActivityPresenter();
    }

    @Provides
    ILoginActivityPresenter provideLoginActivityPresenter() {
        return new LoginActivityPresenter();
    }

}
