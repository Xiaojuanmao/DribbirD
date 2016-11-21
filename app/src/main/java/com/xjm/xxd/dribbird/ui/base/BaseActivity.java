package com.xjm.xxd.dribbird.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xjm.xxd.dribbird.DribApp;
import com.xjm.xxd.dribbird.di.component.ApplicationComponent;
import com.xjm.xxd.dribbird.presenter.activity.BaseActivityPresenter;

/**
 * Created by queda on 2016/11/21.
 */

public abstract class BaseActivity<BAP extends BaseActivityPresenter> extends AppCompatActivity {

    private BAP mBaseActivityPresenter;

    private final String TAG = getTag();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startInject();
        mBaseActivityPresenter = getPresenter();
        mBaseActivityPresenter.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mBaseActivityPresenter != null) {
            mBaseActivityPresenter.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mBaseActivityPresenter != null) {
            mBaseActivityPresenter.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mBaseActivityPresenter != null) {
            mBaseActivityPresenter.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBaseActivityPresenter != null) {
            mBaseActivityPresenter.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBaseActivityPresenter != null) {
            mBaseActivityPresenter.onDestroy();
        }
    }

    @NonNull
    ApplicationComponent getApplicationComponent() {
        return DribApp.getInstance().getApplicationComponent();
    }

    /**
     * 开始注入
     */
    protected void startInject() {

    }

    protected abstract @NonNull BAP getPresenter();

    protected String getTag() {
        return this.getClass().getSimpleName();
    }

}
