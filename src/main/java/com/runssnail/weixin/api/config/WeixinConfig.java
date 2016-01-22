package com.runssnail.weixin.api.config;

import com.runssnail.weixin.api.constants.Constants;

/**
 * ΢微信配置
 * 
 * @author zhengwei
 */
public class WeixinConfig {

    private String appId;

    private String appSecret;

    /**
     * ΢商户id
     */
    private String merchantId;

    /**
     * ΢支付签名秘钥
     */
    private String paySignKey;

    private String defaultEncoding = Constants.DEFAULT_ENCODING;

    /**
     * ΢商户号
     * 
     * @param appId ΢appId
     * @param appSecret  appSecret
     */
    public WeixinConfig(String appId, String appSecret) {
        this.appId = appId;
        this.appSecret = appSecret;
    }

    public WeixinConfig() {

    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    /**
     *商户id
     *
     * @return 商户id
     */
    public String getMerchantId() {
        return this.merchantId;
    }

    public String getPaySignKey() {
        return this.paySignKey;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setPaySignKey(String paySignKey) {
        this.paySignKey = paySignKey;
    }

    public String getDefaultEncoding() {
        return defaultEncoding;
    }

    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

}
