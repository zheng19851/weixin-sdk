package com.runssnail.weixin.api.test.payment;

import com.runssnail.weixin.api.DefaultWeixinPayClient;
import com.runssnail.weixin.api.WeixinPayClient;
import com.runssnail.weixin.api.constants.payment.TradeType;
import com.runssnail.weixin.api.request.payment.UnifiedOrderRequest;
import com.runssnail.weixin.api.response.payment.UnifiedOrderResponse;

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

        WeixinPayClient client = new DefaultWeixinPayClient(appId, merchantId, paySignKey);

        try {

            UnifiedOrderRequest req = new UnifiedOrderRequest();
//            req.setAppId(appId);
//            req.setMerchantId(merchantId);
//            req.setPaySignKey(paySignKey);
            req.setBody("耳机");
            req.setOutTradeNo("1229282");

            req.setTotalFee(1L);
            req.setIp("127.0.0.1");
            req.setNotifyUrl("www.baidu.com");
            req.setTradeType(TradeType.JSAPI);
            req.setOpenId("230fu0aifaofa09f9fi");

            UnifiedOrderResponse res = client.execute(req);

            System.out.println(res.getPrepayId());

        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

}
