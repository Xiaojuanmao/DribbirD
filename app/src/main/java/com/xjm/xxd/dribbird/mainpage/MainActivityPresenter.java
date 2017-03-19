package com.xjm.xxd.dribbird.mainpage;

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
