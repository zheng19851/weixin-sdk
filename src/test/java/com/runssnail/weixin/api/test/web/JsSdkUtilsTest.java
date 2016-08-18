package com.runssnail.weixin.api.test.web;

import com.runssnail.weixin.api.common.utils.PaymentUtils;
import com.runssnail.weixin.api.domain.payment.JsApiPayReq;

/**
 * Created by zhengwei on 16/6/28.
 */
public class JsSdkUtilsTest {
    public static void main(String[] args) {
        String appId = "";
//
//        String url = "http://m.watongban.cn/test/pay/order_pay.htm";
//        Config config = JsSdkUtils.getConfig(appId, "kgt8ON7yVITDhtdwci0qedJM_UH0zvQUbpVD3INoLUTKxxZG_RRX9O-kV4QDkjjizgLRpHYGtn65DbBDf0Rppw", url, SignType.MD5);
//        System.out.println(config);


        String prepayId = "wx201606280028244831f0e0500026535762";

        JsApiPayReq req = PaymentUtils.buildJsApiPayReq(appId, prepayId, "MgxJjILkONRsd85H5bUqTib02RFhRnS4");
        System.out.println(req);

    }
}
