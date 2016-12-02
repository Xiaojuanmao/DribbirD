package com.xjm.xxd.fastwidget.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by queda on 2016/12/2.
 */

public abstract class BaseWidget implements IWidget {

    protected View mRootView;

    protected WeakReference<Context> mContextReference;

    protected WidgetConfig mConfig;

    @Override
    public View onCreate(WeakReference<Context> contextWeakReference) {
        mContextReference = contextWeakReference;
        if (getLayoutInflater() == null) {
            return null;
        }
        LayoutInflater layoutInflater = getLayoutInflater();
        mRootView = createView(layoutInflater);
        return mRootView;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public WidgetConfig getConfig() {
        if (mConfig == null) {
            mConfig = new WidgetConfig();
            mConfig.setWidgetClassName(this.getClass().getCanonicalName());
        }
        return mConfig;
    }

    protected
    @Nullable
    LayoutInflater getLayoutInflater() {
        if (getContext() == null) {
            return null;
        }
        return LayoutInflater.from(getContext());
    }

    protected
    @Nullable
    Context getContext() {
        if (mContextReference != null && mContextReference.get() != null) {
            return mContextReference.get();
        }
        return null;
    }

    protected abstract View createView(LayoutInflater layoutInflater);

}
