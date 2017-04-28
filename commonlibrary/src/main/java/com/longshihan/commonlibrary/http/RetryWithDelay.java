package com.longshihan.commonlibrary.http;

import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author longshihan
 * @time 2017/4/26 11:54
 * @des 错误重发机制
 */

public class RetryWithDelay implements
        Func1<Observable<? extends Throwable>, Observable<?>> {
    private final int maxRetries;
    private final int retryDelayMillis;
    private int retryCount;

    public RetryWithDelay(int maxRetries, int retryDelayMillis) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
    }

    @Override
    public Observable<?> call(Observable<? extends Throwable> attempts) {
        return attempts
                .flatMap(new Func1<Throwable, Observable<?>>() {
                    @Override
                    public Observable<?> call(Throwable throwable) {
                        if (++retryCount <= maxRetries) {
                            // When this Observable calls onNext, the original Observable will be retried (i.e. re-subscribed).
                            Logger.d("get error, it will try after " + retryDelayMillis + " millisecond, retry count " + retryCount);
                            return Observable.timer(
                                    retryDelayMillis,
                                    TimeUnit.MILLISECONDS
                                                   );
                        }
                        // Max retries hit. Just pass the error along.
                        return Observable.error(throwable);
                    }
                });
    }
}
