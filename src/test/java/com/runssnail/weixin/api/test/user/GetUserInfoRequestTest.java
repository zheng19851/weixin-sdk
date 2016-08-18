package com.runssnail.weixin.api.test.user;

import com.runssnail.weixin.api.DefaultWeixinClient;
import com.runssnail.weixin.api.WeixinClient;
import com.runssnail.weixin.api.request.user.GetUserInfoRequest;
import com.runssnail.weixin.api.response.user.GetUserInfoResponse;
import com.runssnail.weixin.api.service.MemoryAccessTokenService;

/**
 * Created by zhengwei on 16/5/30.
 */
public class GetUserInfoRequestTest {

    public static void main(String[] args) {
        String appId = "";
        String appSecret = "";

        WeixinClient weiXinClient = new DefaultWeixinClient(appId, appSecret);

        MemoryAccessTokenService accessTokenService = new MemoryAccessTokenService();
        accessTokenService.setWeiXinClient(weiXinClient);

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
