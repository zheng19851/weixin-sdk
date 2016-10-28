package com.runssnail.weixin.api.test.sns.oauth2;

import com.runssnail.weixin.api.WeixinClient;
import com.runssnail.weixin.api.request.sns.oauth2.GetAccessTokenRequest;
import com.runssnail.weixin.api.response.sns.oauth2.GetAccessTokenResponse;
import com.runssnail.weixin.api.service.MemoryAccessTokenService;
import com.runssnail.weixin.api.support.WeiXinClients;

/**
 * Created by zhengwei on 2016/10/28.
 */
public class GetAccessTokenRequestTest {

    public static void main(String[] args) {

        String appId = "";
        String appSecret = "";

        WeixinClient weiXinClient = null;
        try {
            weiXinClient = WeiXinClients.buildRetryWeiXinClient(appId, appSecret);

            String code = "";
            GetAccessTokenRequest req = new GetAccessTokenRequest(code);

            MemoryAccessTokenService accessTokenService = new MemoryAccessTokenService();
            accessTokenService.setWeiXinClient(weiXinClient);

            GetAccessTokenResponse res = weiXinClient.execute(req, accessTokenService.getAccessToken());

            System.out.println(res);
        } finally {

            if (weiXinClient != null) {
                weiXinClient.close();
            }
        }
    }
}
