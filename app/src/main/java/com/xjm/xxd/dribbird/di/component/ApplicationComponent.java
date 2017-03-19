package com.xjm.xxd.dribbird.di.component;

import android.content.Context;

import com.google.gson.Gson;
import com.xjm.xxd.dribbird.DribApp;
import com.xjm.xxd.dribbird.api.retrofit.DribbleApi;
import com.xjm.xxd.dribbird.bus.RxBus;
import com.xjm.xxd.dribbird.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by queda on 2016/11/21.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    DribApp app();

    Context context();

    Gson gson();

    RxBus rxBus();

    DribbleApi dribbleApi();
}
