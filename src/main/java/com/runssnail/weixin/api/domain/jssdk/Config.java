package com.runssnail.weixin.api.domain.jssdk;

import com.runssnail.weixin.api.domain.BaseDomain;

import java.io.Serializable;

/**
 * js sdk配置
 * 
 * @author zhengwei
 */
public class Config extends BaseDomain implements Serializable {

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

    /**
     * jsapi ticket
     */
    private String ticket;

    public Config(String appId, String timestamp, String noncestr, String signature, String ticket) {
        this.appId = appId;
        this.timestamp = timestamp;
        this.nonceStr = noncestr;
        this.signature = signature;
        this.ticket = ticket;
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
