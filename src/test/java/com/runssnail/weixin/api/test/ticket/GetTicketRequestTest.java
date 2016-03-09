package com.runssnail.weixin.api.test.ticket;

import com.runssnail.weixin.api.RetryWeiXinClient;
import com.runssnail.weixin.api.request.ticket.GetTicketRequest;
import com.runssnail.weixin.api.response.Response;
import com.runssnail.weixin.api.support.WeiXinClients;

/**
 * GetTicketRequestTest
 * 
 * @author zhengwei
 */
public class GetTicketRequestTest {

    public static void main(String[] args) {
        String appId = "wxe58afcd99f7a997e";
        String appSecret = "5dcf8eac1e99e983fc58e42376ab0267";

        RetryWeiXinClient weixinApiClient = null;
        try {
            weixinApiClient = WeiXinClients.buildRetryWeiXinClient(appId, appSecret);

            Response res = weixinApiClient.execute(new GetTicketRequest(), true);

            System.out.println(res);

        } finally {
            if (weixinApiClient != null) {
                weixinApiClient.close();
            }
        }

    }

}
