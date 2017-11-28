package com.shanbay.shanbaywork.main.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.shanbay.shanbaywork.R;

/**
 * Created by 明月春秋 on 2017/11/27.
 * 加载图片界面
 * 功能：
 *      1. 用列表的形式展示一组图片
 *      2. 图片显示的要求是
 *         a. 先显示本地图片（目录：SD卡目录/Andorid/data/包名/Images ）
 *         b. 本地没有图片时，去尝试下载图片
 *            i. 有网络时，根据相应的url下载图片，下载完毕后，显示已下载的图片，并且
 *         保存在本地
 *            ii. 无网络或者下载失败时，显示默认图片（res文件）
 *         c. 图片下载失败时，增加合理的重试机制
 */

public class LoadBitmapActivity extends Activity {

    private ListView lvLoadBitmap;//显示图片的列表控件

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_bitmap);
        init();
    }

    /**
     * 初始化绑定控件
     */
    private void init() {
        lvLoadBitmap = findViewById(R.id.lv_show_bitmaps);
    }
}
