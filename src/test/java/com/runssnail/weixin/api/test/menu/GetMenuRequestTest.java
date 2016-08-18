package com.runssnail.weixin.api.test.menu;

import com.runssnail.weixin.api.RetryWeiXinClient;
import com.runssnail.weixin.api.request.menu.GetMenuRequest;
import com.runssnail.weixin.api.response.Response;
import com.runssnail.weixin.api.service.MemoryAccessTokenService;
import com.runssnail.weixin.api.support.WeiXinClients;

public class GetMenuRequestTest {

    public static void main(String[] args) {
        String appId = "";
        String appSecret = "";

        RetryWeiXinClient weixinApiClient = null;
        try {
            weixinApiClient = WeiXinClients.buildRetryWeiXinClient(appId, appSecret);

            MemoryAccessTokenService accessTokenService = new MemoryAccessTokenService();
            accessTokenService.setWeiXinClient(weixinApiClient);

            GetMenuRequest req = new GetMenuRequest();

            Response res = weixinApiClient.execute(req, accessTokenService.getAccessToken());

            System.out.println(res);

        } finally {
            if (weixinApiClient != null) {
                weixinApiClient.close();
            }
        }

    }

}
