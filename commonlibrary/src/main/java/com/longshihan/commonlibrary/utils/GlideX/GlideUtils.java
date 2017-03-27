package com.longshihan.commonlibrary.utils.GlideX;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * @author Administrator
 * @time 2016/12/9 14:10
 * @des Gilde的管理类
 */
public class GlideUtils {
    /**
     * @param context
     * @param url
     * @param view
     */
    public static void Gildes(Context context, String url, ImageView view) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    /**
     * @param context    上下文对象
     * @param url        图片链接
     * @param view       imageview
     * @param defaultImg 加载失败的图形
     */
    public static void Gildes(Context context, String url, ImageView view, int defaultImg) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(defaultImg)
                .into(view);
    }

    /**
     * @param context
     * @param url
     * @param view
     * @param defaultImg
     * @param holdimg    加载中的图形
     */

    public static void Gildes(Context context, String url, ImageView view, int defaultImg, int
            holdimg) {
        Glide.with(context)
                .load(url)
                .placeholder(holdimg)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(defaultImg)
                .into(view);
    }

    /**
     * @param context
     * @param url
     * @param view
     * @param defaultImg
     */
    public static void GildeSize(Context context, String url, ImageView view, int defaultImg) {
        GlideImageSizeModel customImageRequest = new GlideImageSizeModelFutureStudio(url);
        Glide.with(context)
                .using(new GlideImageSizeUrlLoader(context))
                .load(customImageRequest)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(defaultImg)
                .into(view);
    }

    /**
     * @param context
     * @param url
     * @param view
     * @param defaultImg
     * @param holdimg
     */
    public static void GildeSize(Context context, String url, ImageView view, int defaultImg, int
            holdimg) {
        GlideImageSizeModel customImageRequest = new GlideImageSizeModelFutureStudio(url);
        Glide.with(context)
                .using(new GlideImageSizeUrlLoader(context))
                .load(customImageRequest)
                .placeholder(holdimg)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(defaultImg)
                .into(view);
    }

    /**
     * @param context
     * @param url
     * @param view
     */
    public static void GildeSize(Context context, String url, ImageView view) {
        GlideImageSizeModel customImageRequest = new GlideImageSizeModelFutureStudio(url);
        Glide.with(context)
                .using(new GlideImageSizeUrlLoader(context))
                .load(customImageRequest)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param view
     * @param defaultImg
     */
    public static void GildeRound(Context context, String url, ImageView view, int defaultImg) {
        Glide.with(context).load(url)
                .transform(new GlideCircleTransform(context))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(defaultImg)
                .into(view);
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param view
     * @param radius
     * @param defaultImg
     */
    public static void GildeRound(Context context, String url, ImageView view, int radius, int defaultImg) {
        Glide.with(context).load(url)
                .transform(new GlideCircleTransform(context))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(defaultImg)
                .into(view);
    }

    /**
     * 添加高斯模糊
     */
   /* public static void Gilde(Context context, String url, final int radius, final ImageView view,
                             final LinearLayout linearLayout, Bitmap defaultImg) {
        //if (url == null || url.equals("")) {
        if (view != null) {
            Bitmap bitmap = changeColor(defaultImg);
            view.setImageBitmap(bitmap);
        }
        Bitmap newBitmap = StackBlur.blur(defaultImg, radius, false);
        linearLayout.setBackground(new BitmapDrawable(newBitmap));
        //} else {
        SimpleTarget target = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                if (view != null) {
                    Bitmap bitmap1 = changeColor(bitmap);
                    view.setImageBitmap(bitmap1);
                }
                Bitmap newBitmap = StackBlur.blur(bitmap, radius, false);
                linearLayout.setBackground(new BitmapDrawable(newBitmap));
            }
        };
        Glide.with(context)
                .load(url)
                .asBitmap()
                .into(target);
        //}

    }*/
    private static Bitmap changeColor(Bitmap defaultImg) {
        Bitmap bmp = Bitmap.createBitmap(defaultImg.getWidth(), defaultImg.getHeight(),
                                         defaultImg.getConfig()
                                        );
        int brightness = -30;
        ColorMatrix cMatrix = new ColorMatrix();
        cMatrix.set(new float[]{1, 0, 0, 0, brightness, 0, 1,
                0, 0, brightness,// 改变亮度
                0, 0, 1, 0, brightness, 0, 0, 0, 1, 0});
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(defaultImg, 0, 0, paint);
        return bmp;
    }

}
