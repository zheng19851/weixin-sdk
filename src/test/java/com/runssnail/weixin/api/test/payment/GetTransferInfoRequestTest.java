package com.runssnail.weixin.api.test.payment;

import com.runssnail.weixin.api.DefaultWeixinPayClient;
import com.runssnail.weixin.api.request.payment.GetTransferInfoRequest;
import com.runssnail.weixin.api.response.payment.GetTransferInfoResponse;

/**
 * Created by zhengwei on 16/4/19.
 */
public class GetTransferInfoRequestTest {

    public static void main(String[] args) {
        String appId = "";
        String mchId = "";
        String paySignKey = "";

        String certPath = "/Users/zhengwei/apiclient_cert.p12";
        String certPassword = mchId;

        DefaultWeixinPayClient paymentClient = new DefaultWeixinPayClient(appId, mchId, paySignKey, certPath, certPassword);


        String orderNo = "32739393939338332739393939338337";
        GetTransferInfoRequest request = new GetTransferInfoRequest(orderNo);

        GetTransferInfoResponse response = paymentClient.execute(request);

        System.out.println(response);


        paymentClient.close();
    }
}
