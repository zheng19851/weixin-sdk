package com.runssnail.weixin.api.test.service;

import com.runssnail.weixin.api.DefaultWeiXinClient;
import com.runssnail.weixin.api.WeiXinClient;
import com.runssnail.weixin.api.request.menu.GetMenuRequest;
import com.runssnail.weixin.api.response.menu.GetMenuResponse;
import com.runssnail.weixin.api.service.DefaultWeiXinService;
import com.runssnail.weixin.api.service.MemoryAccessTokenService;

/**
 * Created by zhengwei on 16/7/20.
 */
public class WeiXinServiceTest {

    public static void main(String[] args) {

        String appId = "";
        String appSecret = "";

        WeiXinClient weiXinClient = new DefaultWeiXinClient(appId, appSecret);

        // 内存保存access token
        MemoryAccessTokenService accessTokenService = new MemoryAccessTokenService();
        accessTokenService.setWeiXinClient(weiXinClient);

        // WeiXinService默认实现
        DefaultWeiXinService weiXinService = new DefaultWeiXinService();
        weiXinService.setWeiXinClient(weiXinClient);

        weiXinService.setAccessTokenService(accessTokenService);

        // 获取菜单
        GetMenuResponse getMenuResponse = weiXinService.execute(new GetMenuRequest());
        if (getMenuResponse.isSuccess()) {
            //
        }

        System.out.println(getMenuResponse);

        weiXinClient.close();
    }
}
