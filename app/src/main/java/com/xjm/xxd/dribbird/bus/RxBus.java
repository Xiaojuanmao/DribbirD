package com.xjm.xxd.dribbird.bus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;


/**
 * Created by queda on 2016/11/21.
 */

public class RxBus{

    private static volatile RxBus mInstance;

    private final Subject<IBusEvent, IBusEvent> mActual;

    private RxBus() {
        mActual = new SerializedSubject<>(PublishSubject.<IBusEvent>create());
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

    public Observable<IBusEvent> getObservable() {
        return mActual.asObservable();
    }
}
