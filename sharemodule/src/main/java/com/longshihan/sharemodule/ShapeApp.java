package com.longshihan.sharemodule;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.common.QueuedWork;

/**
 * @author longshihan
 * @time 2017/5/8 10:43
 * @des
 */

public class ShapeApp {
    public static Application mApplication;

    public static void init(Application application) {
        mApplication = application;
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG = true;
        QueuedWork.isUseThreadPool = false;
        UMShareAPI.get(application);
        initID();
    }

    private static void initID() {
        //各个平台的配置，建议放在全局Application或者程序入口
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
        PlatformConfig.setAlipay("2015111700822536");
        PlatformConfig.setPinterest("1439206");
    }

    /**
     * 使用系统级分享还是组件化分享
     *
     * @param osshare
     */
    public static void initOption(boolean osshare) {
        if (osshare) {

        } else {

        }
    }
}
