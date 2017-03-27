package com.longshihan.commonlibrary.utils.GlideX;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;
import com.longshihan.commonlibrary.utils.FileUtil;

import java.io.File;

/**
 * @author Administrator
 * @time 2016/12/9 11:28
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class MyGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //修改图片质量
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //修改缓存路径
        int cacheSize100MegaBytes = 104857600;
        //data/com.zhaioto.sale/cache
        File path = new File(FileUtil.getDiskCacheDir(context) + "/cache");
        if (!path.exists()) {
            path.mkdirs();
        }
        String downloadDirectoryPath = FileUtil.getDiskCacheDir(context) + "/cache";
        builder.setDiskCache(
                new DiskLruCacheFactory(downloadDirectoryPath, cacheSize100MegaBytes));



    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        // register ModelLoaders here.
    }
}
