package com.runssnail.weixin.api.service;

import com.runssnail.weixin.api.WeixinClient;
import com.runssnail.weixin.api.constant.SignType;
import com.runssnail.weixin.api.util.JsSdkUtils;
import com.runssnail.weixin.api.domain.jssdk.Config;
import com.runssnail.weixin.api.request.Request;
import com.runssnail.weixin.api.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 默认的微信服务实现
 *
 * Created by zhengwei on 2016/3/17.
 */
public class DefaultWeiXinService implements WeiXinService {

    private static final Log log = LogFactory.getLog(DefaultWeiXinService.class);

    private WeixinClient weixinClient;

    private AccessTokenService accessTokenService;

    private JsApiTicketService ticketService;

    @Override
    public String getAppId() {
        return weixinClient.getAppId();
    }

    @Override
    public String getAppSecret() {
        return weixinClient.getAppSecret();
    }

    @Override
    public String refreshAccessToken() {

        return this.accessTokenService.refresh();
    }

    @Override
    public String getAccessToken() {
        return this.accessTokenService.getAccessToken();
    }

    @Override
    public <R extends Response> R execute(Request<R> request) {

        assert request != null;

        String accessToken = this.accessTokenService.getAccessToken();
        return weixinClient.execute(request, accessToken);
    }

    @Override
    public Config getJsSdkConfig(String url) {

        assert url != null;

        return JsSdkUtils.getConfig(this.getAppId(), ticketService.getTicket(), url);
    }

    @Override
    public Config getJsSdkConfig(String url, SignType signType) {
        assert url != null;
        assert signType != null;

        return JsSdkUtils.getConfig(this.getAppId(), ticketService.getTicket(), url, signType);
    }

    @Override
    public String refreshTicket() {
        return this.ticketService.refresh();
    }

    @Override
    public String getTicket() {
        return this.ticketService.getTicket();
    }

//    @Override
//    public JsApiPayReq buildJsApiPayReq(String prepayId) {
//        Validate.notEmpty(prepayId, "prepayId is required");
//
//        return PayUtils.buildJsApiPayReq(this.weixinPayClient.getAppId(), prepayId, this.weixinPayClient.getPaySignKey());
//    }

    public AccessTokenService getAccessTokenService() {
        return accessTokenService;
    }

    public void setAccessTokenService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public JsApiTicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(JsApiTicketService ticketService) {
        this.ticketService = ticketService;
    }

    public WeixinClient getWeixinClient() {
        return weixinClient;
    }

    public void setWeixinClient(WeixinClient weixinClient) {
        this.weixinClient = weixinClient;
    }

}
