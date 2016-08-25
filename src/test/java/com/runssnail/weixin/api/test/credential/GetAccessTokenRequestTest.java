package com.runssnail.weixin.api.test.credential;

import com.runssnail.weixin.api.DefaultWeixinClient;
import com.runssnail.weixin.api.WeixinClient;
import com.runssnail.weixin.api.request.credential.GetAccessTokenRequest;
import com.runssnail.weixin.api.response.credential.GetAccessTokenResponse;

/**
 * 获取api的access token
 * 
 * @author zhengwei
 *
 */
public class GetAccessTokenRequestTest {

    public static void main(String[] args) {
        String appId = "";
        String appSecret = "";

        WeixinClient weiXinClient = new DefaultWeixinClient(appId, appSecret);
        try {
            GetAccessTokenRequest req = new GetAccessTokenRequest();

            GetAccessTokenResponse res = weiXinClient.execute(req);
            
            System.out.println(res.getAccessToken());
        } finally {
            if (weiXinClient != null) {
                weiXinClient.close();
            }
        }

    }

}
