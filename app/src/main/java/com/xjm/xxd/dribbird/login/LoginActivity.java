package com.xjm.xxd.dribbird.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.xjm.xxd.dribbird.R;
import com.xjm.xxd.dribbird.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements
        LoginActivityView {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.web_view)
    WebView mWebView;

    @Inject
    ILoginActivityPresenter mPresenter;

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

    private void initViews() {
        setSupportActionBar(mToolbar);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new LoginWebViewClient(mPresenter));
    }

    @Override
    public void loadUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        mWebView.loadUrl(url);
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
