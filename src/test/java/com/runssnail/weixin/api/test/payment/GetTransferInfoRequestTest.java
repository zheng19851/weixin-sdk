package com.runssnail.weixin.api.test.payment;

import com.runssnail.weixin.api.DefaultWeiXinPaymentClient;
import com.runssnail.weixin.api.request.payment.GetTransferInfoRequest;
import com.runssnail.weixin.api.response.Response;

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

        DefaultWeiXinPaymentClient paymentClient = new DefaultWeiXinPaymentClient(appId, mchId, paySignKey, certPath, certPassword);


        String orderNo = "32739393939338332739393939338335";
        GetTransferInfoRequest request = new GetTransferInfoRequest(appId, mchId, orderNo);

        Response response = paymentClient.execute(request);

        System.out.println(response);


        paymentClient.close();
    }
}
