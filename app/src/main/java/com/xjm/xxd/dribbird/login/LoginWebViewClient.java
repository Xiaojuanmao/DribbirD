package com.xjm.xxd.dribbird.login;

import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xjm.xxd.dribbird.account.TokenBean;
import com.xjm.xxd.dribbird.account.TokenManager;
import com.xjm.xxd.dribbird.api.ApiConstants;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author : xiaoxiaoda
 *         date: 16-12-2
 *         email: daque@hustunique.com
 */
public class LoginWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (!TextUtils.isEmpty(url)) {
            // url is match with oauth request url
            if (TokenManager.isMatchRedirectUrl(url)) {
                // get the return code
                Uri uri = Uri.parse(url);
                String returnCode = uri.getQueryParameter(ApiConstants.CODE);
                if (!TextUtils.isEmpty(returnCode)) {
                    // request for access token with return code
                    Observable.just(returnCode)
                            .map(new Func1<String, TokenBean>() {
                                @Override
                                public TokenBean call(String s) {
                                    return TokenManager.requestForToken(s);
                                }
                            }).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<TokenBean>() {
                                @Override
                                public void call(TokenBean tokenBean) {
                                    if (tokenBean != null) {
                                        // authentic success

                                    } else {
                                        // auth failed

                                    }
                                }
                            }, new Action1<Throwable>() {
                                @Override
                                public void call(Throwable throwable) {

                                }
                            });
                }
                return true;
            }
        }
        view.loadUrl(url);
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

}
