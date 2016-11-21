package com.xjm.xxd.dribbird.presenter.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by queda on 2016/11/21.
 */

public class BaseActivityPresenter<IV> implements IActivityPresenter<IV> {

    protected IV mView;


    @Override
    public void bindIView(IV iView) {
        mView = iView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

}
