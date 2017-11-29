package com.shanbay.shanbaywork.model.frame.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by 明月春秋 on 2017/11/29.
 * 自定义Glide的图片缓存路径
 */

public class CacheGlideModule implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context).build();
        int size = calculator.getMemoryCacheSize();
        String fileDirectory = "images";
        builder.setDiskCache(new DiskLruCacheFactory(fileDirectory, size));
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {

    }
}
