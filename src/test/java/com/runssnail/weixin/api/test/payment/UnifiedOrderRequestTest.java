package com.runssnail.weixin.api.test.payment;

import com.runssnail.weixin.api.internal.support.WeixinPayResponseHelper;
import com.runssnail.weixin.api.request.payment.UnifiedOrderRequest;

/**
 * Created by zhengwei on 16/6/8.
 */
public class UnifiedOrderRequestTest {


    public static void main(String[] args) {

        String xml = "<xml><appid>wx2421b1c4370ec43b</appid><attach>支付测试</attach><body>JSAPI支付测试</body><mch_id>10000100</mch_id><nonce_str>1add1a30ac87aa2db72f57a2375d8fec</nonce_str><notify_url>http://wxpay.weixin.qq.com/pub_v2/pay/notify.v2.php</notify_url><openid>oUpF8uMuAJO_M2pxb1Q9zNjWeS6o</openid><out_trade_no>1415659990</out_trade_no><spbill_create_ip>14.23.150.211</spbill_create_ip><total_fee>1</total_fee><trade_type>JSAPI</trade_type><sign>0CB01533B8C1EF103065174F50BCA001</sign></xml>";

        UnifiedOrderRequest req = new UnifiedOrderRequest();
        Object res = WeixinPayResponseHelper.getObjectFromXml(xml, req);

        System.out.println(res);
    }
}
