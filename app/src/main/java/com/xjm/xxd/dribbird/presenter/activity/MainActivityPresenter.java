package com.xjm.xxd.dribbird.presenter.activity;

import com.xjm.xxd.dribbird.view.MainActivityView;

/**
 * Created by queda on 2016/11/21.
 */

public class MainActivityPresenter implements IMainActivityPresenter {

    private MainActivityView mView;

    @Override
    public void bindIView(MainActivityView iView) {
        mView = iView;
    }

    @Override
    public void onDestroy() {

    }

}
