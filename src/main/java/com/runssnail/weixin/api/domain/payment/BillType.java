package com.runssnail.weixin.api.domain.payment;

import java.util.HashMap;
import java.util.Map;

/**
 * 账单类型
 * ALL，返回当日所有订单信息，默认值
 * SUCCESS，返回当日成功支付的订单
 * REFUND，返回当日退款订单
 * <p>
 * Created by zhengwei on 16/6/8.
 */
public enum BillType {

    ALL("ALL", "所有订单信息"), SUCCESS("SUCCESS", "成功支付的订单"), REFUND("REFUND", "退款订单");

    private String code;
    private String desc;

    private static final Map<String, BillType> CACHE = new HashMap<String, BillType>(values().length);

    static {
        for (BillType entry : values()) {
            CACHE.put(entry.code, entry);
        }

    }

    BillType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
