package com.longshihan.commonlibrary.http;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.connection.RealConnection;

/**
 * APNS推送的拦截器，
 * 1.设置链接最大并发请求参数
 * 2.超时统计并关闭链接，根据平均值统计（1.超过花消阀值，2.接近最大平均时间的)
 * Created by longshihan on 2017/3/30.
 */

public class ApnsNetworkInterceptor implements Interceptor {

    public static final double took_time_precent = 0.9;


    //花费时间阀值
    private final long time_took_throttle = 20000;
    //花费时间阀值(最低阀值）
    private final long time_took_throttle_min = 5000;

    //一个链接到并发请求上限
    private final int allocation_limit = 60;
    //统计次数
    private final int stat_count = 20;
    //统计服务器数上限
    private final int stat_server_limit = 100;
    //动态统计次数
    private final int dynamic_stat_count = 1000;

    private ConcurrentHashMap<String, ServerPointStat> pointStat = new ConcurrentHashMap<String, ServerPointStat>(
            stat_server_limit);

    //最大平均花费时间
    private long maxAvgTookTime = 0;
    //总调用次数
    private AtomicLong invokeTotalCount = new AtomicLong(0);
    RealConnection rc = null;

    public ApnsNetworkInterceptor() {
        //启动清除任务
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                //10分钟内没有统计信息的，清楚统计
                long expireTime = System.currentTimeMillis() - 600000;
                for (Map.Entry<String, ServerPointStat> stat : pointStat.entrySet()) {
                    if (stat.getValue().getLastUsedTime() < expireTime) {
                        pointStat.remove(stat.getKey());
                    }
                }
            }
        }, 60000, 60000);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        //设置一个链接的并发请求数上限
        Connection connection = chain.connection();

        if (connection != null && connection instanceof RealConnection) {
            rc = (RealConnection) connection;
            //只会对后面链接池里获取链接有效
            rc.allocationLimit = allocation_limit;

        }

        long startNs = System.nanoTime();
        Response response=null;
        try {
            //处理请求
            response = chain.proceed(request);
        }catch (Exception e ){
            Logger.e(e,"exception happened");
            throw e;
        }finally {

            long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

            try {

                if (rc == null) {
                    return response;
                }

                //统计计数
                String ip = rc.socket.getInetAddress().getHostAddress();
                ServerPointStat stat = pointStat.get(ip);
                if (stat == null) {
                    if (pointStat.size() > stat_server_limit) {
                        //不再统计，直接返回
                        return response;
                    }
                    stat = new ServerPointStat();
                    pointStat.put(ip,stat);
                }

                //调用统计
                long totalCount = invokeTotalCount.incrementAndGet();
                if (totalCount < 0) {
                    //溢出重置
                    invokeTotalCount.set(0);
                }

                //统计
                stat.invokeStat(tookMs);

                //质量差的服务节点处理
                long avgTime = stat.getAvgTime();
                if (stat.getUseCount() > stat_count && avgTime > time_took_throttle && tookMs > avgTime) {
                    //统计次数到了，平均花费时间超过阀值，认定为不好的服务器节点
                    noNewStreams(chain);
                } else if (maxAvgTookTime < time_took_throttle) {

                    //如果请求处理时间都没有超过阀值
                    if (totalCount > dynamic_stat_count && avgTime > time_took_throttle_min && tookMs > avgTime
                            && avgTime > Math.round(maxAvgTookTime * took_time_precent)) {
                        //如果都没有超过超时阀值，并且接近平均最大时间，任务是不好的链接
                        noNewStreams(chain);
                    }

                    if (avgTime > maxAvgTookTime) {
                        maxAvgTookTime = avgTime;
                    }
                }

            } catch (Exception e) {
                Logger.e(e,"统计发送异常");
            }

            //返回请求结果
            return response;
        }

    }

    private void noNewStreams(Interceptor.Chain chain) {
        /**这样处理像是有问题
         if (chain instanceof RealInterceptorChain) {
         StreamAllocation streamAllocation = ((RealInterceptorChain) chain).streamAllocation();
         if (streamAllocation.hasMoreRoutes()) {
         //该请求还有其他服务节点的时候，禁用用这个链接发送请求,等释放
         streamAllocation.noNewStreams();
         }
         }
         */
        rc.allocationLimit = 0;
    }

    private static class ServerPointStat {
        private long lastUsedTime;
        private AtomicLong useCount = new AtomicLong(0);
        private AtomicLong tookTotalTime = new AtomicLong(0);

        public void invokeStat(long tookTime) {

            tookTotalTime.addAndGet(tookTime);
            useCount.incrementAndGet();
            lastUsedTime = System.currentTimeMillis();

            if (tookTotalTime.get() < 0) {
                //溢出重置
                tookTotalTime.set(0);
                useCount.set(0);
            }
        }

        public long getAvgTime() {
            return tookTotalTime.get() / useCount.get();
        }

        /**
         * 获取 {@link #lastUsedTime}
         *
         * @return 返回 {@link #lastUsedTime}
         */
        public long getLastUsedTime() {
            return lastUsedTime;
        }

        /**
         * 获取 {@link #useCount}
         *
         * @return 返回 {@link #useCount}
         */
        public long getUseCount() {
            return useCount.get();
        }

        /*
         * 获取 {@link #tookTotalTime}
         *
         * @return 返回 {@link #tookTotalTime}
         */
        public long getTookTotalTime() {
            return tookTotalTime.get();
        }

    }
}
