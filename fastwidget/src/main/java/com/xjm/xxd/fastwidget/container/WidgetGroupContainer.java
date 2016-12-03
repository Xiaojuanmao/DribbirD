package com.xjm.xxd.fastwidget.container;

/**
 * Created by queda on 2016/12/2.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.xjm.xxd.fastwidget.widget.BaseWidget;

/**
 * 作为所有组件的父容器
 * 管理所有组件的生命周期
 */

public class WidgetGroupContainer extends ScrollView
        implements IGroupContainer {

    private IGroupManager mManager;

    private LinearLayout mRootContainer;

    public WidgetGroupContainer(Context context) {
        this(context, null);
    }

    public WidgetGroupContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WidgetGroupContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        if (mRootContainer == null) {
            mRootContainer = new LinearLayout(context);
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            mRootContainer.setLayoutParams(layoutParams);
            mRootContainer.setOrientation(LinearLayout.VERTICAL);
        }
        addView(mRootContainer);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mManager = new WidgetGroupManager(getContext());
        mManager.bindContainer(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public void addWidgetView(View view) {
        if (view == null) {
            return;
        }
        mRootContainer.addView(view);
    }

    @Override
    public void removeWidgetView(View view) {
        if (view == null) {
            return;
        }
        mRootContainer.removeView(view);
    }

    public void addWidget(BaseWidget baseWidget) {
        mManager.addWidget(baseWidget, true);
    }

    public void saveWidgetConfigs() {
        if (mManager != null) {
            mManager.saveWidgetConfig();
        }
    }

    public void removeWidget(BaseWidget baseWidget) {
        mManager.removeWidget(baseWidget);
    }

}
