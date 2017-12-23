package com.xjm.xxd.dribbird.login;

import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xjm.xxd.dribbird.R;
import com.xjm.xxd.dribbird.account.TokenBean;
import com.xjm.xxd.dribbird.account.TokenManager;
import com.xjm.xxd.dribbird.api.ApiConstants;
import com.xjm.xxd.dribbird.api.retrofit.RetrofitManager;
import com.xjm.xxd.dribbird.utils.RxUtils;


import java.lang.ref.WeakReference;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author : xiaoxiaoda
 *         date: 16-12-2
 *         email: daque@hustunique.com
 */
public class LoginWebViewClient extends WebViewClient {

    private Disposable mAuthDisposable;

    private WeakReference<LoginWebViewClientCallback> mCallback;

    private static final String TAG = LoginWebViewClient.class.getSimpleName();

    public LoginWebViewClient(LoginWebViewClientCallback callback) {
        mCallback = new WeakReference<>(callback);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (TextUtils.isEmpty(url)) {
            // TODO : url is null
        } else {
            // url is match with oauth request url
            if (TokenManager.INSTANCE.isMatchRedirectUrl(url)) {
                // get the return code
                Uri uri = Uri.parse(url);
                String returnCode = uri.getQueryParameter(ApiConstants.INSTANCE.getCODE());
                processReturnCode(returnCode);
            } else {
                view.loadUrl(url);
            }
        }
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

    private void processReturnCode(String returnCode) {
        if (!TextUtils.isEmpty(returnCode)) {
            // request for access token with return code
            if (mCallback != null && mCallback.get() != null) {
                mCallback.get().showLoading(R.string.being_authorized);
            }
            mAuthDisposable = Observable.just(returnCode)
                    .map(new Function<String, TokenBean>() {
                        @Override
                        public TokenBean apply(String s) {
                            return TokenManager.INSTANCE.requestForToken(s);
                        }
                    }).compose(RxUtils.<TokenBean>applyNetworkScheduler())
                    .subscribe(new Consumer<TokenBean>() {
                        @Override
                        public void accept(TokenBean tokenBean) throws Exception {
                            if (mCallback != null && mCallback.get() != null) {
                                mCallback.get().hideLoading();
                            }
                            if (tokenBean != null) {
                                // 给Retrofit设置token
                                RetrofitManager.getInstance().addTokenInterceptor(tokenBean.accessToken);
                                // authentic success
                                if (mCallback != null && mCallback.get() != null) {
                                    mCallback.get().loginSuccess(tokenBean);
                                }
                            } else {
                                // auth failed
                                if (mCallback != null && mCallback.get() != null) {
                                    mCallback.get().loginFailed();
                                }
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) {
                            Log.w(TAG, "LoginWebViewClient request for access token error ", throwable);
                            if (mCallback != null && mCallback.get() != null) {
                                mCallback.get().hideLoading();
                                mCallback.get().loginFailed();
                            }
                        }
                    });
        }
    }

    void onDestroy() {
        if (mAuthDisposable != null && mAuthDisposable.isDisposed()) {
            mAuthDisposable.dispose();
        }
    }

}
