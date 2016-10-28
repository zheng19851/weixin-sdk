package com.runssnail.weixin.api.test.service;

import com.runssnail.weixin.api.DefaultWeixinClient;
import com.runssnail.weixin.api.WeixinClient;
import com.runssnail.weixin.api.request.menu.GetMenuRequest;
import com.runssnail.weixin.api.request.user.GetUserInfoRequest;
import com.runssnail.weixin.api.request.web.GetAuthAccessTokenRequest;
import com.runssnail.weixin.api.response.menu.GetMenuResponse;
import com.runssnail.weixin.api.response.user.GetUserInfoResponse;
import com.runssnail.weixin.api.response.web.GetAuthAccessTokenResponse;
import com.runssnail.weixin.api.service.DefaultWeiXinService;
import com.runssnail.weixin.api.service.MemoryAccessTokenService;

/**
 * Created by zhengwei on 16/7/20.
 */
public class WeiXinServiceTest {

    public static void main(String[] args) {

        String appId = "";
        String appSecret = "";

        WeixinClient weiXinClient = new DefaultWeixinClient(appId, appSecret);

        // 内存保存access token
        MemoryAccessTokenService accessTokenService = new MemoryAccessTokenService();
        accessTokenService.setWeiXinClient(weiXinClient);

        // WeiXinService默认实现
        DefaultWeiXinService weiXinService = new DefaultWeiXinService();
        weiXinService.setWeixinClient(weiXinClient);

        weiXinService.setAccessTokenService(accessTokenService);

        // 获取菜单
        GetMenuResponse getMenuResponse = weiXinService.execute(new GetMenuRequest());
        if (getMenuResponse.isSuccess()) {
            //
        }

        System.out.println(getMenuResponse);

        weiXinClient.close();

        String code = "";
        GetAuthAccessTokenRequest getAuthAccessTokenRequest = new GetAuthAccessTokenRequest(code);

        GetAuthAccessTokenResponse getAuthAccessTokenResponse = weiXinClient.execute(getAuthAccessTokenRequest);

        String openId = getAuthAccessTokenResponse.getOpenId();

        GetUserInfoRequest getUserInfoRequest = new GetUserInfoRequest(openId);


        GetUserInfoResponse getUserInfoResponse = weiXinService.execute(getUserInfoRequest);

        getUserInfoResponse.isSubscribed();

    }
}
