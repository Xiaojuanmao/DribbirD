package com.xjm.xxd.fastwidget.container;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.xjm.xxd.fastwidget.widget.BaseWidget;
import com.xjm.xxd.fastwidget.widget.WidgetConfig;

import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by queda on 2016/12/2.
 */

public class WidgetGroupManager implements IGroupManager {

    private IGroupContainer mContainer;

    private IGroupConfig mConfig;

    private Map<BaseWidget, View> mWidgetViewMap; // 管理widget和view对应的关系
    private WeakReference<Context> mContextReference;

    private static final String TAG = WidgetGroupManager.class.getSimpleName();

    public WidgetGroupManager(Context context) {
        mContextReference = new WeakReference<>(context);
        mWidgetViewMap = new LinkedHashMap<>();
        mConfig = new GsonWidgetGroupConfig(mContextReference);
    }

    private void initWithConfig() {
        List<WidgetConfig> configList = mConfig.getConfigs(false);
        if (!configList.isEmpty()) {
            for (WidgetConfig config : configList) {
                String widgetClassName = config.getWidgetClassName();
                if (!TextUtils.isEmpty(widgetClassName)) {
                    try {
                        Class clazz = Class.forName(widgetClassName);
                        Object widgetObj = clazz.newInstance();
                        if (widgetObj instanceof BaseWidget) {
                            Log.e(TAG, "initWithConfig(), find widgetObj : " + widgetClassName + " use reflect");
                            addWidget(((BaseWidget) widgetObj), false);
                        }
                    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void bindContainer(IGroupContainer groupContainer) {
        mContainer = groupContainer;
        initWithConfig();
    }

    @Override
    public void addWidget(BaseWidget widget, boolean isNeedAddToConfig) {
        if (widget == null) {
            Log.w(TAG, "addWidget(), widget is null");
            return;
        }
        if (mWidgetViewMap == null) {
            mWidgetViewMap = new LinkedHashMap<>();
            Log.w(TAG, "addWidget(), widget -> view map is null");
        }
        if (mWidgetViewMap.get(widget) != null) {
            Log.w(TAG, "addWidget(), widget already exist");
            return;
        }
        View view = widget.onCreate(mContextReference);
        if (view == null) {
            Log.w(TAG, "addWidget(), onCreate widget error");
            return;
        }
        mWidgetViewMap.put(widget, view);
        mContainer.addWidgetView(view);
        if (isNeedAddToConfig) {
            mConfig.addConfig(widget.getConfig());
        }
        widget.onResume();
    }

    @Override
    public void removeWidget(BaseWidget widget) {
        if (widget == null) {
            Log.w(TAG, "removeWidget(), widget is null");
            return;
        }
        if (mWidgetViewMap == null) {
            mWidgetViewMap = new LinkedHashMap<>();
            Log.w(TAG, "removeWidget(), widget -> view map is null");
        }
        if (mWidgetViewMap.get(widget) == null) {
            Log.w(TAG, "removeWidget(), widget not exist");
            return;
        }
        View viewNeedRemove = mWidgetViewMap.remove(widget);
        mContainer.removeWidgetView(viewNeedRemove);
        widget.onDestroy();
    }

    @Override
    public void saveWidgetConfig() {
        if (mConfig != null) {
            mConfig.save();
        }
    }

    @Override
    public void destroy() {
        if (mConfig != null) {
            mConfig.save();
        }
    }

}
