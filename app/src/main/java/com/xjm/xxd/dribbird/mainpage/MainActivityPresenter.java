package com.xjm.xxd.dribbird.mainpage;

import com.xjm.xxd.dribbird.DribApp;

/**
 * Created by queda on 2016/11/21.
 */

public class MainActivityPresenter implements IMainActivityPresenter {

    private MainActivityView mView;
    private IMainActivityModel mModel;

    @Override
    public void bindIView(MainActivityView iView) {
        mView = iView;
        mModel = new MainActivityModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }

}
