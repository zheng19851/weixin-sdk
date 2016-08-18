package com.runssnail.weixin.api.test.user;

import com.runssnail.weixin.api.WeixinClient;
import com.runssnail.weixin.api.request.web.GetAuthAccessTokenRequest;
import com.runssnail.weixin.api.response.web.GetAuthAccessTokenResponse;
import com.runssnail.weixin.api.service.MemoryAccessTokenService;
import com.runssnail.weixin.api.support.WeiXinClients;

public class GetAuthAccessTokenRequestTest {

    public static void main(String[] args) {

        String appId = "";
        String appSecret = "";

        WeixinClient weiXinClient = null;
        try {
            weiXinClient = WeiXinClients.buildRetryWeiXinClient(appId, appSecret);

            String code = "";
            GetAuthAccessTokenRequest req = new GetAuthAccessTokenRequest(code);

            MemoryAccessTokenService accessTokenService = new MemoryAccessTokenService();
            accessTokenService.setWeiXinClient(weiXinClient);

            GetAuthAccessTokenResponse res = weiXinClient.execute(req, accessTokenService.getAccessToken());

            System.out.println(res);
        } finally {

            if (weiXinClient != null) {
                weiXinClient.close();
            }
        }

    }
}
