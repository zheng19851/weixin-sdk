package com.runssnail.weixin.api.test.menu;

import com.runssnail.weixin.api.RetryWeiXinClient;
import com.runssnail.weixin.api.request.menu.GetMenuRequest;
import com.runssnail.weixin.api.response.Response;
import com.runssnail.weixin.api.support.WeiXinClients;

public class GetMenuRequestTest {

    public static void main(String[] args) {
        // zw test
        String appId = "wxe58afcd99f7a997e";
        String appSecret = "5dcf8eac1e99e983fc58e42376ab0267";

        RetryWeiXinClient weixinApiClient = null;
        try {
            weixinApiClient = WeiXinClients.buildRetryWeiXinClient(appId, appSecret);

//            MemoryAccessTokenManager accessTokenService = new MemoryAccessTokenManager();
//            accessTokenService.setWeiXinClient(weixinApiClient);

            GetMenuRequest req = new GetMenuRequest();

            Response res = weixinApiClient.execute(req, "OcJhL_GKdzEqkaB12yiwA1mgEgOf-sr1gTgWlAkv0HNyeGIHEGIRr_4PXCNXHg5ERJ77kySJwte3EFRt0yME04sEga-oI4S_SMtlkpQ0HGMQxMsAhsZ7s8ZZz4-SIdE-BYWjAIAROM");

            System.out.println(res);

        } finally {
            if (weixinApiClient != null) {
                weixinApiClient.close();
            }
        }

    }

}
