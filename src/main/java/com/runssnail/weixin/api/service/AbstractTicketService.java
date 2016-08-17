package com.runssnail.weixin.api.service;

import com.runssnail.weixin.api.WeixinClient;
import com.runssnail.weixin.api.request.ticket.GetTicketRequest;
import com.runssnail.weixin.api.response.ticket.GetTicketResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by zhengwei on 2016/3/31.
 */
public abstract class AbstractTicketService implements TicketService {

    protected final Log log = LogFactory.getLog(getClass());

    private WeixinClient weiXinClient;

    private AccessTokenService accessTokenService;

    @Override
    public String refresh() {
        if (log.isInfoEnabled()) {
            log.info("refreshAccessToken start");
        }

        GetTicketResponse response = weiXinClient.execute(new GetTicketRequest(), accessTokenService.getAccessToken());
        if (response.isSuccess()) {
            saveTicket(response.getTicket());
            if (log.isInfoEnabled()) {
                log.info("refresh ticket success, new ticket->" + response.getTicket());
            }

            return response.getTicket();
        } else {
            log.error("refresh ticket error, errorCode=" + response.getErrcode() + ", errorInfo=" + response.getErrmsg());
        }

        return null;
    }

    /**
     * 保存，由自子类自己实现
     *
     * @param ticket
     */
    protected abstract void saveTicket(String ticket);

    public WeixinClient getWeiXinClient() {
        return weiXinClient;
    }

    public void setWeiXinClient(WeixinClient weiXinClient) {
        this.weiXinClient = weiXinClient;
    }

    public AccessTokenService getAccessTokenService() {
        return accessTokenService;
    }

    public void setAccessTokenService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }
}
