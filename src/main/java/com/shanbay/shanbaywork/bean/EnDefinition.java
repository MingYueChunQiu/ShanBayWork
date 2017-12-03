package com.shanbay.shanbaywork.bean;

/**
 * Created by 明月春秋 on 2017/12/3.
 * 英文释义信息Bean
 * 继承自Bean基类
 */

public class EnDefinition extends Bean {

    private int pos;

    private String defn;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getDefn() {
        return defn;
    }

    public void setDefn(String defn) {
        this.defn = defn;
    }
}
