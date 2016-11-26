package com.xjm.xxd.dribbird.presenter.activity;


import com.xjm.xxd.dribbird.presenter.IPresenter;
import com.xjm.xxd.dribbird.view.MainActivityView;

/**
 * Created by queda on 2016/11/21.
 */

public interface IMainActivityPresenter extends IPresenter<MainActivityView> {

    void startLoadData();
}
