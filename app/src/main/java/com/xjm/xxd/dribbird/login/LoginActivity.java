package com.xjm.xxd.dribbird.login;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.xjm.xxd.dribbird.R;
import com.xjm.xxd.dribbird.account.TokenManager;
import com.xjm.xxd.dribbird.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.web_view)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initViews();

        mWebView.loadUrl(TokenManager.getOAuth2Url());
    }

    private void initViews() {
        setSupportActionBar(mToolbar);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new LoginWebViewClient());

    }

    @Override
    protected void startInject() {
        super.startInject();
    }

}
