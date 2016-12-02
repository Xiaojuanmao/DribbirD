package com.xjm.xxd.dribbird.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.xjm.xxd.dribbird.R;
import com.xjm.xxd.dribbird.presenter.activity.IMainActivityPresenter;
import com.xjm.xxd.dribbird.ui.base.BaseActivity;
import com.xjm.xxd.dribbird.view.MainActivityView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements
        MainActivityView {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;

    @Inject
    IMainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);

        initViews();

        mPresenter.bindIView(this);

    }

    private void initViews() {
        setSupportActionBar(mToolbar);

    }

}
