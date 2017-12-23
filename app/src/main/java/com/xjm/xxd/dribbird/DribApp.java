package com.xjm.xxd.dribbird;

import android.app.Application;
import android.support.annotation.NonNull;

/**
 * User : xiaoxiaoda
 * Date : 2016/11/21
 * Email : wonderfulifeel@gmail.com
 */

public class DribApp extends Application {

    private static volatile DribApp mInstance;

    public DribApp() {
        super();
        mInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public
    @NonNull
    static DribApp getInstance() {
        return mInstance;
    }

}
