package com.runssnail.weixin.api.test.user;

import com.runssnail.weixin.api.WeixinClient;
import com.runssnail.weixin.api.request.user.GetUserRequest;
import com.runssnail.weixin.api.response.Response;
import com.runssnail.weixin.api.service.MemoryAccessTokenService;
import com.runssnail.weixin.api.support.WeiXinClients;


public class GetUserRequestTest {

    public static void main(String[] args) {
        String appId = "";
        String appSecret = "";

        WeixinClient weiXinClient = null;
        try {
            weiXinClient = WeiXinClients.buildRetryWeiXinClient(appId, appSecret);

            MemoryAccessTokenService accessTokenService = new MemoryAccessTokenService();
            accessTokenService.setWeiXinClient(weiXinClient);

            String token = "OezXcEiiBSKSxW0eoylIeJzncPOWyDcs4M0mnDjDyefojf46BkBFzjVLcygaNmtT_QZyAZezyaSMcKYQWnfpbjfb30oC4OoUlrbT-wDz87S5rXxM896PBjZogxXo1hXbp1hOByWHSUeg_ND7ohPjAg";

            GetUserRequest req = new GetUserRequest(token, "oeumFjrOrsYmEV-MAElyRnscFwoo");

            Response res = weiXinClient.execute(req);

            System.out.println(res);

        } finally {
            if (weiXinClient != null) {
                weiXinClient.close();
            }

        }

    }

}
