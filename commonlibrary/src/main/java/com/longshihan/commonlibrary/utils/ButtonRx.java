/*
package com.longshihan.commonlibrary.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.subjects.PublishSubject;

*/
/**
 * Created by longshihan on 2017/5/1.
 *//*


public class ButtonRx {
    private PublishSubject<Boolean> eventSubject = PublishSubject.create();
    public static final int TIME_BETWEEN_EVENTS_MILLIS = 500;
    public static final int NUMBER_OF_EVENTS = 2;
    public DoubleClick() {
        eventSubject.buffer(eventSubject.debounce(TIME_BETWEEN_EVENTS_MILLIS, TimeUnit.MILLISECONDS))
                .filter((List<Boolean> events) -> {
                    return events.size() == NUMBER_OF_EVENTS;
                })
                .subscribe((List<Boolean> events) -> {
                    doSomething();
                });

        button.setOnClickListener((button) -> {
            return eventSubject.onNext(true);
        });
    }

    private void doSomething() {

    }
}
*/
