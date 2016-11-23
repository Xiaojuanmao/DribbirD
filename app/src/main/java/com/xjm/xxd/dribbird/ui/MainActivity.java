package com.xjm.xxd.dribbird.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import com.xjm.xxd.dribbird.R;
import com.xjm.xxd.dribbird.di.component.ActivityComponent;
import com.xjm.xxd.dribbird.di.component.DaggerActivityComponent;
import com.xjm.xxd.dribbird.di.module.ActivityModule;
import com.xjm.xxd.dribbird.presenter.activity.MainActivityPresenter;
import com.xjm.xxd.dribbird.ui.base.BaseActivity;
import com.xjm.xxd.dribbird.view.MainActivityView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainActivityPresenter> implements
        MainActivityView {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;

    @Inject
    MainActivityPresenter mPresenter;

    private ActivityComponent mComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        setSupportActionBar(mToolbar);

    }

    @Override
    protected void startInject() {
        super.startInject();
        mComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
        mComponent.inject(this);
    }

    @NonNull
    @Override
    protected MainActivityPresenter getPresenter() {
        return mPresenter;
    }

}
