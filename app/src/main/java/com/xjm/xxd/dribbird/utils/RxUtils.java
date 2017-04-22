package com.xjm.xxd.dribbird.utils;

import rx.Observable;
import rx.Observable.Transformer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * User : xiaoxiaoda
 * Date : 17-4-19
 * Email : wonderfulifeel@gmail.com
 */

public class RxUtils {

    public static <T> Transformer<T, T> applyNetworkScheduler() {
        return (Transformer<T, T>) sNetworkScheduler;
    }

    private static final Transformer<Observable, Observable> sNetworkScheduler
            = new Transformer<Observable, Observable>() {
        @Override
        public Observable<Observable> call(Observable<Observable> observableObservable) {
            return observableObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

}
