package com.xjm.xxd.fastwidget.widget;

/**
 * Created by queda on 2016/12/2.
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 每一个显示在container上的widget都有一个config
 * config里面保存了widget的相关信息，能够通过config来确定widget
 */

public class WidgetConfig implements Serializable {

    @SerializedName("class_name")
    private String mWidgetClassName;

    public String getWidgetClassName() {
        return mWidgetClassName;
    }

    public void setWidgetClassName(String widgetClassName) {
        mWidgetClassName = widgetClassName;
    }

}
