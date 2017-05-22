package com.longshihan.commonlibrary.utils.bar;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.text.TextUtils;

import java.lang.reflect.Method;

/**
 * Created by geyifeng on 2017/4/18.
 */

public class BarUtils {

    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_EMUI_VERSION_NAME = "ro.build.version.emui";
    private static final String KEY_DISPLAY = "ro.build.display.id";

    public static String MIUIVersion() {
        return isMIUI() ? getSystemProperty(KEY_MIUI_VERSION_NAME, null) : "";
    }

    public static boolean isMIUI() {
        String property = getSystemProperty(KEY_MIUI_VERSION_NAME, null);
        return !TextUtils.isEmpty(property);
    }

    public static boolean isFlymeOS() {
        return getMeizuFlymeOSFlag().toLowerCase().contains("flyme");
    }

    public static boolean isEMUI() {
        String property = getSystemProperty(KEY_EMUI_VERSION_NAME, null);
        return !TextUtils.isEmpty(property);
    }

    public static boolean isEMUI3_1() {
        if ("EmotionUI_3.1".equals(getSystemProperty(KEY_EMUI_VERSION_NAME, null))) {
            return true;
        }
        return false;
    }

    public static String getMeizuFlymeOSFlag() {
        return getSystemProperty(KEY_DISPLAY, "");
    }

    private static String getSystemProperty(String key, String defaultValue) {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method get = clz.getMethod("get", String.class, String.class);
            return (String) get.invoke(clz, key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }


    private static String getEmuiVersion() {
        Class<?> classType = null;
        try {
            classType = Class.forName("android.os.SystemProperties");
            Method getMethod = classType.getDeclaredMethod("get", String.class);
            return (String) getMethod.invoke(classType, "ro.build.version.emui");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @ColorInt
    public static int blendARGB(@ColorInt int color1, @ColorInt int color2,
                                @FloatRange(from = 0.0, to = 1.0) float ratio) {
        final float inverseRatio = 1 - ratio;
        float a = Color.alpha(color1) * inverseRatio + Color.alpha(color2) * ratio;
        float r = Color.red(color1) * inverseRatio + Color.red(color2) * ratio;
        float g = Color.green(color1) * inverseRatio + Color.green(color2) * ratio;
        float b = Color.blue(color1) * inverseRatio + Color.blue(color2) * ratio;
        return Color.argb((int) a, (int) r, (int) g, (int) b);
    }
}
