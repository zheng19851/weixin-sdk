package com.runssnail.weixin.api.test.payment;

import com.alibaba.fastjson.JSON;
import com.runssnail.weixin.api.response.payment.GetTransferInfoResponse;

/**
 * Created by zhengwei on 16/4/14.
 */
public class GetTransferInfoResponseTest {
    public static void main(String[] args) {
//        GetTransferInfoResponse response = new GetTransferInfoResponse();
//
//        response.setMchId("333");
//        response.setPartnerTradeNo("323u99r");

        String json = "{\"partner_trade_no\":\"33322222\", \"mch_id\":\"3222fffkf\"}";

        System.out.println(JSON.parseObject(json, GetTransferInfoResponse.class));
    }
}
