package com.runssnail.weixin.api.test.sns.oauth2;

import com.runssnail.weixin.api.DefaultWeixinClient;
import com.runssnail.weixin.api.WeixinClient;
import com.runssnail.weixin.api.request.sns.oauth2.GetAccessTokenRequest;
import com.runssnail.weixin.api.response.sns.oauth2.GetAccessTokenResponse;

/**
 * Created by zhengwei on 2016/10/28.
 */
public class GetAccessTokenRequestTest {

    public static void main(String[] args) {

        // 潘老板
        String appId = "wx7cbc0121c2093f64";
        String appSecret = "5380b2231935166e7d0f02cdce8e7209";

        WeixinClient weiXinClient = new DefaultWeixinClient(appId, appSecret);
        try {

            String code = "021gYL9c0j5RRv12mD8c0ZEM9c0gYL9e";
            GetAccessTokenRequest req = new GetAccessTokenRequest(code);

            GetAccessTokenResponse res = weiXinClient.execute(req);

            System.out.println(res);
        } finally {

            if (weiXinClient != null) {
                weiXinClient.close();
            }
        }
    }
}
