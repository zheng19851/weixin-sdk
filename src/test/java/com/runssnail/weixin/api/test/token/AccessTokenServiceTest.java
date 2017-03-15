package com.runssnail.weixin.api.test.token;

import com.runssnail.weixin.api.manager.token.AccessTokenManager;
import com.runssnail.weixin.api.support.WeiXinClients;

public class AccessTokenServiceTest {

    public static void main(String[] args) {

        String appId = "";
        String appSecret = "";

        AccessTokenManager accessTokenService = null;
        try {

            accessTokenService = WeiXinClients.buildAccessTokenService(appId, appSecret);
            String accessToken = accessTokenService.getAccessToken();

            System.out.println("accessToken=" + accessToken);
        } finally {
            if (accessTokenService != null) {
//                accessTokenService.close();
            }
        }
    }
}
