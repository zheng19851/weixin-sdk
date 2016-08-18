package com.runssnail.weixin.api.test.credential;

import com.runssnail.weixin.api.DefaultWeixinClient;
import com.runssnail.weixin.api.WeixinClient;
import com.runssnail.weixin.api.request.credential.GetAccessTokenRequest;
import com.runssnail.weixin.api.response.Response;

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

        WeixinClient weiXinClient = new DefaultWeixinClient(appId, appSecret);
        try {
            GetAccessTokenRequest req = new GetAccessTokenRequest();

            Response res = weiXinClient.execute(req);
            
            System.out.println(res);
        } finally {
            if (weiXinClient != null) {
                weiXinClient.close();
            }
        }

    }

}
