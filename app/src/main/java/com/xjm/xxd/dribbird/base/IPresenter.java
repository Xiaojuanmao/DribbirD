package com.xjm.xxd.dribbird.base;

/**
 * Created by queda on 2016/11/21.
 */

public interface IPresenter<IV extends IView> {

    void bindIView(IV iView);

    void onDestroy();
}