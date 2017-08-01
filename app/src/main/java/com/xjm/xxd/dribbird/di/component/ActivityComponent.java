package com.xjm.xxd.dribbird.di.component;

import com.xjm.xxd.dribbird.di.PerActivity;
import com.xjm.xxd.dribbird.di.module.ActivityModule;
import com.xjm.xxd.dribbird.login.LoginActivity;
import com.xjm.xxd.dribbird.splash.SplashActivity;
import com.xjm.xxd.dribbird.main.MainActivity;

import dagger.Component;

/**
 * Created by queda on 2016/11/21.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);

    void inject(MainActivity activity);

    void inject(LoginActivity activity);

}
