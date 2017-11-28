package com.shanbay.shanbaywork.main.activity;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import com.shanbay.shanbaywork.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

/**
 * Created by 明月春秋 on 2017/11/27.
 * 查询单词界面
 * 功能：
 *      1. 渲染一段英文文本
 *      2. 文本中的单词，被点击后可以高亮
 *      3. 单词高亮后，显示一个查词框（不限查词框形式）
 */

public class QueryWordActivity extends Activity {

    private TextView tvShowArticle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_word);
        init();
    }

    /**
     * 初始化文件IO工具，绑定控件
     */
    private void init() {
        tvShowArticle = findViewById(R.id.tv_show_article);
        readTextFromAsset("article.txt", tvShowArticle);
    }

    /**
     * 从资源文件中读取文本并进行显示
     * @param fileName
     *          被读取的资源文件名称
     * @param textView
     *          显示文本的控件
     */
    private void readTextFromAsset(String fileName, TextView textView) {
        AssetManager assetManager = getAssets();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line = null;
            StringBuilder content = new StringBuilder();
            while ((line = br.readLine()) != null){
                content.append(line);
            }
            textView.setTextSize(20);
            textView.setText(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(br);
        }
    }

    /**
     * 关闭流资源
     * @param reader
     *          需要关闭的流资源
     */
    private void closeStream(Reader reader) {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
