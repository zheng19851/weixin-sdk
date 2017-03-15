package com.runssnail.weixin.api.manager.ticket;

import com.runssnail.weixin.api.WeixinClient;
import com.runssnail.weixin.api.domain.ticket.TicketDO;
import com.runssnail.weixin.api.manager.AbstractValueManager;
import com.runssnail.weixin.api.manager.token.AccessTokenManager;
import com.runssnail.weixin.api.request.ticket.GetTicketRequest;
import com.runssnail.weixin.api.response.ticket.GetTicketResponse;
import com.runssnail.weixin.api.result.Result;

/**
 * ticket manager
 * <p>
 * Created by zhengwei on 2016/3/31.
 */
public abstract class AbstractTicketManager extends AbstractValueManager<TicketDO> implements TicketManager {

    private WeixinClient weixinClient;

    private AccessTokenManager accessTokenManager;

    /**
     * ticket 类型
     */
    private String ticketType;

    protected String getAppId() {
        return this.weixinClient.getAppId();
    }

    @Override
    public String getTicket() {
        return this.getStringValue();
    }

    @Override
    protected Result<TicketDO> doRefresh() {

        if (log.isInfoEnabled()) {
            log.info("refreshAndGet ticket start");
        }

        Result<TicketDO> result = new Result<>();

        GetTicketResponse response = weixinClient.execute(new GetTicketRequest(this.ticketType), accessTokenManager.getAccessToken());
        if (response.isSuccess()) {

            if (log.isInfoEnabled()) {
                log.info("refreshAndGet ticket success, new ticket->" + response.getTicket() + ", response->" + response);
            }

            TicketDO value = new TicketDO(this.getAppId(), response.getTicket(), System.currentTimeMillis());

            return result.setSuccess(true).setResult(value);
        } else {
            log.error("refreshAndGet ticket error, errorCode=" + response.getErrcode() + ", errorInfo=" + response.getErrmsg());
            return result.setError(response.getErrcode(), response.getErrmsg());
        }
    }

    public WeixinClient getWeixinClient() {
        return weixinClient;
    }

    public void setWeixinClient(WeixinClient weixinClient) {
        this.weixinClient = weixinClient;
    }

    public AccessTokenManager getAccessTokenManager() {
        return accessTokenManager;
    }

    public void setAccessTokenManager(AccessTokenManager accessTokenManager) {
        this.accessTokenManager = accessTokenManager;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
}
