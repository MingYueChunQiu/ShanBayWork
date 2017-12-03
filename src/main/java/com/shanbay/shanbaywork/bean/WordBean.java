package com.shanbay.shanbaywork.bean;

/**
 * Created by 明月春秋 on 2017/12/3.
 * 扇贝单词信息Bean
 * 继承自Bean基类
 */

public class WordBean extends Bean {

    private int status_code;//状态码

    private String msg;//信息

    private DataBean data;//单词具体数据

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
}
