package com.runssnail.weixin.api.test.user;

import com.runssnail.weixin.api.DefaultWeiXinClient;
import com.runssnail.weixin.api.WeiXinClient;
import com.runssnail.weixin.api.request.user.GetUserInfoRequest;
import com.runssnail.weixin.api.response.user.GetUserInfoResponse;
import com.runssnail.weixin.api.service.MemoryAccessTokenService;

/**
 * Created by zhengwei on 16/5/30.
 */
public class GetUserInfoRequestTest {

    public static void main(String[] args) {
        String appId = "wxe58afcd99f7a997e";
        String appSecret = "5dcf8eac1e99e983fc58e42376ab0267";

        WeiXinClient weiXinClient = new DefaultWeiXinClient(appId, appSecret);

        MemoryAccessTokenService accessTokenService = new MemoryAccessTokenService();
        accessTokenService.setWeiXinClient(weiXinClient);

        try {
            GetUserInfoRequest req = new GetUserInfoRequest(accessTokenService.getAccessToken(), "oeumFjrOrsYmEV-MAElyRnscFwoo");

            GetUserInfoResponse res = weiXinClient.execute(req);

            System.out.println(res);
        } finally {
            if (weiXinClient != null) {
                weiXinClient.close();
            }
        }


    }
}
