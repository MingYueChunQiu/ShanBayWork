package com.shanbay.shanbaywork.bean;

/**
 * Created by 明月春秋 on 2017/12/3.
 * 扇贝返回的Token对象
 */

public class TokenBean extends Bean{

    private String token;

    private String tokenType;

    private String state;

    private long expires;

    private String scope;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
