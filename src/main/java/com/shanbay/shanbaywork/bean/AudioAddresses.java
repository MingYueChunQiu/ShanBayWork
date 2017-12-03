package com.shanbay.shanbaywork.bean;

/**
 * Created by 明月春秋 on 2017/12/3.
 * 音频地址数组
 * 继承自Bean基类
 */

public class AudioAddresses extends Bean {

    private String[] uk;

    private String[] us;

    public String[] getUk() {
        return uk;
    }

    public void setUk(String[] uk) {
        this.uk = uk;
    }

    public String[] getUs() {
        return us;
    }

    public void setUs(String[] us) {
        this.us = us;
    }
}
