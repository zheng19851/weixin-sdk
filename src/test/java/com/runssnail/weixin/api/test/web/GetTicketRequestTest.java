package com.runssnail.weixin.api.test.web;

import com.runssnail.weixin.api.RetryWeiXinClient;
import com.runssnail.weixin.api.request.web.GetJsApiTicketRequest;
import com.runssnail.weixin.api.response.Response;
import com.runssnail.weixin.api.service.MemoryAccessTokenService;
import com.runssnail.weixin.api.support.WeiXinClients;

/**
 * 获取jsapi ticket
 *
 * GetTicketRequestTest
 * 
 * @author zhengwei
 */
public class GetTicketRequestTest {

    public static void main(String[] args) {
        String appId = "";
        String appSecret = "";

        RetryWeiXinClient weixinApiClient = null;
        try {
            weixinApiClient = WeiXinClients.buildRetryWeiXinClient(appId, appSecret);

            MemoryAccessTokenService accessTokenService = new MemoryAccessTokenService();
            accessTokenService.setWeiXinClient(weixinApiClient);

            Response res = weixinApiClient.execute(new GetJsApiTicketRequest(), accessTokenService.getAccessToken());

            System.out.println(res);

        } finally {
            if (weixinApiClient != null) {
                weixinApiClient.close();
            }
        }

    }

}
