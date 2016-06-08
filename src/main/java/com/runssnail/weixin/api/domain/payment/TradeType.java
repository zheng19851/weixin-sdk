package com.runssnail.weixin.api.domain.payment;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付交易类型，JSAPI、NATIVE、APP，必填
 *
 * @author zhengwei
 */
public enum TradeType {

    JSAPI("JSAPI"), NATIVE("NATIVE"), APP("APP");

    private String code;

    private static final Map<String, TradeType> CACHE = new HashMap<String, TradeType>(values().length);

    static {
        for (TradeType entry : values()) {
            CACHE.put(entry.code, entry);
        }

    }

    TradeType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public boolean isJsApi() {
        return JSAPI == this;
    }

}
