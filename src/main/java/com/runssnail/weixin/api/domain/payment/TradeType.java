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

    private String                                  val;

    private static final Map<String, TradeType> CACHE = new HashMap<String, TradeType>(values().length);

    static {
        for (TradeType entry : values()) {
            CACHE.put(entry.val, entry);
        }

    }

    private TradeType(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public boolean isJsApi() {
        return JSAPI == this;
    }

}
