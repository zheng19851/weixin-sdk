package com.runssnail.weixin.api.test.credential;

import com.runssnail.weixin.api.WeixinApiClient;
import com.runssnail.weixin.api.request.credential.GetAccessTokenRequest;
import com.runssnail.weixin.api.response.Response;
import com.runssnail.weixin.api.support.WeixinApiClients;

/**
 * 获取api的access token
 * 
 * @author zhengwei
 *
 */
public class GetAccessTokenRequestTest {

    public static void main(String[] args) {
        String appId = "wxe58afcd99f7a997e";
        String appSecret = "5dcf8eac1e99e983fc58e42376ab0267";

        WeixinApiClient weixinApiClient = null;
        try {
            weixinApiClient = WeixinApiClients.buildRetryWeixinApiClient(appId, appSecret);
            GetAccessTokenRequest req = new GetAccessTokenRequest(appId, appSecret);

            Response res = weixinApiClient.execute(req);
            
            System.out.println(res);
        } finally {
            if (weixinApiClient != null) {
                weixinApiClient.close();
            }
        }

    }

}
