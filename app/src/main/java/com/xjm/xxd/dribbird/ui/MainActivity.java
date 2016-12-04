package com.xjm.xxd.dribbird.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.xjm.xxd.dribbird.R;
import com.xjm.xxd.dribbird.account.TokenBean;
import com.xjm.xxd.dribbird.account.TokenManager;
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
        mPresenter.bindIView(this);

        initViews();

        TokenBean bean = TokenManager.getTokenBean();
        if (bean != null) {
            Log.e("here", "token ； " + bean.getAccessToken());
        }
    }

    private void initViews() {

        setSupportActionBar(mToolbar);

    }

    public static void open(BaseActivity baseActivity) {
        if (baseActivity == null) {
            return;
        }
        Intent intent = new Intent(baseActivity, MainActivity.class);
        baseActivity.startActivity(intent);
    }

}
