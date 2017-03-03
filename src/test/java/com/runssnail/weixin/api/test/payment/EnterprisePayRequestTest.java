package com.runssnail.weixin.api.test.payment;

import com.runssnail.weixin.api.DefaultWeixinPayClient;
import com.runssnail.weixin.api.request.pay.EnterprisePayRequest;
import com.runssnail.weixin.api.response.Response;

/**
 * Created by zhengwei on 16/4/19.
 */
public class EnterprisePayRequestTest {
    public static void main(String[] args) {

        String appId = "";
        String mchId = "";
        String paySignKey = "";

        String certPath = "/Users/zhengwei/apiclient_cert.p12";
        String certPassword = mchId;

        DefaultWeixinPayClient paymentClient = new DefaultWeixinPayClient(appId, mchId, paySignKey, certPath, certPassword);

        EnterprisePayRequest request = new EnterprisePayRequest();
        request.setAmount(100L);
//        request.setMerchantId(mchId);
//        request.setAppId(appId);
        request.setOrderNo("32739393939338332739393939338335");
        request.setOpenId("o9dkewPqU8xj9E39Ph-UfqCJdIew"); // o9dkewEqSw354trjs4sshGFnBois
        request.setDesc("提现");
        request.setIp("192.168.1.11");
//        request.setUserName("郑炜");
        Response response = paymentClient.execute(request);

        System.out.println(response);

        paymentClient.close();

//        String postData = "<xml>" +
//                "<mch_appid>wxe062425f740c30d8</mch_appid>" +
//                "<mchid>10000098</mchid>" +
//                "<nonce_str>3PG2J4ILTKCH16CQ2502SI8ZNMTM67VS</nonce_str>" +
//                "<partner_trade_no>100000982014120919616</partner_trade_no>" +
//                "<openid>ohO4Gt7wVPxIT1A9GjFaMYMiZY1s</openid>" +
//                "<check_name>OPTION_CHECK</check_name>" +
//                "<re_user_name>张三</re_user_name>" +
//                "<amount>100</amount>" +
//                "<desc>节日快乐!</desc>" +
//                "<spbill_create_ip>10.2.3.10</spbill_create_ip>" +
//                "<sign>C97BDBACF37622775366F38B629F45E3</sign>" +
//                "</xml>";

    }
}
