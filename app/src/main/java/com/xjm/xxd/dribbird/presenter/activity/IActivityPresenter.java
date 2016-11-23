package com.xjm.xxd.dribbird.presenter.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xjm.xxd.dribbird.presenter.IPresenter;
import com.xjm.xxd.dribbird.view.IView;

/**
 * Created by queda on 2016/11/21.
 */

interface IActivityPresenter<IV extends IView> extends IPresenter<IV> {

    void onCreate(@Nullable Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
