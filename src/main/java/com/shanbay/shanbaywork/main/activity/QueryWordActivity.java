package com.shanbay.shanbaywork.main.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shanbay.shanbaywork.R;

/**
 * Created by 明月春秋 on 2017/11/27.
 * 查询单词界面
 * 功能：
 *      1. 渲染一段英文文本
 *      2. 文本中的单词，被点击后可以高亮
 *      3. 单词高亮后，显示一个查词框（不限查词框形式）
 */

public class QueryWordActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_word);
    }
}
