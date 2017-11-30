package com.shanbay.shanbaywork.model.frame.glide;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * Created by 明月春秋 on 2017/11/29.
 * 自定义Glide的图片缓存路径
 */

public class CacheGlideModule implements GlideModule {

    @Override
    public void applyOptions(final Context context, GlideBuilder builder) {
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context).build();
        final int size = calculator.getMemoryCacheSize();
        String fileDirectory = "images";
        builder.setDiskCache(new DiskCache.Factory() {
            @Nullable
            @Override
            public DiskCache build() {
                File file = new File(context.getExternalCacheDir(), "images");
                file.mkdirs();
                return DiskLruCacheWrapper.get(file, size);
            }
        });
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {

    }
}
