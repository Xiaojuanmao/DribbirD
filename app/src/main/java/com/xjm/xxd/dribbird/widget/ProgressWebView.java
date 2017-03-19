package com.xjm.xxd.dribbird.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.xjm.xxd.dribbird.R;

/**
 * Created by queda on 2016/12/2.
 */

public class ProgressWebView extends WebView {

    private Drawable mProgressDrawable;
    private int mProgressHeight;

    private ProgressBar mProgressBar;

    private static final int DEFAULT_HEIGHT = 8;

    public ProgressWebView(Context context) {
        this(context, null);
    }

    public ProgressWebView(Context context, AttributeSet attrs) {
        this(context, attrs, Resources.getSystem().getIdentifier("webViewStyle","attr","android"));
    }

    public ProgressWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressWebView, defStyleAttr, 0);
        mProgressDrawable = typedArray.getDrawable(R.styleable.ProgressWebView_pwb_progressBarDrawable);
        mProgressHeight = (int) typedArray.getDimension(R.styleable.ProgressWebView_pwb_progressBarHeight, DEFAULT_HEIGHT);
        typedArray.recycle();

        initViews(context);
    }

    private void initViews(Context context) {
        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mProgressHeight);
        mProgressBar.setLayoutParams(layoutParams);
        mProgressBar.setProgressDrawable(mProgressDrawable);
        addView(mProgressBar);
        setWebChromeClient(new ProgressWebChromeClient());
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) mProgressBar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        mProgressBar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    class ProgressWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                mProgressBar.setVisibility(GONE);
            } else {
                if (mProgressBar.getVisibility() == GONE)
                    mProgressBar.setVisibility(VISIBLE);
                mProgressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

    }

}
