package com.longshihan.commonlibrary.server;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

import com.longshihan.commonlibrary.R;
import com.longshihan.commonlibrary.listener.DownloadListener;
import com.longshihan.commonlibrary.utils.DownloadTask;
import com.longshihan.commonlibrary.utils.ToastInfo;

import java.io.File;

public class DownloadService extends Service {
    private DownloadTask mDownloadTask;
    private String downloadUrl;
    private DownloadListener mDownloadListener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("Download...", progress));
        }

        @Override
        public void onSuccess() {
            mDownloadTask = null;
            //下载失败通知前台服务关闭，并创建一个下载成功的通知
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Success", -1));
            ToastInfo.showLongToast(DownloadService.this, "Success...");

        }

        @Override
        public void onFailed() {
            mDownloadTask = null;
            //下载失败通知前台服务关闭，并创建一个下载失败的通知
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Failed", -1));
            ToastInfo.showLongToast(DownloadService.this, "Failed...");

        }

        @Override
        public void onCanceled() {
            mDownloadTask = null;
            stopForeground(true);
            ToastInfo.showLongToast(DownloadService.this, "Cancel...");

        }

        @Override
        public void onPaused() {
            mDownloadTask = null;
            ToastInfo.showLongToast(DownloadService.this, "Paused...");
        }
    };

    public DownloadService() {
    }

    private DownloadBinder mDownloadBinder = new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mDownloadBinder;
    }

    class DownloadBinder extends Binder {
        public void startDownload(String url) {
            if (mDownloadTask == null) {
                downloadUrl = url;
                mDownloadTask = new DownloadTask(mDownloadListener);
                mDownloadTask.execute(downloadUrl);
                startForeground(1, getNotification("Downloading...", 0));
                ToastInfo.showLongToast(DownloadService.this, "Download...");
            }
        }

        public void pauseDownload() {
            if (mDownloadTask != null) {
                mDownloadTask.pausedDownload();
            }
        }

        public void cancelDownload() {
            if (mDownloadTask != null) {
                mDownloadTask.cacelDownload();
            } else {
                if (downloadUrl != null) {
                    //取消下载时须将文件删除，并通知关闭
                    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + fileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    ToastInfo.showLongToast(DownloadService.this, "Cancel...");

                }
            }
        }
    }


    public NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title, int progress) {
        // Intent intent=new Intent(this,);
        Intent intent = new Intent();
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        if (progress > 0) {
            //当progress>=0的时候才显示下载进度
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }

        return builder.build();
    }
}
