package com.runssnail.weixin.api.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 交易状态
 * <p>
 * Created by zhengwei on 16/7/20.
 */
public enum TradeState {

    SUCCESS("SUCCESS", "支付成功"),
    REFUND("REFUND", "转入退款"),
    NOTPAY("NOTPAY", "未支付"),
    CLOSED("CLOSED", "已关闭"),
    REVOKED("REVOKED", "已撤销（刷卡支付）"),
    USERPAYING("USERPAYING", "用户支付中"),
    PAYERROR("PAYERROR", "支付失败(其他原因，如银行返回失败)"),;

    private String code;
    private String desc;

    private static final Map<String, TradeState> CACHE = new HashMap<String, TradeState>(values().length);

    static {
        for (TradeState entry : values()) {
            CACHE.put(entry.code, entry);
        }

    }

    TradeState(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


    public TradeState get(String code) {
        return CACHE.get(code);
    }

    public static boolean isSuccess(String code) {
        return SUCCESS.getCode().equalsIgnoreCase(code);
    }
}
