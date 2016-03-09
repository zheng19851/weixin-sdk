package com.runssnail.weixin.api.test.credential;

import com.runssnail.weixin.api.WeiXinClient;
import com.runssnail.weixin.api.request.credential.GetAccessTokenRequest;
import com.runssnail.weixin.api.response.Response;
import com.runssnail.weixin.api.support.WeiXinClients;

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

        WeiXinClient weiXinClient = null;
        try {
            weiXinClient = WeiXinClients.buildRetryWeiXinClient(appId, appSecret);
            GetAccessTokenRequest req = new GetAccessTokenRequest(appId, appSecret);

            Response res = weiXinClient.execute(req);
            
            System.out.println(res);
        } finally {
            if (weiXinClient != null) {
                weiXinClient.close();
            }
        }

    }

}
