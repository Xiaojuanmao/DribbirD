package com.xjm.xxd.dribbird.di.module;

import android.content.Context;

import com.xjm.xxd.dribbird.DribApp;
import com.xjm.xxd.dribbird.bus.RxBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by queda on 2016/11/21.
 */

@Module
public class ApplicationModule {

    private final DribApp mApp;

    public ApplicationModule(DribApp app) {
        mApp = app;
    }

    @Provides
    @Singleton
    DribApp provideDribApp() {
        return mApp;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApp;
    }

    @Provides
    @Singleton
    RxBus provideRxBus() {
        return RxBus.getInstance();
    }

}
