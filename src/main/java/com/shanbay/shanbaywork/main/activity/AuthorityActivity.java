package com.shanbay.shanbaywork.main.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.shanbay.shanbaywork.R;
import com.shanbay.shanbaywork.bean.TokenBean;
import com.shanbay.shanbaywork.constants.ShanbayUrlConstants;

/**
 * Created by 明月春秋 on 2017/12/3.
 * 用于扇贝进行用户授权的界面
 */

public class AuthorityActivity extends Activity {

    private WebView wbAuthority;//显示授权相关网页的WebView
    private String tokenUrl = null;//token界面的url
    public static final int RESULT_CODE = 0x00;//结果码

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authority);
        init();
    }

    /**
     * 初始化控件，并进行初始化设置
     */
    private void init() {
        wbAuthority = findViewById(R.id.wb_authority);
        showAuthorityWeb(ShanbayUrlConstants.REQUEST_AUTHORITY);
    }

    /**
     * 加载授权网页
     * @param url
     *          授权网页地址
     */
    private void showAuthorityWeb(String url){
        wbAuthority.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                tokenUrl = url;
                //要进行判断是否是获取到token的页面
                if (!tokenUrl.contains("#access_token")){
                    return false;
                }
                TokenBean tokenBean = getTokenInfo(tokenUrl);
                if (tokenBean != null){
                    Intent intent = getIntent();
                    Log.d("传", tokenBean.getScope());
                    intent.putExtra("token", tokenBean);
                    setResult(RESULT_CODE, intent);
                }
                return false;
            }
        });
        wbAuthority.loadUrl(url);
//        Log.d("地址", wbAuthority.getUrl());
    }

    private TokenBean getTokenInfo(String url){

        String[] token = url.split("=");
        Log.d("测试", url+" "+token.length);
        Log.d("测试", token[1] + " "+ token[2]+" "+token[3]+" "+token[4]+" "+token[5]);
        TokenBean tokenBean = new TokenBean();
        tokenBean.setToken(getSubString(token, 1));
        tokenBean.setTokenType(getSubString(token, 2));
        tokenBean.setState(getSubString(token, 3));
        tokenBean.setExpires(Long.valueOf(getSubString(token, 4)));
        tokenBean.setScope(token[5].substring(0));
        Log.d("处理", url + " " + tokenBean.getToken() + " " + tokenBean.getExpires() + " "
                + tokenBean.getScope());
        return tokenBean;
    }

    /**
     * 获取分割得到的token相关的字符串数组中的子字符串
     * @param token
     *          token相关的字符串数组
     * @param index
     *          要操作的字符串数组的索引
     * @return
     *          返回截取的子字符串
     */
    private String getSubString(String[] token, int index){
        return token[index].substring(0, token[index].indexOf("&"));
    }
}
