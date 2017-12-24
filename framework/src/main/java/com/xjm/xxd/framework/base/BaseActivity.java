package com.xjm.xxd.framework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by queda on 2016/11/21.
 */

public abstract class BaseActivity extends AppCompatActivity {

    final String TAG = getTag();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected String getTag() {
        return this.getClass().getSimpleName();
    }

}
