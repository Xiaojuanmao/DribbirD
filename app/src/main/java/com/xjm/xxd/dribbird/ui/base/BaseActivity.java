package com.xjm.xxd.dribbird.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xjm.xxd.dribbird.DribApp;
import com.xjm.xxd.dribbird.di.component.ActivityComponent;
import com.xjm.xxd.dribbird.di.component.ApplicationComponent;
import com.xjm.xxd.dribbird.di.component.DaggerActivityComponent;
import com.xjm.xxd.dribbird.di.module.ActivityModule;

/**
 * Created by queda on 2016/11/21.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private final String TAG = getTag();

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startInject();
    }

    protected
    @NonNull
    ApplicationComponent getApplicationComponent() {
        return DribApp.getInstance().getApplicationComponent();
    }

    protected
    @NonNull
    ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent
                    .builder()
                    .applicationComponent(getApplicationComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return mActivityComponent;
    }

    /**
     * 开始注入
     */
    protected void startInject() {
        mActivityComponent = DaggerActivityComponent
                .builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    protected String getTag() {
        return this.getClass().getSimpleName();
    }

}
