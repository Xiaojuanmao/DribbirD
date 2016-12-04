package com.xjm.xxd.dribbird.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.webkit.WebSettings;

import com.xjm.xxd.dribbird.R;
import com.xjm.xxd.dribbird.ui.base.BaseActivity;
import com.xjm.xxd.dribbird.ui.dialog.BaseDialog;
import com.xjm.xxd.dribbird.ui.widget.ProgressWebView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements
        LoginActivityView {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.web_view)
    ProgressWebView mWebView;

    @Inject
    ILoginActivityPresenter mPresenter;

    private BaseDialog mBaseDialog;
    private LoginWebViewClient mWebViewClient;

    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);

        initViews();

        mPresenter.bindIView(this);
        mPresenter.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }

        if (mWebViewClient != null) {
            mWebViewClient.onDestroy();
        }
    }

    private void initViews() {
        setSupportActionBar(mToolbar);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebViewClient = new LoginWebViewClient(mPresenter);
        mWebView.setWebViewClient(mWebViewClient);
    }

    @Override
    public void loadUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        mWebView.loadUrl(url);
    }

    @Override
    public void showLoading(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (mBaseDialog == null) {
            BaseDialog.Builder builder = new BaseDialog.Builder();
            mBaseDialog = builder.build();
        }
        mBaseDialog.title(msg);
        mBaseDialog.show(getSupportFragmentManager(), TAG);
    }

    @Override
    public void showLoading(@StringRes int strId) {
        if (strId <= 0) {
            return;
        }
        showLoading(getString(strId));
    }

    @Override
    public void hideLoading() {
        if (mBaseDialog != null && mBaseDialog.isVisible()) {
            mBaseDialog.dismiss();
        }
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginFailed() {

    }

    public static void open(BaseActivity activity) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

}
