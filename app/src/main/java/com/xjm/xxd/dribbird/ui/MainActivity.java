package com.xjm.xxd.dribbird.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xjm.xxd.dribbird.R;
import com.xjm.xxd.dribbird.account.TokenManager;
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

        mWebView.loadUrl(TokenManager.getDribbleOAuth2Url());
    }

    private void initViews() {
        setSupportActionBar(mToolbar);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (!TextUtils.isEmpty(url)) {
                    if (url.equals(TokenManager.getDribbleOAuth2Url())) {
                        // 当前加载的url为请求认证授权的url，直接加载
                        view.loadUrl(url);
                        return true;
                    }

                    if (url.equals(ApiConstants.DEFAULT_REDIRECT_URI)) {
                        // 当前url为重定向的url，用户已授权
                        Log.e("here", "url : " + url);
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
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
