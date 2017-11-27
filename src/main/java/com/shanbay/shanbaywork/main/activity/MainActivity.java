package com.shanbay.shanbaywork.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shanbay.shanbaywork.R;

/**
 * Created by 明月春秋 on 2017/11/27.
 * 应用程序主界面
 * 功能：
 *      用户通过界面按钮进入对应Activity
 *      btnQueryWord：进入查询单词界面
 *      btnLoadBitmap：进入加载图片界面
 */

public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnQueryWord;//查询单词按钮
    private Button btnLoadBitmap;//加载图片按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * 初始化绑定控件，并设置点击监听事件
     */
    private void init() {
        btnQueryWord = findViewById(R.id.btn_query_word);
        btnLoadBitmap = findViewById(R.id.btn_load_bitmap);
        btnQueryWord.setOnClickListener(this);
        btnLoadBitmap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //根据按钮Id，启动对应Activity
        switch (v.getId()){
            case R.id.btn_query_word:
                startActivity(new Intent(this, QueryWordActivity.class));
                break;
            case R.id.btn_load_bitmap:
                startActivity(new Intent(this, LoadBitmapActivity.class));
                break;
        }
    }
}
