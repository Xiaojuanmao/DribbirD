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

import rx.Observable;
import rx.Subscription;
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

    private Subscription mAuthSubscription;

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
            if (TokenManager.isMatchRedirectUrl(url)) {
                // get the return code
                Uri uri = Uri.parse(url);
                String returnCode = uri.getQueryParameter(ApiConstants.CODE);
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
            mAuthSubscription = Observable.just(returnCode)
                    .map(new Func1<String, TokenBean>() {
                        @Override
                        public TokenBean call(String s) {
                            return TokenManager.requestForToken(s);
                        }
                    }).compose(RxUtils.<TokenBean>applyNetworkScheduler())
                    .subscribe(new Action1<TokenBean>() {
                        @Override
                        public void call(TokenBean tokenBean) {
                            if (mCallback != null && mCallback.get() != null) {
                                mCallback.get().hideLoading();
                            }
                            if (tokenBean != null) {
                                // 给Retrofit设置token
                                RetrofitManager.getInstance().addTokenInterceptor(tokenBean.getAccessToken());
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
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
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
        if (mAuthSubscription != null && mAuthSubscription.isUnsubscribed()) {
            mAuthSubscription.unsubscribe();
        }
    }

}
