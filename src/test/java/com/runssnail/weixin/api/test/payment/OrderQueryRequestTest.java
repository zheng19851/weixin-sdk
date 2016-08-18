package com.runssnail.weixin.api.test.payment;

import com.runssnail.weixin.api.DefaultWeixinPayClient;
import com.runssnail.weixin.api.WeixinPayClient;
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
        String mchId = "";
        String paySignKey = "";


        WeixinPayClient client = new DefaultWeixinPayClient(appId, mchId, paySignKey);

        OrderQueryRequest request = new OrderQueryRequest();
        request.setOutTradeNo("2016071900174386040118");

        OrderQueryResponse response = client.execute(request);

        System.out.println(response);

        client.close();

    }
}
