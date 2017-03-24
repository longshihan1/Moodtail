package com.longshihan.commonlibrary.model;

/**
 * @创建者 longshihan
 * 获取网络请求
 * @创建时间 2016/9/20 15:27
 */
public class HttpResponse<T> {
    public static final int SUCCESS_CODE = 0;

    private boolean error;
    private T       results;

    public T getResults() {
        return results;
    }


    public void setResults(T results) {
        this.results = results;
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

}
