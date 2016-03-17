package com.runssnail.weixin.api.domain.jssdk;

import java.io.Serializable;

/**
 * js sdk配置
 * 
 * @author zhengwei
 */
public class Config implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7204339595843429975L;

    /**
     * appid
     */
    private String appId;

    /**
     * 时间戳
     */
    private String timestamp;

    /**
     * 随机串
     */
    private String nonceStr;

    /**
     * 签名
     */
    private String signature;

    public Config(String appId, String timestamp, String noncestr, String signature) {
        this.appId = appId;
        this.timestamp = timestamp;
        this.nonceStr = noncestr;
        this.signature = signature;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

}
