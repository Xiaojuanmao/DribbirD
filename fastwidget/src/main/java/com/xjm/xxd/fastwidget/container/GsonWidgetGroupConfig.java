package com.xjm.xxd.fastwidget.container;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xjm.xxd.fastwidget.widget.WidgetConfig;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by queda on 2016/12/2.
 */

/**
 * 用gson来辅助存储配置信息
 */
public class GsonWidgetGroupConfig implements IGroupConfig {

    private Gson mGson;
    private List<WidgetConfig> mConfigs;
    private WeakReference<Context> mContextReference;

    private static final String ADDED_WIDGET_LIST = "added_widget_list";
    private static final String TAG = GsonWidgetGroupConfig.class.getSimpleName();

    public GsonWidgetGroupConfig(WeakReference<Context> contextWeakReference) {
        mGson = new Gson();
        mContextReference = contextWeakReference;
    }

    @Override
    public void addConfig(WidgetConfig config) {
        if (mConfigs == null) {
            mConfigs = new LinkedList<>();
        }
        Log.w(TAG, "addConfig, add widget : " + config.getWidgetClassName() + " to config");
        mConfigs.add(config);
    }

    @Override
    public boolean removeConfig(WidgetConfig config) {
        if (mConfigs != null && config != null) {
            return mConfigs.remove(config);
        }
        return false;
    }

    @NonNull
    @Override
    public List<WidgetConfig> getConfigs() {
        if (mConfigs == null) {
            mConfigs = new LinkedList<>();
            SharedPreferences preferences = getSharedPreferences();
            if (preferences != null) {
                String configStr = preferences.getString(ADDED_WIDGET_LIST, "");
                if (!TextUtils.isEmpty(configStr)) {
                    Type type = new TypeToken<LinkedList<WidgetConfig>>(){}.getType();
                    LinkedList<WidgetConfig> configFromFile = mGson.fromJson(configStr, type);
                    if (configFromFile != null && (!configFromFile.isEmpty())) {
                        mConfigs.addAll(configFromFile);
                    }
                }
            }
        }
        return mConfigs;
    }

    @Override
    public void save() {
        SharedPreferences preferences = getSharedPreferences();
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(ADDED_WIDGET_LIST, mGson.toJson(mConfigs));
            editor.apply();
        }
    }

    private
    @Nullable
    SharedPreferences getSharedPreferences() {
        if (mContextReference != null && mContextReference.get() != null) {
            return mContextReference.get().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        }
        return null;
    }

}
