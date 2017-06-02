package com.xjm.xxd.dribbird.bus;


import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * Created by queda on 2016/11/21.
 */

public class RxBus{

    private static volatile RxBus mInstance;

    private final FlowableProcessor<IBusEvent> mActual;

    private RxBus() {
        mActual = PublishProcessor.<IBusEvent>create().toSerialized();
    }

    public static RxBus getInstance() {
        if (mInstance == null) {
            synchronized(RxBus.class) {
                if (mInstance == null) {
                    mInstance = new RxBus();
                }
            }
        }
        return mInstance;
    }

    public void postEvent(IBusEvent event) {
        mActual.onNext(event);
    }

    public <T> Flowable<T> register(Class<T> clazz) {
        return mActual.ofType(clazz);
    }

}
