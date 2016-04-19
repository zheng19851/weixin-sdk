package com.runssnail.weixin.api.service;

import com.runssnail.weixin.api.WeiXinPaymentClient;
import com.runssnail.weixin.api.WeiXinClient;
import com.runssnail.weixin.api.common.utils.JsSdkUtils;
import com.runssnail.weixin.api.domain.jssdk.Config;
import com.runssnail.weixin.api.request.Request;
import com.runssnail.weixin.api.request.payment.PaymentRequest;
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

    private WeiXinClient weiXinClient;

    private AccessTokenService accessTokenService;

    private TicketService ticketService;

    /**
     * 微信支付api client
     */
    private WeiXinPaymentClient weiXinPaymentClient;

    @Override
    public String getAppId() {
        return weiXinClient.getAppId();
    }

    @Override
    public String getAppSecret() {
        return weiXinClient.getAppSecret();
    }

    @Override
    public String refreshAccessToken() {

        if (log.isInfoEnabled()) {
            log.info("refreshAccessToken start");
        }

        String accessToken = accessTokenService.refresh();

        if (log.isInfoEnabled()) {
            log.info("refreshAccessToken end, new Access Token->" + accessToken);
        }

        return accessToken;

    }

    @Override
    public String getAccessToken() {
        return this.accessTokenService.getAccessToken();
    }

    @Override
    public <R extends Response> R execute(Request<R> request) {

        assert request != null;

        if (request instanceof PaymentRequest) {
            return weiXinPaymentClient.execute(request);
        }

        String accessToken = this.accessTokenService.getAccessToken();
        return weiXinClient.execute(request, accessToken);
    }

    @Override
    public Config getJsSdkConfig(String url) {

        assert url != null;

        return JsSdkUtils.getConfig(this.getAppId(), ticketService.getTicket(), url);
    }

    @Override
    public String refreshTicket() {
        return this.ticketService.refresh();
    }

    @Override
    public String getTicket() {
        return this.ticketService.getTicket();
    }

    public WeiXinClient getWeiXinClient() {
        return weiXinClient;
    }

    public void setWeiXinClient(WeiXinClient weiXinClient) {
        this.weiXinClient = weiXinClient;
    }

    public AccessTokenService getAccessTokenService() {
        return accessTokenService;
    }

    public void setAccessTokenService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public WeiXinPaymentClient getWeiXinPaymentClient() {
        return weiXinPaymentClient;
    }

    public void setWeiXinPaymentClient(WeiXinPaymentClient weiXinPaymentClient) {
        this.weiXinPaymentClient = weiXinPaymentClient;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }
}
