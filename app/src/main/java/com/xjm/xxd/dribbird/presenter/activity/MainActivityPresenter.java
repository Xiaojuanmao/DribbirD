package com.xjm.xxd.dribbird.presenter.activity;

import com.xjm.xxd.dribbird.view.MainActivityView;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by queda on 2016/11/21.
 */

public class MainActivityPresenter implements IMainActivityPresenter {

    private MainActivityView mView;

    @Inject
    public MainActivityPresenter() {
        super();
    }

    @Override
    public void bindIView(MainActivityView iView) {
        mView = iView;
    }

    @Override
    public void startLoadData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

}
