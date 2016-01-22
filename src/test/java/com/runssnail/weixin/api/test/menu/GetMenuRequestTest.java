package com.runssnail.weixin.api.test.menu;

import com.runssnail.weixin.api.RetryWeixinApiClient;
import com.runssnail.weixin.api.request.menu.GetMenuRequest;
import com.runssnail.weixin.api.response.Response;
import com.runssnail.weixin.api.support.WeixinApiClients;

public class GetMenuRequestTest {

    public static void main(String[] args) {
        String appId = "wxe58afcd99f7a997e";
        String appSecret = "5dcf8eac1e99e983fc58e42376ab0267";

        RetryWeixinApiClient weixinApiClient = null;
        try {
            weixinApiClient = WeixinApiClients.buildRetryWeixinApiClient(appId, appSecret);

            GetMenuRequest req = new GetMenuRequest();

            Response res = weixinApiClient.execute(req, true);

            System.out.println(res);

        } finally {
            if (weixinApiClient != null) {
                weixinApiClient.close();
            }
        }

    }

}
