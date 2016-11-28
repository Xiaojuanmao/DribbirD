package com.xjm.xxd.dribbird.presenter.activity;

import com.xjm.xxd.dribbird.view.MainActivityView;

import javax.inject.Inject;

/**
 * Created by queda on 2016/11/21.
 */

public class MainActivityPresenter implements IMainActivityPresenter {

    private MainActivityView mView;

    @Inject
    MainActivityPresenter() {
        super();
    }

    @Override
    public void bindIView(MainActivityView iView) {
        mView = iView;
    }

}
