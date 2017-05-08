package com.longshihan.commonlibrary.listener;

/**
 * @author longshihan
 * @time 2017/5/4 16:44
 * @des 下载任务的回调
 */

public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onCanceled();
    void onPaused();
}
