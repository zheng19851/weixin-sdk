package com.runssnail.weixin.api.service;

import com.runssnail.weixin.api.WeixinClient;
import com.runssnail.weixin.api.constant.SignType;
import com.runssnail.weixin.api.domain.jssdk.Config;
import com.runssnail.weixin.api.domain.token.TokenDO;
import com.runssnail.weixin.api.manager.ticket.TicketManager;
import com.runssnail.weixin.api.manager.token.AccessTokenManager;
import com.runssnail.weixin.api.request.Request;
import com.runssnail.weixin.api.response.Response;
import com.runssnail.weixin.api.util.JsSdkUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 默认的微信服务实现
 * <p>
 * Created by zhengwei on 2016/3/17.
 */
public class DefaultWeiXinService implements WeiXinService {

    private static final Log log = LogFactory.getLog(DefaultWeiXinService.class);

    private WeixinClient weixinClient;

    private AccessTokenManager accessTokenManager;

    private TicketManager ticketManager;

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

        return this.accessTokenManager.refresh();
    }

    @Override
    public String getAccessToken() {
        return this.accessTokenManager.getAccessToken();
    }

    @Override
    public TokenDO getToken() {
        return this.accessTokenManager.getValue();
    }

    @Override
    public <R extends Response> R execute(Request<R> request) {

        assert request != null;

        String accessToken = this.accessTokenManager.getAccessToken();
        return weixinClient.execute(request, accessToken);
    }

    @Override
    public Config getJsSdkConfig(String url) {

        assert url != null;

        return JsSdkUtils.getConfig(this.getAppId(), ticketManager.getTicket(), url);
    }

    @Override
    public Config getJsSdkConfig(String url, SignType signType) {
        assert url != null;
        assert signType != null;

        return JsSdkUtils.getConfig(this.getAppId(), ticketManager.getTicket(), url, signType);
    }

    @Override
    public String refreshTicket() {
        return this.ticketManager.refresh();
    }

    @Override
    public String getTicket() {
        return this.ticketManager.getTicket();
    }

//    @Override
//    public JsApiPayReq buildJsApiPayReq(String prepayId) {
//        Validate.notEmpty(prepayId, "prepayId is required");
//
//        return PayUtils.buildJsApiPayReq(this.weixinPayClient.getAppId(), prepayId, this.weixinPayClient.getPaySignKey());
//    }


    public AccessTokenManager getAccessTokenManager() {
        return accessTokenManager;
    }

    public void setAccessTokenManager(AccessTokenManager accessTokenManager) {
        this.accessTokenManager = accessTokenManager;
    }

    public TicketManager getTicketManager() {
        return ticketManager;
    }

    public void setTicketManager(TicketManager ticketManager) {
        this.ticketManager = ticketManager;
    }

    public WeixinClient getWeixinClient() {
        return weixinClient;
    }

    public void setWeixinClient(WeixinClient weixinClient) {
        this.weixinClient = weixinClient;
    }

}
