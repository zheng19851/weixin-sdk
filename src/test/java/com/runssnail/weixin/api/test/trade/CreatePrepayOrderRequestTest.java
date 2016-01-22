package com.runssnail.weixin.api.test.trade;

import com.runssnail.weixin.api.DefaultWechatPaymentClient;
import com.runssnail.weixin.api.WeixinApiClient;
import com.runssnail.weixin.api.domain.payment.TradeType;
import com.runssnail.weixin.api.request.payment.CreatePrepayOrderRequest;
import com.runssnail.weixin.api.response.payment.CreatePrepayOrderResponse;

/**
 * 创建预支付订单
 * 
 * @author zhengwei
 *
 */
public class CreatePrepayOrderRequestTest {

    public static void main(String[] args) {
        String appId = "wxc829b42548f53840";
        String merchantId = "10065789";

        String paySignKey = "hfhaf97fj32kj32jk98f98a833fajfa9";
//        String appSecret = "799376a5d78418e2a77479dae5df047b";
//
//        WeixinConfig config = new WeixinConfig(appId, appSecret);
//        config.setMerchantId("10065789");
//        config.setPaySignKey("hfhaf97fj32kj32jk98f98a833fajfa9");

        WeixinApiClient client = new DefaultWechatPaymentClient(appId, merchantId, paySignKey);

        try {

            CreatePrepayOrderRequest req = new CreatePrepayOrderRequest();
            req.setAppId(appId);
            req.setMerchantId(merchantId);
            req.setPaySignKey(paySignKey);
            req.setProductDesc("耳机");
            req.setOrderId("1229282");

            req.setTotalFee(1L);
            req.setIp("127.0.0.1");
            req.setNotifyUrl("www.baidu.com");
            req.setTradeType(TradeType.JSAPI);
            req.setOpenId("230fu0aifaofa09f9fi");

            CreatePrepayOrderResponse res = client.execute(req);

            System.out.println(res);

        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

}
