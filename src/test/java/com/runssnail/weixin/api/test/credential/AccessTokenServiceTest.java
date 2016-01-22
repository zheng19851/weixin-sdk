package com.runssnail.weixin.api.test.credential;

import com.runssnail.weixin.api.service.AccessTokenService;
import com.runssnail.weixin.api.support.WeixinApiClients;

public class AccessTokenServiceTest {

    public static void main(String[] args) {

        String appId = "wxe58afcd99f7a997e";
        String appSecret = "5dcf8eac1e99e983fc58e42376ab0267";

        AccessTokenService accessTokenService = null;
        try {

            accessTokenService = WeixinApiClients.buildAccessTokenService(appId, appSecret);
            String accessToken = accessTokenService.getAccessToken();

            System.out.println("accessToken=" + accessToken);
        } finally {
            if (accessTokenService != null) {
                accessTokenService.close();
            }
        }
    }
}
