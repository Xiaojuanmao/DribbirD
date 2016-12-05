package com.xjm.xxd.fastwidget.edit;

/**
 * Created by queda on 2016/12/5.
 */

public interface IEditManager extends EditWidgetItemCallback {

    void bindView(IEditView view);

    void loadWidgetConfig();

}
