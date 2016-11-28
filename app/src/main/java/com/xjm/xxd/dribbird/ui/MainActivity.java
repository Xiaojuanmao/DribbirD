package com.xjm.xxd.dribbird.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.HttpAuthHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xjm.xxd.dribbird.R;
import com.xjm.xxd.dribbird.api.ApiConstants;
import com.xjm.xxd.dribbird.di.component.ActivityComponent;
import com.xjm.xxd.dribbird.di.component.DaggerActivityComponent;
import com.xjm.xxd.dribbird.di.module.ActivityModule;
import com.xjm.xxd.dribbird.presenter.activity.MainActivityPresenter;
import com.xjm.xxd.dribbird.ui.base.BaseActivity;
import com.xjm.xxd.dribbird.view.MainActivityView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements
        MainActivityView {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.web_view)
    WebView mWebView;

    @Inject
    MainActivityPresenter mPresenter;

    private ActivityComponent mComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();

        mPresenter.bindIView(this);

        StringBuilder builder = new StringBuilder(ApiConstants.OAUTH_BASE_URL);
        builder.append(ApiConstants.AUTHORIZE)
                .append(ApiConstants.QUESTION_MARK)
                .append(ApiConstants.CLIENT_ID)
                .append(ApiConstants.EQUAL)
                .append(ApiConstants.DRIBBLE_CLIENT_ID);
        mWebView.loadUrl(builder.toString());

    }

    private void initViews() {
        setSupportActionBar(mToolbar);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
                Log.e("here", "onReceivedLoginRequest, realm : " + realm);
                super.onReceivedLoginRequest(view, realm, account, args);
            }

            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                Log.e("here", "onReceivedHttpAuthRequest, host : " + host);
                super.onReceivedHttpAuthRequest(view, handler, host, realm);
            }

        });
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

}
