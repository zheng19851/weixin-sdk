package com.runssnail.weixin.api.test.token;

import com.runssnail.weixin.api.DefaultWeixinClient;
import com.runssnail.weixin.api.RetryWeiXinClient;
import com.runssnail.weixin.api.WeixinClient;
import com.runssnail.weixin.api.request.token.GetAccessTokenRequest;
import com.runssnail.weixin.api.response.token.GetAccessTokenResponse;

/**
 * 获取api的access token
 * 
 * @author zhengwei
 *
 */
public class GetAccessTokenRequestTest {

    public static void main(String[] args) {
        // panLaoBan test
        String appId = "wx7cbc0121c2093f64";
        String appSecret = "5380b2231935166e7d0f02cdce8e7209";

        WeixinClient defaultWeixinClient = new DefaultWeixinClient(appId, appSecret);

        RetryWeiXinClient weiXinClient = new RetryWeiXinClient(defaultWeixinClient);

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
