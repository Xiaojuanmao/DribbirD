package com.xjm.xxd.dribbird.utils;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * User : xiaoxiaoda
 * Date : 17-4-19
 * Email : wonderfulifeel@gmail.com
 */

public class RxUtils {

    public static <T> FlowableTransformer<T, T> applyNetworkScheduler() {
        return (FlowableTransformer<T, T>) sNetworkScheduler;
    }

    private static final FlowableTransformer<Observable, Observable> sNetworkScheduler
            = new FlowableTransformer<Observable, Observable>() {

        @Override
        public Publisher<Observable> apply(Flowable<Observable> flowable) {
            return flowable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }

    };

}
