package com.shanbay.shanbaywork.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.shanbay.shanbaywork.R;
import com.shanbay.shanbaywork.bean.CnDefinition;
import com.shanbay.shanbaywork.bean.DataBean;
import com.shanbay.shanbaywork.bean.EnDefinition;
import com.shanbay.shanbaywork.bean.TokenBean;
import com.shanbay.shanbaywork.bean.WordBean;
import com.shanbay.shanbaywork.constants.ShanbayUrlConstants;
import com.shanbay.shanbaywork.model.frame.volley.GsonRequest;
import com.shanbay.shanbaywork.view.SelectableView;
import com.shanbay.shanbaywork.view.WordInfoView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 明月春秋 on 2017/11/27.
 * 查询单词界面
 * 功能：
 * 1. 渲染一段英文文本
 * 2. 文本中的单词，被点击后可以高亮
 * 3. 单词高亮后，显示一个查词框（不限查词框形式）
 */

public class QueryWordActivity extends Activity {

    private SelectableView svShowArticle;//采用两端对齐方式显示的文本控件
    private TokenBean tokenBean;//存储token信息对象

    private static final int AUTHORITY_REQUEST = 0x00;//获取授权和token的请求码

    private WordInfoView wvWordInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_word);
        init();
        startActivityForResult(new Intent(this, AuthorityActivity.class), AUTHORITY_REQUEST);
    }

    /**
     * 初始化文件IO工具，绑定控件
     */
    private void init() {
        svShowArticle = findViewById(R.id.sv_show_article);
        wvWordInfo = findViewById(R.id.wv_word_info);
        readTextFromAsset("article.txt", svShowArticle);
        svShowArticle.setOnWordSelectedListener(new SelectableView.OnWordSelectedListener() {
            @Override
            public void onSelected(String word) {
                RequestQueue queue = Volley.newRequestQueue(QueryWordActivity.this);
                JsonObjectRequest request = new JsonObjectRequest(ShanbayUrlConstants.QUERY_WORD + word,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                WordBean wordBean = getWordInfoFromJson(response);
                                if (wordBean == null){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(QueryWordActivity.this, "抱歉，未获取到该单词数据！",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    return;
                                }
                                Log.d("原始", wordBean.getData().getAudio() + " "
                                        + wordBean.getData().getContent());
                                setWordViewShown(wordBean.getData().getContent(),
                                        wordBean.getData().getPronunciation(),
                                        wordBean.getData().getDefinition(),
                                        wordBean.getData().getAudio());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put("Authorization", "Bearer " + tokenBean.getToken());
                        return map;
                    }
                };
                queue.add(request);
            }
        });
    }

    /**
     * 从资源文件中读取文本并进行显示
     *
     * @param fileName 被读取的资源文件名称
     * @param textView 显示文本的控件
     */
    private void readTextFromAsset(String fileName, TextView textView) {
        AssetManager assetManager = getAssets();
        BufferedInputStream br = null;
        try {
            //此处不能使用BufferedReader来读取字符，因为BufferedReader会自动将换行符过滤掉
            //所以文本会无法实现换行效果
            br = new BufferedInputStream(assetManager.open(fileName));
            byte[] bytes = new byte[1024];
            int num = 0;
            StringBuilder content = new StringBuilder();
            while ((num = br.read(bytes)) != -1) {
                content.append(new String(bytes, 0, num));
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
     *
     * @param is 需要关闭的流资源
     */
    private void closeStream(InputStream is) {
        if (is == null) {
            return;
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null){
            return;
        }
        if (requestCode == AUTHORITY_REQUEST && resultCode == AuthorityActivity.RESULT_CODE) {
            tokenBean = (TokenBean) data.getSerializableExtra("token");
        }
    }

    /**
     * 从json数据中获取单词信息
     *
     * @param jsonObject 从网上获取到单词json
     * @return 返回设置好信息的单词Bean对象
     */
    private WordBean getWordInfoFromJson(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        WordBean wordBean = new WordBean();
        DataBean dataBean = new DataBean();
        CnDefinition cnDefinition = new CnDefinition();
        EnDefinition enDefinition = new EnDefinition();
        try {
            JSONObject data = jsonObject.getJSONObject("data");
            cnDefinition.setDefn(data.getJSONObject("cn_definition").getString("defn"));
            enDefinition.setDefn(data.getJSONObject("en_definition").getString("defn"));
            String content = data.getString("content");
            String pronunciation = data.getString("pronunciation");
            String audio = data.getString("audio");
            String definition = data.getString("definition");
            dataBean.setCn_definition(cnDefinition);
            dataBean.setEn_definition(enDefinition);
            dataBean.setContent(content);
            dataBean.setPronunciation(pronunciation);
            dataBean.setAudio(audio);
            dataBean.setDefinition(definition);
            wordBean.setData(dataBean);
            return wordBean;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置单词View的显示
     */
    private void setWordViewShown(String content, String pronunciation, String definition, String audio) {
        if (wvWordInfo.getVisibility() == View.VISIBLE) {
            setWordViewGone();
        }
        wvWordInfo.setContent(content);
        wvWordInfo.setPronunciation(pronunciation);
        wvWordInfo.setDefinition(definition);
        wvWordInfo.setAudio(audio);
        wvWordInfo.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_bottom));
        wvWordInfo.setVisibility(View.VISIBLE);
    }

    /**
     * 设置单词View的消失
     */
    private void setWordViewGone() {
        if (wvWordInfo.getVisibility() == View.VISIBLE) {
            wvWordInfo.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_out_bottom));
            wvWordInfo.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (wvWordInfo.getVisibility() == View.VISIBLE) {
                setWordViewGone();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
