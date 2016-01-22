package com.runssnail.weixin.api.test.ticket;

import com.runssnail.weixin.api.RetryWeixinApiClient;
import com.runssnail.weixin.api.request.ticket.GetTicketRequest;
import com.runssnail.weixin.api.response.Response;
import com.runssnail.weixin.api.support.WeixinApiClients;

/**
 * GetTicketRequestTest
 * 
 * @author zhengwei
 */
public class GetTicketRequestTest {

    public static void main(String[] args) {
        String appId = "wxe58afcd99f7a997e";
        String appSecret = "5dcf8eac1e99e983fc58e42376ab0267";

        RetryWeixinApiClient weixinApiClient = null;
        try {
            weixinApiClient = WeixinApiClients.buildRetryWeixinApiClient(appId, appSecret);

            Response res = weixinApiClient.execute(new GetTicketRequest(), true);

            System.out.println(res);

        } finally {
            if (weixinApiClient != null) {
                weixinApiClient.close();
            }
        }

    }

}
