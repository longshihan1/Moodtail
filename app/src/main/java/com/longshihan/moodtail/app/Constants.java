package com.longshihan.moodtail.app;

import com.longshihan.moodtail.App;

import java.io.File;

/**
 * @创建者 longshihan
 * @创建时间 2016/9/29 10:04
 */

public class Constants {

    /**
     * KEY
     */


    /**
     * PATH
     */


    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";
}
