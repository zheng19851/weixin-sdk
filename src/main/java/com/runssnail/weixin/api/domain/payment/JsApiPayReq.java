package com.runssnail.weixin.api.domain.payment;

import com.runssnail.weixin.api.domain.BaseDomain;

/**
 * js api 支付请求数据
 *
 * @author zhengwei
 */
public class JsApiPayReq extends BaseDomain {

    /**
     *
     */
    private static final long serialVersionUID = -5033942029745100717L;

    /**
     * 微信公众号id
     */
    private String            appId;

    /**
     * 微信预支付单id
     */
    private String            prepayId;

    /**
     * 随机字符串
     */
    private String            nonceStr;

    /**
     * 时间戳
     */
    private long              timeStamp;

    /**
     * 支付签名
     */
    private String            paySign;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

}
