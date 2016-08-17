package com.runssnail.weixin.api.test.payment;

import com.runssnail.weixin.api.DefaultWeixinPaymentClient;
import com.runssnail.weixin.api.request.payment.DownloadBillRequest;
import com.runssnail.weixin.api.response.payment.DownloadBillResponse;

/**
 * Created by zhengwei on 16/6/16.
 */
public class DownloadBillRequestTest {

    public static void main(String[] args) {

        String appId = "wxada6b9ac122390ff";
        String mchId = "1334006201";
        String paySignKey = "MgxJjILkONRsd85H5bUqTib02RFhRnS4";

        DefaultWeixinPaymentClient paymentClient = new DefaultWeixinPaymentClient(appId, mchId, paySignKey);


        DownloadBillRequest request = new DownloadBillRequest("20140603");

        DownloadBillResponse response = paymentClient.execute(request);

        System.out.println(response);


        paymentClient.close();
    }
}
