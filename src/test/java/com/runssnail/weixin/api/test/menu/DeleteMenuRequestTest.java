package com.runssnail.weixin.api.test.menu;

import com.runssnail.weixin.api.RetryWeiXinClient;
import com.runssnail.weixin.api.request.menu.DeleteMenuRequest;
import com.runssnail.weixin.api.response.Response;
import com.runssnail.weixin.api.service.MemoryAccessTokenService;
import com.runssnail.weixin.api.support.WeiXinClients;

public class DeleteMenuRequestTest {

    public static void main(String[] args) {

        String appId = "";
        String appSecret = "";

        RetryWeiXinClient weixinApiClient = WeiXinClients.buildRetryWeiXinClient(appId, appSecret);

        MemoryAccessTokenService accessTokenService = new MemoryAccessTokenService();
        accessTokenService.setWeiXinClient(weixinApiClient);

        DeleteMenuRequest req = new DeleteMenuRequest();

        Response res = weixinApiClient.execute(req, accessTokenService.getAccessToken());

        System.out.println(res);

        weixinApiClient.close();
    }
}
