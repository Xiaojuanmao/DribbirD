package com.xjm.xxd.dribbird.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.xjm.xxd.dribbird.R;
import com.xjm.xxd.dribbird.presenter.activity.MainActivityPresenter;
import com.xjm.xxd.dribbird.ui.base.BaseActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainActivityPresenter> {

    @Inject
    MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void startInject() {
        super.startInject();

    }

    @NonNull
    @Override
    protected MainActivityPresenter getPresenter() {
        return null;
    }

}
