package com.xjm.xxd.dribbird;

import android.app.Application;
import android.support.annotation.NonNull;

import com.xjm.xxd.dribbird.di.component.ApplicationComponent;
import com.xjm.xxd.dribbird.di.component.DaggerApplicationComponent;
import com.xjm.xxd.dribbird.di.module.ApplicationModule;

/**
 * Created by queda on 2016/11/21.
 */

public class DribApp extends Application {

    private final ApplicationComponent mApplicationComponent;

    private static volatile DribApp mInstance;

    public DribApp() {
        super();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public
    @NonNull
    ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public
    @NonNull
    static DribApp getInstance() {
        return mInstance;
    }

}
