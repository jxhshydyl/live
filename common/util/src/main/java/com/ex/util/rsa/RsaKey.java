package com.ex.util.rsa;

import java.io.Serializable;

/***
 * 定义用户登录时的Rsa信息  公钥以及私钥
 * @author apple
 */
public class RsaKey implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String pubKey;
    private String priKey;
    private long stimes;

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    public String getPriKey() {
        return priKey;
    }

    public void setPriKey(String priKey) {
        this.priKey = priKey;
    }

    public long getStimes() {
        return stimes;
    }

    public void setStimes(long stimes) {
        this.stimes = stimes;
    }
}
