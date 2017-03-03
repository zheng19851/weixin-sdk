package com.runssnail.weixin.api.test.payment;

import com.runssnail.weixin.api.DefaultWeixinPayClient;
import com.runssnail.weixin.api.request.pay.DownloadBillRequest;
import com.runssnail.weixin.api.response.pay.DownloadBillResponse;

/**
 * Created by zhengwei on 16/6/16.
 */
public class DownloadBillRequestTest {

    public static void main(String[] args) {

        String appId = "";
        String mchId = "";
        String paySignKey = "";

        DefaultWeixinPayClient paymentClient = new DefaultWeixinPayClient(appId, mchId, paySignKey);


        DownloadBillRequest request = new DownloadBillRequest("20140603");

        DownloadBillResponse response = paymentClient.execute(request);

        System.out.println(response);


        paymentClient.close();
    }
}
