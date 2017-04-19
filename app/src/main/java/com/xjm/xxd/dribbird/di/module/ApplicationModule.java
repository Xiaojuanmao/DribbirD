package com.xjm.xxd.dribbird.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.xjm.xxd.dribbird.DribApp;
import com.xjm.xxd.dribbird.api.retrofit.DribbleApi;
import com.xjm.xxd.dribbird.api.retrofit.RetrofitManager;
import com.xjm.xxd.dribbird.api.retrofit.UserApi;
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
    private final Gson mGson;

    public ApplicationModule(DribApp app) {
        mApp = app;
        mGson = new Gson();
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

    @Provides
    @Singleton
    Gson provideGson() {
        return mGson;
    }

    @Provides
    @Singleton
    DribbleApi provideDribbleApi(RetrofitManager manager) {
        return manager.create(DribbleApi.class);
    }

    @Provides
    @Singleton
    UserApi provideUserApi(RetrofitManager manager) {
        return manager.create(UserApi.class);
    }

    @Provides
    @Singleton
    RetrofitManager provideRetrofitManager() {
        return RetrofitManager.getInstance();
    }

}
