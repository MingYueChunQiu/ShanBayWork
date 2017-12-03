package com.shanbay.shanbaywork.main.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;
import com.shanbay.shanbaywork.R;
import com.shanbay.shanbaywork.constants.BitmapUrlConstants;
import com.shanbay.shanbaywork.model.adapter.BitmapAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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
    private List<String> bitmapList = null;//图片资源集合

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_bitmap);
        init();
        //        loadBitmap();
    }

    /**
     * 初始化绑定控件，并设置相关资源
     */
    private void init() {
        lvLoadBitmap = findViewById(R.id.lv_show_bitmaps);
        if (bitmapList == null){
            bitmapList = new ArrayList<>();
        }
        for (int i = 0; i < BitmapUrlConstants.bitmaps.length; i ++){
            bitmapList.add(BitmapUrlConstants.bitmaps[i]);
        }
        BitmapAdapter adapter = new BitmapAdapter(this, bitmapList);
        lvLoadBitmap.setAdapter(adapter);
    }

//    private void loadBitmap(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final Context context = getApplicationContext();
//                FutureTarget<File> futureTarget = Glide.with(context).load(BitmapUrlConstants.BITMAP_1)
//                        .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
//                try {
//                    final File imageFile = futureTarget.get();
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(LoadBitmapActivity.this, imageFile.getAbsolutePath() + "", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//    }

}
