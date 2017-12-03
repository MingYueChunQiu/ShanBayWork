package com.shanbay.shanbaywork.bean;

/**
 * Created by 明月春秋 on 2017/12/3.
 * 扇贝英文释义的数组Bean
 * 继承自Bean基类
 */

public class EnDefinitions extends Bean {

    private String[] n;

    private String[] v;

    public String[] getN() {
        return n;
    }

    public void setN(String[] n) {
        this.n = n;
    }

    public String[] getV() {
        return v;
    }

    public void setV(String[] v) {
        this.v = v;
    }
}
