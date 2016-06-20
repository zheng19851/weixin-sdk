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

    /**
     * 签名方式 md5/sha1
     */
    private String signType;

    /**
     *
     * @param appId 微信ID
     * @param timestamp 时间戳
     * @param nonceStr 随机字符串
     * @param signature 签名
     * @param ticket ticket
     * @param signType 签名方式
     */
    public Config(String appId, String timestamp, String nonceStr, String signature, String ticket, String signType) {
        this.appId = appId;
        this.timestamp = timestamp;
        this.nonceStr = nonceStr;
        this.signature = signature;
        this.ticket = ticket;
        this.signType = signType;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
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
