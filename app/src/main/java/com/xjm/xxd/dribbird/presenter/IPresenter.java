package com.xjm.xxd.dribbird.presenter;

import com.xjm.xxd.dribbird.view.IView;

/**
 * Created by queda on 2016/11/21.
 */

public interface IPresenter<IV extends IView> {

    void bindIView(IV iView);

    void onDestroy();
}
