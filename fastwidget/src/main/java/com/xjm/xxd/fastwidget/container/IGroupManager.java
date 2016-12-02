package com.xjm.xxd.fastwidget.container;

import com.xjm.xxd.fastwidget.widget.BaseWidget;

/**
 * Created by queda on 2016/12/2.
 */

interface IGroupManager {

    void bindContainer(IGroupContainer groupContainer);

    void addWidget(BaseWidget widget, boolean isNeedAddToConfig);

    void removeWidget(BaseWidget widget);

    void destroy();
}
