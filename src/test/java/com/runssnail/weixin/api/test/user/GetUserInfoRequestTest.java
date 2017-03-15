package com.runssnail.weixin.api.test.user;

import com.runssnail.weixin.api.DefaultWeixinClient;
import com.runssnail.weixin.api.WeixinClient;
import com.runssnail.weixin.api.request.user.GetUserInfoRequest;
import com.runssnail.weixin.api.response.user.GetUserInfoResponse;
import com.runssnail.weixin.api.manager.token.MemoryAccessTokenManager;

/**
 * Created by zhengwei on 16/5/30.
 */
public class GetUserInfoRequestTest {

    public static void main(String[] args) {
        // zw test
        String appId = "wxe58afcd99f7a997e";
        String appSecret = "5dcf8eac1e99e983fc58e42376ab0267";

        WeixinClient weiXinClient = new DefaultWeixinClient(appId, appSecret);

        MemoryAccessTokenManager accessTokenService = new MemoryAccessTokenManager();
        accessTokenService.setWeixinClient(weiXinClient);

        try {
            GetUserInfoRequest req = new GetUserInfoRequest("oeumFjrOrsYmEV-MAElyRnscFwoo");

            GetUserInfoResponse res = weiXinClient.execute(req, accessTokenService.getAccessToken());

            System.out.println(res);
        } finally {
            if (weiXinClient != null) {
                weiXinClient.close();
            }
        }


    }
}
