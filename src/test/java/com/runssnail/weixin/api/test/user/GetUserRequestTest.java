package com.runssnail.weixin.api.test.user;

import com.runssnail.weixin.api.WeiXinClient;
import com.runssnail.weixin.api.request.user.GetUserRequest;
import com.runssnail.weixin.api.response.Response;
import com.runssnail.weixin.api.service.MemoryAccessTokenService;
import com.runssnail.weixin.api.support.WeiXinClients;


public class GetUserRequestTest {

    public static void main(String[] args) {
        String appId = "wxe58afcd99f7a997e";
        String appSecret = "5dcf8eac1e99e983fc58e42376ab0267";

        WeiXinClient weiXinClient = null;
        try {
            weiXinClient = WeiXinClients.buildRetryWeiXinClient(appId, appSecret);

            MemoryAccessTokenService accessTokenService = new MemoryAccessTokenService();
            accessTokenService.setWeiXinClient(weiXinClient);

            GetUserRequest req = new GetUserRequest(appId, appSecret);

            Response res = weiXinClient.execute(req, accessTokenService.getAccessToken());

            System.out.println(res);

        } finally {
            if (weiXinClient != null) {
                weiXinClient.close();
            }

        }

    }

}
