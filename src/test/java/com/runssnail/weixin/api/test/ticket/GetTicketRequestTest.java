package com.runssnail.weixin.api.test.ticket;

import com.runssnail.weixin.api.DefaultWeixinClient;
import com.runssnail.weixin.api.request.ticket.GetTicketRequest;
import com.runssnail.weixin.api.response.ticket.GetTicketResponse;
import com.runssnail.weixin.api.service.MemoryAccessTokenService;

/**
 * Created by zhengwei on 2016/10/28.
 */
public class GetTicketRequestTest {
    public static void main(String[] args) {
        // 潘老板测试帐号
        String appId = "wx7cbc0121c2093f64";
        String appSecret = "5380b2231935166e7d0f02cdce8e7209";

        DefaultWeixinClient weixinClient = new DefaultWeixinClient(appId, appSecret);

        MemoryAccessTokenService accessTokenService = new MemoryAccessTokenService();
        accessTokenService.setWeiXinClient(weixinClient);


        GetTicketResponse getTicketResponse = weixinClient.execute(new GetTicketRequest("jsapi"), accessTokenService.getAccessToken());

        System.out.println(getTicketResponse);
    }
}
