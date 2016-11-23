package com.xjm.xxd.dribbird.di.module;

import com.xjm.xxd.dribbird.di.PerActivity;
import com.xjm.xxd.dribbird.ui.base.BaseActivity;

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

}
