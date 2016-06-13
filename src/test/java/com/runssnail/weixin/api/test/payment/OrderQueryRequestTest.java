package com.runssnail.weixin.api.test.payment;

import com.runssnail.weixin.api.DefaultWeiXinPaymentClient;
import com.runssnail.weixin.api.WeiXinPaymentClient;
import com.runssnail.weixin.api.request.payment.OrderQueryRequest;
import com.runssnail.weixin.api.response.payment.OrderQueryResponse;

/**
 * 支付订单查询接口
 *
 * Created by zhengwei on 16/6/13.
 */
public class OrderQueryRequestTest {

    public static void main(String[] args) {

        String appId = "";
        String merchantId = "";
        String paySignKey = "";


        WeiXinPaymentClient client = new DefaultWeiXinPaymentClient(appId, merchantId, paySignKey);

        OrderQueryRequest request = new OrderQueryRequest("1217752501201407033233368018");

        OrderQueryResponse response = client.execute(request);

        System.out.println(response);

        client.close();

    }
}
